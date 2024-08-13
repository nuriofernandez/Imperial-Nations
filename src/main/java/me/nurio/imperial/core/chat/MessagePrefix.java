package me.nurio.imperial.core.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class MessagePrefix {

    private static Component suffix = Component.text(":").color(TextColor.color(255, 130, 0));

    public static Component messagePrefix(Player sender) {
        return OrganizationPrefix.getComponent(sender)
            .appendSpace()
            .append(sender.displayName())
            .appendSpace()
            .append(suffix);
    }

}
