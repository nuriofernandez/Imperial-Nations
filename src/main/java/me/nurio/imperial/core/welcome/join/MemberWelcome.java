package me.nurio.imperial.core.welcome.join;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class MemberWelcome {

    static void sendWelcome(Player player) {
        Organization organization = Imperial.getOrganizationFactory().fromPlayer(player);

        // Do not send welcome to outsiders
        if (organization == null) {
            return;
        }

        Bukkit.getScheduler().runTaskLater(Imperial.getPlugin(), () -> {
            sendOrganizationWelcome(organization, player);
        }, 20);
    }

    static void sendOrganizationWelcome(Organization organization, Player player) {
        Component mainTitle = Component.text(organization.getName(), NamedTextColor.GOLD);
        Component subtitle = Component.text("Welcome, together we will shine!", NamedTextColor.GREEN);

        Title title = Title.title(mainTitle, subtitle);
        Audience.audience(player).showTitle(title);
    }

}
