package me.nurio.imperial.core.menus.creation;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.areas.ClaimManager;
import me.nurio.imperial.core.areas.OrganizationDistance;
import me.nurio.imperial.core.menus.creation.operations.CreateOrganizationOperation;
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

        // Not sneaking, don't work
        if (!player.isSneaking()) {
            return;
        }

        // Check if the item is a banner of any color
        if (!material.name().endsWith("_BANNER")) {
            return;
        }

        // If player is not an outsider, stop
        Organization organization = organizationFactory.fromPlayer(player);
        if (organization != null) {
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
        if (OrganizationDistance.isCloseToAnyOtherOrganization(null, location)) {
            player.sendMessage(Component.text("You are too close to another state."));
            OrganizationDistance.reportCloseOrganization(player);
            return;
        }

        eve.setCancelled(true);

        // Execute create organization
        CreateOrganizationOperation.createOrganization(player, block.getLocation());
    }

}
