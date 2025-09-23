package me.nurio.imperial.core.chat;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncChatEvent eve) {
        eve.setCancelled(true);

        // Send message thru message send attempt
        MessageSendAttempt attempt = new MessageSendAttempt(eve.getPlayer(), eve.message());
        Bukkit.getOnlinePlayers().forEach(attempt::send);

        String plainMessage = PlainTextComponentSerializer.plainText().serialize(eve.message());
        Bukkit.getLogger().info(
                String.format("%s : '%s'", eve.getPlayer().getName(), plainMessage)
        );
    }


}
