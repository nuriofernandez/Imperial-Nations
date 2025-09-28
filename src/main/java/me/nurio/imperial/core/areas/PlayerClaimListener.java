package me.nurio.imperial.core.areas;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;
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

        // Is not a bed
        BlockData data = eve.getBlock().getBlockData();
        if (!(data instanceof Bed)) {
            return;
        }

        // If player is an outsider
        Player player = eve.getPlayer();
        Organization organization = organizationFactory.fromPlayer(player);
        if (organization == null) {
            return;
        }

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
            Bukkit.getLogger().warning(player.getName() + " >> unable to claim due to distance to center!");
            return;
        }

        // If there is any other org close by
        if (OrganizationDistance.isCloseToAnyOtherOrganization(organization, location)) {
            Bukkit.getLogger().warning(player.getName() + " >> too close to another org!");
            return;
        }

        // Not enough power
        double stars = organization.getStars();
        int currentSize = organization.getWorldArea().getAreas().size();
        if (stars <= (double) currentSize) {
            Bukkit.getLogger().warning(player.getName() + " >> tried to claim but not enough stars");
            return;
        }


        // Execute claim
        ClaimManager.claim(location, material, organization);
    }

}
