package me.nurio.imperial.core.power;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import me.nurio.imperial.core.organizations.disk.OrganizationSaver;
import me.nurio.imperial.core.power.config.disk.BlockPowerLoader;
import me.nurio.imperial.core.power.config.disk.BlockPowerSaver;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;

import java.util.Arrays;
import java.util.Collection;

public class PowerSystem {

    public static void start() {
        // Load power
        BlockPowerLoader.loadAll();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Imperial.getPlugin(),
            () -> {
                int onlinePlayers = Bukkit.getOnlinePlayers().size();
                if (onlinePlayers == 0) {
                    performOperation();
                }
            },
            20 * 60,
            20 * 60 * 60
        );

        Bukkit.getPluginManager().registerEvents(new DayPassListener(), Imperial.getPlugin());
    }

    public static void stop(){
        BlockPowerSaver.saveAll();
    }

    public static void performOperation() {
        OrganizationFactory factory = Imperial.getOrganizationFactory();

        // Send a message warning the operation is starting
        Bukkit.getLogger().info("Starting organization power calculation.");

        // Perform the operation
        long start = System.currentTimeMillis();
        for (Organization organization : factory.getOrganizations()) {
            int power = calculateOrganizationPower(organization);
            Bukkit.getLogger().info(
                "Organization '" + organization.getName() + "' has a power of '" + power + "'"
            );

            int membersPower = calculateOrganizationMembersPower(organization);
            Bukkit.getLogger().info(
                    "Organization '" + organization.getName() + "' has a MEMBERS power of '" + membersPower + "'"
            );

            // Save power to organization
            organization.setPower(power + membersPower);
            OrganizationSaver.save(organization);
        }

        // Send a message indicating the operation has been done
        long end = System.currentTimeMillis();
        long time = end - start;
        Bukkit.getLogger().info("Organization power calcualtion took "+time+"ms");
    }

    public static int calculateOrganizationPower(Organization organization) {
        return organization.getWorldArea()
            .getAreas()
            .stream()
            .map(AreaPointCalculatorOperation::new)
            .map(AreaPointCalculatorOperation::getSpecialBlocks)
            .flatMap(Collection::stream)
            .distinct()
            .map(PowerFromLocation::power)
            .mapToInt(Integer::intValue)
            .sum();
    }

    public static int calculateOrganizationMembersPower(Organization organization) {
        return organization.getMembers().stream()
                .map(PowerSystem::memberPower)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static int memberPower(OfflinePlayer player) {
        int statistic = player.getStatistic(Statistic.MINE_BLOCK, Material.STONE);
        statistic += player.getStatistic(Statistic.MINE_BLOCK, Material.DIRT);
        statistic += player.getStatistic(Statistic.MINE_BLOCK, Material.GRASS_BLOCK);
        statistic += player.getStatistic(Statistic.MINE_BLOCK, Material.SAND);
        statistic += player.getStatistic(Statistic.MINE_BLOCK, Material.GRAVEL);
        return statistic;
    }

}
