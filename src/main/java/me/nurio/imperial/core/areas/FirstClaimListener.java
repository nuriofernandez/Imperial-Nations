package me.nurio.imperial.core.areas;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;

import java.util.List;

public class FirstClaimListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlaceSign(BlockCanBuildEvent eve) {
        Material material = eve.getBlock().getType();

        // First claim should be done with a sign
        if (material != Material.WHITE_BANNER) return;

        // If player is an outsider
        List<Organization> organizations = Imperial.getOrganizationFactory().fromPlayer(eve.getPlayer());
        if (organizations.isEmpty()) {
            return;
        }

        // In case the user belongs to many organizations, do nothing.
        if (organizations.size() >= 2) {
            return;
        }

        Organization organization = organizations.getFirst();

        // If the organization has already any area, then ignore
        if (!organization.getWorldArea().getAreas().isEmpty()) {
            return;
        }

        Location location = eve.getBlock().getLocation();

        // If terrain already belongs to someone
        List<Organization> organizationsAtLoc = Imperial.getOrganizationFactory().fromLocation(location);
        if (!organizationsAtLoc.isEmpty()) {
            return;
        }

        // If there is any other org close by
        if (OrganizationDistance.isCloseToAnyOtherOrganization(organization, location)) {
            return;
        }

        // Execute claim
        ClaimManager.claim(location, material, organization);
        eve.setBuildable(true);

        // Send a lightning!
        location.getWorld().strikeLightningEffect(location);
    }

}
