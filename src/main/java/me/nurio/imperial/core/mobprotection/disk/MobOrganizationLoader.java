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

public class MobOrganizationLoader {

    private static final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    public static void loadAll() {
        organizationFactory.getOrganizations()
                .forEach(MobOrganizationLoader::load);
    }

    public static void load(Organization organization) {
        Bukkit.getLogger().info("Loading organization mobs: " + organization.getUuid());

        GrechConfig config = new GrechConfig(Imperial.getPlugin(), "mobs" + File.separator + organization.getUuid());

        List<String> uuidMobs = config.getConfig().getStringList("mobs");

        uuidMobs.forEach(mob -> {
            UUID uuid = UUID.fromString(mob);
            MobOrganizationStorage.mobOrganizations.put(uuid, organization);
        });

        Bukkit.getLogger().info("Loaded "+uuidMobs.size()+" mobs into "+organization.getName());
    }

}
