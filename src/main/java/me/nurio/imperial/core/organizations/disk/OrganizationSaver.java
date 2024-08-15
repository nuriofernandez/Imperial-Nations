package me.nurio.imperial.core.organizations.disk;

import me.nurio.bukkit.configuration.files.GrechConfig;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.minecraft.worldareas.GrechAreas;
import me.nurio.minecraft.worldareas.areas.WorldArea;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrganizationSaver {

    public static void saveAll() {
        Imperial.getOrganizationFactory().getOrganizations()
            .stream()
            .forEach(OrganizationSaver::save);
    }

    public static File save(Organization organization) {
        Bukkit.getLogger().info("Saving organization: " + organization.getUuid());

        GrechConfig config = new GrechConfig(Imperial.getPlugin(), "organizations" + File.separator + organization.getUuid());

        config.set("name", organization.getName());
        config.set("uuid", organization.getUuid().toString());

        List<String> uuidMembers = organization.getMembers()
            .stream()
            .map(OfflinePlayer::getUniqueId)
            .map(UUID::toString)
            .toList();
        config.getConfig().set("members", uuidMembers);

        config.set("worldArea", organization.getWorldArea().getUuid().toString());

        // Save power if it's not 0 (0 could mean it's not calculated yet)
        if (organization.getPower() != 0) {
            config.set("power", 0);
        }

        return config.getConfigFile();
    }

}
