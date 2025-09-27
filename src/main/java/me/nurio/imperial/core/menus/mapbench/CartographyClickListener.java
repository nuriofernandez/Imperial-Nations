package me.nurio.imperial.core.menus.mapbench;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.input.TextDialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.menus.mapbench.operations.RenameOrganization;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

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
        RenameOrganization.rename(player, organization);
    }

}
