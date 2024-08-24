package me.nurio.imperial.core.menus.playertoplayer.operations.acceptjoinrequest.joinrequest;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.menus.playertoplayer.operations.joinrequest.RequestJoinOrganization;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import me.nurio.imperial.core.organizations.disk.OrganizationSaver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AcceptJoinOrganization {

    private static final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    public static void acceptJoinRequest(Player player, Player requestPlayer){
        Organization organization = organizationFactory.fromPlayer(player);

        if (!RequestJoinOrganization.hasRequest(requestPlayer, organization)) {
            return;
        }

        RequestJoinOrganization.removeRequest(requestPlayer.getUniqueId());
        organization.getMembers().add(Bukkit.getOfflinePlayer(requestPlayer.getUniqueId()));
        OrganizationSaver.save(organization);

        organization.getOnlineMembers().forEach(member -> {
            member.sendMessage(PlayerJoinedMessage.build(organization, requestPlayer));
        });
    }

}
