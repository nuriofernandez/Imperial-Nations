package me.nurio.imperial.core.protection;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class OutsiderMessager {

    private static final TextColor messageColor = TextColor.color(255, 255, 255);
    private static final TextColor outsiderColor = TextColor.color(255,85,85);

    private static final Component outsiderMessage = (
        Component.text("Outsider").color(outsiderColor)
            .append(Component.text(", become a member of a state to interact here.").color(messageColor))
            .append(Component.newline())
            .append(Component.text("Outsiders can only interact with the world below height 20").color(messageColor))
    );

    static void sendOutsiderMessage(Player player) {
        player.sendMessage(outsiderMessage);
    }

}
