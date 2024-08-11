package me.nurio.imperial.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Imperial extends JavaPlugin {

    @Override
    public void onEnable() {
        // Send loaded message
        Bukkit.getLogger().info("Imperial is installed!");
    }

}
