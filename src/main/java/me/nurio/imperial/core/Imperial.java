package me.nurio.imperial.core;

import lombok.Getter;
import me.nurio.imperial.core.areas.InitialClaimListener;
import me.nurio.imperial.core.areas.PlayerClaimListener;
import me.nurio.imperial.core.chat.ChatListener;
import me.nurio.imperial.core.nether.NetherPortalListener;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import me.nurio.imperial.core.organizations.disk.OrganizationLoader;
import me.nurio.imperial.core.power.PowerSystem;
import me.nurio.imperial.core.protection.ProtectionPlayerListener;
import me.nurio.imperial.core.welcome.join.PlayerJoinListener;
import me.nurio.imperial.core.welcome.move.PlayerMoveListener;
import me.nurio.imperial.core.worldborder.DynamicWorldBorder;
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
        Bukkit.getPluginManager().registerEvents(new InitialClaimListener(), this);
        Bukkit.getPluginManager().registerEvents(new ProtectionPlayerListener(organizationFactory), this);
        Bukkit.getPluginManager().registerEvents(new PlayerClaimListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(organizationFactory), this);
        Bukkit.getPluginManager().registerEvents(new NetherPortalListener(), this);

        // Register Organization power system
        PowerSystem.start();
        DynamicWorldBorder.start();

        // Send loaded message
        Bukkit.getLogger().info("Imperial is installed!");
    }

}
