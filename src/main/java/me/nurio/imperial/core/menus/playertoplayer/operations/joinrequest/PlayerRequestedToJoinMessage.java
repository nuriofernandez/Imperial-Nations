package me.nurio.imperial.core.menus.playertoplayer.operations.joinrequest;

import me.nurio.imperial.core.organizations.Organization;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class PlayerRequestedToJoinMessage {

    private static final TextColor outsider = TextColor.color(255,85,85);

    private static TextColor gold = NamedTextColor.GOLD;
    private static TextColor white = TextColor.color(255, 255, 255);
    private static TextColor comment = TextColor.color(129, 129, 129);

    public static Component build(Organization organization, Player player) {
        return Component.text(player.getName()).color(outsider)
            .appendSpace()
            .append(Component.text("requested to join to ").color(white))
            .appendSpace()
            .append(Component.text(organization.getName()).color(gold))
            .append(Component.text("."))
            .appendNewline()
            .append(Component.text("sneak and click this player to open the organization menu").color(comment));
    }

}
