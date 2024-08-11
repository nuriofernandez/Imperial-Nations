package me.nurio.imperial.core.welcome;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

public class OutsiderWelcome {

    static void sendWelcome(Player player) {
        Component mainTitle = Component.text("Welcome outsider", NamedTextColor.WHITE);
        Component subtitle = Component.text("You don't belong to any state", NamedTextColor.RED);

        Title title = Title.title(mainTitle, subtitle);
        Audience.audience(player).showTitle(title);
    }

}
