package me.nurio.imperial.core.protection;

import me.nurio.imperial.core.organizations.OrganizationFactory;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;


public class ProtectionPlayerListener implements Listener {

    @NotNull private OrganizationFactory organizationFactory;
    @NotNull private PermissionManager permissionManager;

    public ProtectionPlayerListener(OrganizationFactory organizationFactory) {
        this.organizationFactory = organizationFactory;
        this.permissionManager = new PermissionManager(organizationFactory);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void blockBreakEvent(BlockBreakEvent eve) {
        boolean hasPermissionsAt = permissionManager.hasPermissionsAt(eve.getPlayer(), eve.getBlock().getLocation());

        // Outsiders can't
        if (!hasPermissionsAt) {
            eve.setCancelled(true);
            return;
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void blockPlaceEvent(BlockPlaceEvent eve) {
        boolean hasPermissionsAt = permissionManager.hasPermissionsAt(eve.getPlayer(), eve.getBlock().getLocation());

        // Outsiders can't
        if (!hasPermissionsAt) {
            eve.setCancelled(true);
            return;
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void blockCanEvent(BlockCanBuildEvent eve) {
        boolean hasPermissionsAt = permissionManager.hasPermissionsAt(eve.getPlayer(), eve.getBlock().getLocation());

        // Outsiders can't
        if (!hasPermissionsAt) {
            eve.setBuildable(false);
            return;
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void playerInteractEvent(PlayerInteractEvent eve) {
        Location interactionPoint = eve.getInteractionPoint();
        if (interactionPoint == null) {
            return;
        }

        boolean hasPermissionsAt = permissionManager.hasPermissionsAt(eve.getPlayer(), interactionPoint);
        if (!hasPermissionsAt) {
            eve.setCancelled(true);
            return;
        }
    }

}
