package me.nurio.imperial.core.nether;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class NetherPortalListener implements Listener {

    @EventHandler
    public void onPortal(PortalCreateEvent eve) {
        // Only count nether portal user creation
        if (eve.getReason() != PortalCreateEvent.CreateReason.FIRE) {
            return;
        }

        // Disallow creating portals from nether
        if (!eve.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
            eve.setCancelled(true);
            return;
        }

        // Make sure portals are underground
        Location location = eve.getBlocks().getFirst().getLocation();
        if (location.getBlockY() >= 15) {
            eve.setCancelled(true);
        }
    }

}
