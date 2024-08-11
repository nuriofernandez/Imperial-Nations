package me.nurio.imperial.core.organizations.disk;

import me.nurio.imperial.core.Imperial;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrganizationLoader {

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

}
