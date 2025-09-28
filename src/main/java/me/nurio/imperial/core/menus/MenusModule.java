package me.nurio.imperial.core.menus;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.menus.creation.InitialClaimListener;
import me.nurio.imperial.core.menus.mapbench.CartographyClickListener;
import me.nurio.imperial.core.menus.mapbench.dialog.LeaveOrganizationDialog;
import me.nurio.imperial.core.menus.mapbench.dialog.OrganizationSettingsDialog;
import me.nurio.imperial.core.menus.mapbench.dialog.RenameOrganizationDialog;
import me.nurio.imperial.core.menus.playertoplayer.PlayerClickListener;
import org.bukkit.Bukkit;

public class MenusModule {

    public static void register(BootstrapContext context) {
        RenameOrganizationDialog.register(context);
        OrganizationSettingsDialog.register(context);
        LeaveOrganizationDialog.register(context);
    }

    public static void start() {
        // Register listeners
        Bukkit.getPluginManager().registerEvents(new PlayerClickListener(), Imperial.getPlugin());
        Bukkit.getPluginManager().registerEvents(new CartographyClickListener(), Imperial.getPlugin());
        Bukkit.getPluginManager().registerEvents(new InitialClaimListener(), Imperial.getPlugin());
    }

}
