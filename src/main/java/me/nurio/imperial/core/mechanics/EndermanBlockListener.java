package me.nurio.imperial.core.mechanics;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EndermanBlockListener implements Listener {

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent eve) {
        if (eve.getEntity().getType() == EntityType.ENDERMAN) {
            eve.setCancelled(true);
        }
    }

}
