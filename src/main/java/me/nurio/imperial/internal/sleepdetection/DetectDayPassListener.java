package me.nurio.imperial.internal.sleepdetection;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class DetectDayPassListener implements Listener {

    @EventHandler
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
        long time = event.getPlayer().getWorld().getTime();
        if (time >= 0 && time <= 100) {
            Bukkit.getPluginManager().callEvent(new DayPassBySleepEvent());
        }
    }

}
