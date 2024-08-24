package me.nurio.imperial.core.menus.playertoplayer.operations.acceptjoinrequest.joinrequest;

import me.nurio.imperial.core.organizations.Organization;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class PlayerJoinedMessage {

    private static final TextColor outsider = TextColor.color(255,85,85);

    private static TextColor gold = NamedTextColor.GOLD;
    private static TextColor white = TextColor.color(255, 255, 255);

    public static Component build(Organization organization, Player player) {
        return Component.text(player.getName()).color(outsider)
            .appendSpace()
            .append(Component.text("is now a member of").color(white))
            .appendSpace()
            .append(Component.text(organization.getName()).color(gold))
            .append(Component.text("."));
    }

}
