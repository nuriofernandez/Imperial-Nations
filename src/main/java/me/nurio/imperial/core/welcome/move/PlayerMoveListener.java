package me.nurio.imperial.core.welcome.move;

import me.nurio.minecraft.worldareas.areas.WorldArea;
import me.nurio.minecraft.worldareas.events.PlayerJoinAreaEvent;
import me.nurio.minecraft.worldareas.events.PlayerLeaveAreaEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onJoinArea(PlayerJoinAreaEvent eve) {
        WorldArea worldArea = eve.getWorldArea();
        AreaMoveMessages.sendWelcome(eve.getPlayer(), worldArea);
    }

    @EventHandler
    public void onLeaveArea(PlayerLeaveAreaEvent eve) {
        WorldArea worldArea = eve.getWorldArea();
        AreaMoveMessages.sendGoodbye(eve.getPlayer(), worldArea);
    }

}
