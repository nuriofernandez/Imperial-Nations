package me.nurio.imperial.core.menus.mapbench;

import me.nurio.imperial.core.organizations.Organization;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class OrganizationRenamedMessage {

    public static Component build(Player player, Organization organization, String oldOrganizationName) {
        return Component.text(player.getName()).color(NamedTextColor.YELLOW)
            .appendSpace()
            .append(Component.text("renamed").color(NamedTextColor.WHITE))
            .appendSpace()
            .append(Component.text(oldOrganizationName).color(NamedTextColor.RED))
            .appendSpace()
            .append(Component.text("empire to").color(NamedTextColor.WHITE))
            .appendSpace()
            .append(Component.text(organization.getName()).color(NamedTextColor.GOLD))
            .append(Component.text(".").color(NamedTextColor.WHITE));
    }

}
