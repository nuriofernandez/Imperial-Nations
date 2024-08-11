package me.nurio.imperial.core.welcome;

import lombok.RequiredArgsConstructor;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {

    @NotNull private OrganizationFactory organizationFactory;

    @EventHandler
    public void onJoin(PlayerJoinEvent eve) {
        Player player = eve.getPlayer();

        List<Organization> organizations = organizationFactory.fromPlayer(player);

        if (organizations.isEmpty()) {
            OutsiderWelcome.sendWelcome(player);
            return;
        }

        MemberWelcome.sendWelcome(player);
    }

}
