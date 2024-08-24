package me.nurio.imperial.core.areas;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InitialClaimListener implements Listener {

    private final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlaceBanner(PlayerInteractEvent eve) {
        if (eve.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (eve.getClickedBlock() == null) {
            return;
        }

        Player player = eve.getPlayer();
        Block block = eve.getClickedBlock();

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        Material material = itemInMainHand.getType();

        // First claim should be done with a sign
        if (material != Material.WHITE_BANNER) {
            return;
        }

        // If player is an outsider
        Organization organization = organizationFactory.fromPlayer(player);
        if (organization == null) {
            player.sendMessage(Component.text("You don't belong to any organization."));
            return;
        }

        // If the organization has already any area, then ignore
        if (!organization.getWorldArea().getAreas().isEmpty()) {
            return;
        }

        Location location = block.getLocation();

        // If terrain already belongs to someone
        List<Organization> organizationsAtLoc = organizationFactory.fromLocation(location);
        if (!organizationsAtLoc.isEmpty()) {
            player.sendMessage(Component.text("This place belongs to another state."));
            return;
        }

        // If there is any other org close by
        if (OrganizationDistance.isCloseToAnyOtherOrganization(organization, location)) {
            player.sendMessage(Component.text("You are too close to another state."));
            return;
        }

        // Execute claim
        ClaimManager.claim(location, material, organization);
        eve.setCancelled(false);

        // Send a lightning!
        location.getWorld().strikeLightningEffect(location);
    }

}
