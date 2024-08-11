package me.nurio.imperial.core.welcome.move;

import me.nurio.minecraft.worldareas.areas.WorldArea;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

public class AreaMoveMessages {

    static void sendWelcome(Player player, WorldArea area) {
        Component mainTitle = Component.text(area.getName(), NamedTextColor.GOLD);
        Component subtitle = Component.text("You are in a controlled territory", NamedTextColor.WHITE);

        Title title = Title.title(mainTitle, subtitle);
        Audience.audience(player).showTitle(title);
    }

    static void sendGoodbye(Player player, WorldArea area) {
        Component mainTitle = Component.text(area.getName(), NamedTextColor.GOLD);
        Component subtitle = Component.text("You are in a wilderness territory", NamedTextColor.RED);

        Title title = Title.title(mainTitle, subtitle);
        Audience.audience(player).showTitle(title);
    }

}
