package me.nurio.imperial.core.protection;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class WildernessMessager {

    private static final TextColor messageColor = TextColor.color(255, 255, 255);
    private static final TextColor wildernessColor = TextColor.color(255,85,85);

    private static final Component wildernessMessage = (
        Component.text("This is an").color(messageColor)
            .appendSpace()
            .append(Component.text("unclaimed territory").color(wildernessColor))
            .append(Component.text(", is not possible to build here.").color(messageColor))
    );

    static void sendMessage(Player player) {
        player.sendMessage(wildernessMessage);
    }

}
