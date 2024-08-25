package me.nurio.imperial.core.power;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import me.nurio.imperial.core.organizations.disk.OrganizationSaver;
import org.bukkit.Bukkit;

import java.util.Collection;

public class PowerSystem {

    public static void start() {
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

            // Save power to organization
            organization.setPower(power);
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
            .map(PowerCalculator::powerFromLocation)
            .mapToInt(Integer::intValue)
            .sum();
    }

}
