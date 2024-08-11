package me.nurio.imperial.core.organizations.disk;

import me.nurio.bukkit.configuration.files.GrechConfig;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrganizationLoader {

    public static void loadAll() {
        getOrganizationFiles().stream()
            .map(OrganizationLoader::parse)
            .forEach(Imperial.getOrganizationFactory()::addOrganization);
    }

    /**
     * Obtains a list of all organizations configuration files.
     *
     * @return List of YAML organization configuration files.
     */
    public static List<File> getOrganizationFiles() {
        File folder = new File(Imperial.getPlugin().getDataFolder() + File.separator + "organizations");
        if (!folder.exists()) folder.mkdirs();
        return Arrays.stream(folder.listFiles())
            .filter(file -> file.getName().endsWith(".yml"))
            .collect(Collectors.toList());
    }


    public static Organization parse(File file) {
        Bukkit.getLogger().info("Loading organization: " + file.getPath());

        GrechConfig config = new GrechConfig(Imperial.getPlugin(), "organizations" + File.separator + file.getName());

        String name = config.getConfig().getString("name");
        UUID uuid = UUID.fromString(config.getConfig().getString("uuid"));

        List<String> uuidMembers = config.getConfig().getStringList("members");
        List<OfflinePlayer> members = uuidMembers.stream()
            .map(UUID::fromString)
            .map(Bukkit::getOfflinePlayer)
            .toList();

        return new Organization(uuid, name, members);
    }

}
