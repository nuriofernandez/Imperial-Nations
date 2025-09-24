package me.nurio.imperial.core.power;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.internal.sleepdetection.DayPassBySleepEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;

public class DayPassListener implements Listener {

    @EventHandler
    public void onAllPlayersSleep(DayPassBySleepEvent eve) {
        BlindEffect.applyEffectsToAll();
        Bukkit.getScheduler().runTaskLater(Imperial.getPlugin(), PowerSystem::performOperation, 5L);
    }

}
