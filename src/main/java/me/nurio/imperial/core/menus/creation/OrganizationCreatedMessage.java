package me.nurio.imperial.core.menus.creation;

import me.nurio.imperial.core.organizations.Organization;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class OrganizationCreatedMessage {

    public static Component build(Player player, Organization organization) {
        return Component.text(player.getName()).color(NamedTextColor.YELLOW)
            .appendSpace()
            .append(Component.text("just created a new empire, glorious").color(NamedTextColor.WHITE))
            .appendSpace()
            .append(Component.text(organization.getName()).color(NamedTextColor.GOLD))
            .append(Component.text(" empire!").color(NamedTextColor.WHITE));
    }

}
