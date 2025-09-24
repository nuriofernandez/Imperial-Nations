package me.nurio.imperial.core.power;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BlindEffect {

    public static void applyEffectsToAll() {
        int durationTicks = 20;
        int amplifier = 10;

        // Create the PotionEffect objects
        PotionEffect blindness = new PotionEffect(
                PotionEffectType.BLINDNESS,
                durationTicks,
                amplifier,
                false, // ambient (particles)
                true   // showIcon
        );

        PotionEffect slowness = new PotionEffect(
                PotionEffectType.SLOWNESS,
                durationTicks,
                amplifier,
                false, // ambient (particles)
                true   // showIcon
        );


        // Loop through all online players and apply the effects
        sendLoading();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.addPotionEffect(blindness);
            player.addPotionEffect(slowness);
        }
    }

    /**
     * Sends a "Loading..." title message to all online players.
     */
    public static void sendLoading() {
        Component mainTitle = Component.text("Loading...", NamedTextColor.RED);
        Component subtitle = Component.text("Recalculating empire's power...", NamedTextColor.GRAY);

        Title title = Title.title(mainTitle, subtitle);
        Audience.audience(Bukkit.getOnlinePlayers()).showTitle(title);
    }

}