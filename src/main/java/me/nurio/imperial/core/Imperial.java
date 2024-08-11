package me.nurio.imperial.core;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Imperial extends JavaPlugin {

    @Getter
    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // Send loaded message
        Bukkit.getLogger().info("Imperial is installed!");
    }

}
