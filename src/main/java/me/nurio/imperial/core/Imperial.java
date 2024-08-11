package me.nurio.imperial.core;

import lombok.Getter;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import me.nurio.imperial.core.organizations.disk.OrganizationLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Imperial extends JavaPlugin {

    @Getter private static Plugin plugin;
    @Getter private static OrganizationFactory organizationFactory;

    @Override
    public void onEnable() {
        plugin = this;

        // Register Organization Factory
        organizationFactory = new OrganizationFactory();

        // Load Organizations
        OrganizationLoader.loadAll();

        // Send loaded message
        Bukkit.getLogger().info("Imperial is installed!");
    }

}
