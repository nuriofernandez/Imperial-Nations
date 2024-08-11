package me.nurio.imperial.core;

import lombok.Getter;
import me.nurio.imperial.core.areas.PlayerClaimListener;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import me.nurio.imperial.core.organizations.disk.OrganizationLoader;
import me.nurio.imperial.core.protection.ProtectionPlayerListener;
import me.nurio.imperial.core.welcome.join.PlayerJoinListener;
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

        // Register event listeners
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(organizationFactory), this);
        Bukkit.getPluginManager().registerEvents(new ProtectionPlayerListener(organizationFactory), this);
        Bukkit.getPluginManager().registerEvents(new PlayerClaimListener(), this);

        // Send loaded message
        Bukkit.getLogger().info("Imperial is installed!");
    }

}
