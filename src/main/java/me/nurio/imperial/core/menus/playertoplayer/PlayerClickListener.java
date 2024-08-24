package me.nurio.imperial.core.menus.playertoplayer;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerClickListener implements Listener {

    private final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    @EventHandler
    public void sneakingClickToPlayer(PlayerInteractAtEntityEvent eve) {
        Player player = eve.getPlayer();
        if (!player.isSneaking()) {
            return;
        }

        boolean clickedIsPlayer = eve.getRightClicked() instanceof Player;
        if (!clickedIsPlayer) {
            return;
        }

        Player clickedPlayer = ((Player) eve.getRightClicked());
        Organization playerOrganization = organizationFactory.fromPlayer(player);
        Organization clickedPlayerOrganization = organizationFactory.fromPlayer(clickedPlayer);

        // If player is an outsider and clicked player is a member:
        if (playerOrganization == null && clickedPlayerOrganization != null) {
            // Open the player to player menu
            OutsiderToMemberMenu.openMenu(player, clickedPlayer);
            return;
        }

        // If player is a member and clicks on an outsider:
        if (playerOrganization != null && clickedPlayerOrganization == null) {
            // Open the player to player menu
            MemberToOutsiderMenu.openMenu(player, clickedPlayer);
            return;
        }
    }

}
