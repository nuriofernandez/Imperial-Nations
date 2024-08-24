package me.nurio.imperial.core.chat;

import lombok.Getter;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class MessageSendAttempt {

    private final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    @Getter
    private Player sender;

    private Component message;

    @Getter
    private Organization senderOrganization;

    @Getter
    private Organization senderLocationOrganization;

    public MessageSendAttempt(Player sender, Component message) {
        this.sender = sender;
        this.message = message;

        Organization playerOrganization = organizationFactory.fromPlayer(sender.getPlayer());
        if (playerOrganization != null) {
            senderOrganization = playerOrganization;
        }

        List<Organization> locationOrganization = organizationFactory.fromLocation(sender.getLocation());
        if (!locationOrganization.isEmpty()) {
            senderLocationOrganization = locationOrganization.getFirst();
        }
    }

    public void send(Player receiver) {
        Organization receiverOrganization = organizationFactory.fromPlayer(receiver);

        Organization receiverLocationOrganization = null;
        List<Organization> locationOrganization = organizationFactory.fromLocation(receiver.getLocation());
        if (!locationOrganization.isEmpty()) {
            receiverLocationOrganization = locationOrganization.getFirst();
        }

        receiver.sendMessage(
            MessagePrefix.messagePrefix(sender)
                .appendSpace()
                .append(message.color(MessageColor.getMessageColor(
                    sender, receiver,
                    senderOrganization, senderLocationOrganization,
                    receiverOrganization, receiverLocationOrganization
                )))
        );

    }

}
