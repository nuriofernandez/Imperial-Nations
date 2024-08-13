package me.nurio.imperial.core.chat;

import io.papermc.paper.event.player.AsyncChatEvent;
import lombok.RequiredArgsConstructor;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class ChatListener implements Listener {

    @NotNull private OrganizationFactory organizationFactory;

    @EventHandler
    public void onChat(AsyncChatEvent eve) {
        eve.setCancelled(true);

        // Send message thru message send attempt
        MessageSendAttempt attempt = new MessageSendAttempt(eve.getPlayer(), eve.message());
        Bukkit.getOnlinePlayers().forEach(attempt::send);
    }


}
