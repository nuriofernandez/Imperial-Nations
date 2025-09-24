package me.nurio.imperial.internal.sleepdetection;

import me.nurio.imperial.core.Imperial;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

public class DetectPlayersSleepingTask {

    private static boolean earlyMorning = false;
    private static boolean everyoneSleeping = false;

    public static void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Imperial.getPlugin(), () -> {
            boolean previousTickEarlyMorning = earlyMorning;
            boolean previousTickEveryoneSleeping = everyoneSleeping;

            earlyMorning = isEarlyMorning();
            everyoneSleeping = allRequiredPlayersAreSleeping();

            // Previously players where not sleeping and was not night, abort.
            if (previousTickEarlyMorning) return;
            if (!previousTickEveryoneSleeping) return;

            // Now should be morning and everyone awake.
            if (!earlyMorning) return;
            if (everyoneSleeping) return;

            // So, players just wake up!
            Bukkit.getPluginManager().callEvent(new DayPassBySleepEvent());
        }, 40, 40);
    }

    public static boolean isEarlyMorning() {
        World world = Bukkit.getWorlds().stream()
            .filter(w -> w.getEnvironment().equals(World.Environment.NORMAL))
            .findFirst().orElse(null);

        if (world == null) {
            return false;
        }

        return world.getTime() < 100;
    }

    public static boolean allRequiredPlayersAreSleeping() {
        int requiredPeopleSleeping = (Bukkit.getOnlinePlayers().size()/2); // 50% gamerule applied
        int peopleSleeping = Math.toIntExact(Bukkit.getOnlinePlayers().stream().filter(LivingEntity::isSleeping).count());
        return peopleSleeping >= requiredPeopleSleeping;
    }

}
