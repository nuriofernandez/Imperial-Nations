package me.nurio.imperial.core.chat;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class OrganizationPrefix {

    private static Component outsiderOrganization = Component.text("[")
        .append(Component.text("Outsider").color(TextColor.color(255, 85, 85)))
        .append(Component.text("]"));

    private static Component getOrganization(Organization organization) {
        return Component.text("[")
            .append(PowerPrefix.getPower(organization))
            .appendSpace()
            .append(Component.text(organization.getName()).color(TextColor.color(255, 130, 0)))
            .append(Component.text("]"));
    }

    public static Component getComponent(Player player) {
        Organization organization = Imperial.getOrganizationFactory().fromPlayer(player);
        if (organization == null) {
            return outsiderOrganization;
        }

        return getOrganization(organization);
    }

}
