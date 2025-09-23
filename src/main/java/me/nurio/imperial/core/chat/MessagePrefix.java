package me.nurio.imperial.core.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class MessagePrefix {

    private static Component suffix = Component.text(":").color(TextColor.color(255, 130, 0));

    public static Component messagePrefix(Player sender) {
        return OrganizationPrefix.getComponent(sender)
            .appendSpace()
            .append(Component.text(String.format(
                    "(☀%d)", playedDays(sender)
            )).color(NamedTextColor.GREEN))
            .appendSpace()
            .append(sender.displayName())
            .appendSpace()
            .append(suffix);
    }

    private static int playedDays(Player sender) {
        int time = sender.getStatistic(Statistic.PLAY_ONE_MINUTE);
        return time / (20 * 60 * 60); // day in ticks
    }

}
