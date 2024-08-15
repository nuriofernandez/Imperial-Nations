package me.nurio.imperial.core.areas;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.minecraft.worldareas.areas.BlockArea;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class PlayerClaimListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlaceSpecialBlock(BlockPlaceEvent eve) {
        Material material = eve.getBlock().getType();

        if (!ClaimMaterials.isClaimingMaterial(material)) {
            return;
        }

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
        Location location = eve.getBlock().getLocation();

        // If terrain already belongs to someone
        List<Organization> organizationsAtLoc = Imperial.getOrganizationFactory().fromLocation(location);
        if (!organizationsAtLoc.isEmpty()) {
            return;
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
