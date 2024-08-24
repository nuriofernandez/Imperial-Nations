package me.nurio.imperial.core.menus.playertoplayer.operations.joinrequest;

import me.nurio.imperial.core.organizations.Organization;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RequestJoinOrganization {

    private static Map<UUID, OrganizationJoinRequest> requestMap = new HashMap<>();

    private static void cleanMap() {
        for (Map.Entry<UUID, OrganizationJoinRequest> entry : requestMap.entrySet()) {
            if (entry.getValue().isExpired()) {
                removeRequest(entry.getKey());
            }
        }
    }

    public static void removeRequest(UUID uuid) {
        requestMap.remove(uuid);
    }

    public static boolean hasRequest(Player player, Organization organization){
        // Make sure expired request are no longer there
        cleanMap();

        // Check it
        OrganizationJoinRequest organizationJoinRequest = requestMap.get(player.getUniqueId());
        if (organizationJoinRequest == null) {
            return false;
        }

        // If request matches with organization, then is valid
        return organizationJoinRequest.targetOrganization() == organization;
    }

    public static void requestToJoin(Player player, Organization organization) {
        // Make sure expired request are no longer there
        cleanMap();

        boolean isAlreadyRequested = hasRequest(player, organization);
        if (isAlreadyRequested) {
            requestMap.remove(player.getUniqueId());
            player.sendMessage("Cancelled previous join request.");
        }

        requestMap.put(player.getUniqueId(), new OrganizationJoinRequest(
            player.getUniqueId(),
            organization,
            System.currentTimeMillis()
        ));

        // Send messages
        player.sendMessage("Requested to join!");
        organization.getOnlineMembers().forEach(member -> {
            member.sendMessage(PlayerRequestedToJoinMessage.build(organization, player));
        });
    }

}
