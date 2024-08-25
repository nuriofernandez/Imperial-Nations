package me.nurio.imperial.core.power;

import me.nurio.imperial.internal.sleepdetection.DayPassBySleepEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DayPassListener implements Listener {

    @EventHandler
    public void onAllPlayersSleep(DayPassBySleepEvent eve) {
        PowerSystem.performOperation();
    }

}
