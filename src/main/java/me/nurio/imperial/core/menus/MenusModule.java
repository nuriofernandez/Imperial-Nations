package me.nurio.imperial.core.menus;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.menus.mapbench.CartographyClickListener;
import me.nurio.imperial.core.menus.playertoplayer.PlayerClickListener;
import org.bukkit.Bukkit;

public class MenusModule {

    public static void start() {
        Bukkit.getPluginManager().registerEvents(new PlayerClickListener(), Imperial.getPlugin());
        Bukkit.getPluginManager().registerEvents(new CartographyClickListener(), Imperial.getPlugin());
    }

}
