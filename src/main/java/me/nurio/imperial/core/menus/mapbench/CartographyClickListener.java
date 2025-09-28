package me.nurio.imperial.core.menus.mapbench;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.menus.mapbench.dialog.OrganizationSettingsDialog;
import me.nurio.imperial.core.menus.mapbench.dialog.RenameOrganizationDialog;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class CartographyClickListener implements Listener {

    private final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    @EventHandler(priority = EventPriority.MONITOR)
    public void sneakingClickToBench(PlayerInteractEvent eve) {
        Player player = eve.getPlayer();
        if (!player.isSneaking()) {
            return;
        }

        if (eve.getClickedBlock() == null) {
            return;
        }

        // Not cartography table
        if (!eve.getClickedBlock().getType().equals(Material.CARTOGRAPHY_TABLE)){
            return;
        }

        eve.setCancelled(true);

        Organization organization = organizationFactory.fromPlayer(player);
        if (organization == null){
            player.sendMessage("You don't belong to any empire!");
            return;
        }

        // Execute rename function
        OrganizationSettingsDialog.show(player);
    }

}
