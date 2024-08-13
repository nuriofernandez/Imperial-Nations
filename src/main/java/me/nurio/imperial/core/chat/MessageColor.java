package me.nurio.imperial.core.chat;
import me.nurio.imperial.core.organizations.Organization;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

public class MessageColor {

    // Outsider
    private static TextColor outsider = TextColor.color(40, 40, 40);

    // Location based
    private static TextColor deepstone = TextColor.color(85, 85, 85);
    private static TextColor wilderness = TextColor.color(141, 141, 141);

    // Organiation based
    private static TextColor sameOrganization = TextColor.color(205, 255, 220);
    private static TextColor differentOrganization = TextColor.color(255, 255, 255);

    public static TextColor getMessageColor(
        Player sender, Player receiver,
        Organization senderOrganization, Organization senderLocationOrganization,
        Organization receiverOrganization, Organization receiverLocationOrganization
    ) {
        // If deepstone
        if (sender.getLocation().getBlockY() <= 20) {
            return deepstone;
        }

        // If outsider
        if (senderOrganization == null) {
            return outsider;
        }

        // If wilderness
        if (senderLocationOrganization == null) {
            return wilderness;
        }

        // If same organization
        if (senderLocationOrganization == receiverLocationOrganization) {
            return sameOrganization;
        }

        // Default case (different org)
        return differentOrganization;
    }

}
