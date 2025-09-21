package me.nurio.imperial.core.mobprotection.disk;

import me.nurio.bukkit.configuration.files.GrechConfig;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.mobprotection.MobOrganizationStorage;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MobOrganizationSaver {

    private static final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    public static void saveAll() {
        organizationFactory.getOrganizations()
                .forEach(MobOrganizationSaver::save);
    }

    public static void save(Organization organization) {
        Bukkit.getLogger().info("Saving organization mobs: " + organization.getUuid());

        GrechConfig config = new GrechConfig(Imperial.getPlugin(), "mobs" + File.separator + organization.getUuid());

        config.set("name", organization.getName());
        config.set("uuid", organization.getUuid().toString());

        List<String> uuidMobs = MobOrganizationStorage.mobOrganizations.entrySet()
                .stream().filter(entry -> entry.getValue().equals(organization))
                .map(Map.Entry::getKey)
                .map(UUID::toString)
                .toList();
        config.getConfig().set("mobs", uuidMobs);

        // Save config
        config.save();
    }

}
