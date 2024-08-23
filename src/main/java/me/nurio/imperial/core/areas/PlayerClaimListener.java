package me.nurio.imperial.core.areas;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class PlayerClaimListener implements Listener {

    private final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlaceSpecialBlock(BlockPlaceEvent eve) {
        Material material = eve.getBlock().getType();

        if (!ClaimMaterials.isClaimingMaterial(material)) {
            return;
        }

        // If player is an outsider
        List<Organization> organizations = organizationFactory.fromPlayer(eve.getPlayer());
        if (organizations.isEmpty()) {
            return;
        }

        // In case the user belongs to many organizations, do nothing.
        if (organizations.size() >= 2) {
            return;
        }

        Organization organization = organizations.getFirst();
        Location location = eve.getBlock().getLocation();

        // If terrain is wilderness or belongs to a different organization
        List<Organization> organizationsAtLoc = organizationFactory.fromLocation(location);
        if (organizationsAtLoc.isEmpty()) {
            return; // wilderness
        }
        if (organizationsAtLoc.getFirst() != organization) {
            return; // is not the user's organization
        }

        // Prevent creating two areas in the same place
        if (AreaCenterUtil.isCloseToAnyCenter(organization, location)) {
            return;
        }

        // If there is any other org close by
        if (OrganizationDistance.isCloseToAnyOtherOrganization(organization, location)) {
            return;
        }

        // Execute claim
        ClaimManager.claim(location, material, organization);
    }

}
