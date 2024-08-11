package me.nurio.imperial.core.areas;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
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

        // Execute claim
        ClaimManager.claim(location, material, organization);
    }

}
