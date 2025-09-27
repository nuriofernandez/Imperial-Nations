package me.nurio.imperial.core.mobprotection;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;

public class MobDeathListener implements Listener {

    private final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    @EventHandler
    public void onSpawn(EntityDeathEvent event) {
        DamageSource damageSource = event.getDamageSource();
        Entity causingEntity = damageSource.getCausingEntity();
        if (!(causingEntity instanceof Player player)) return;
        String entityName = event.getEntity().getType().name();

        Organization playerOrganization = organizationFactory.fromPlayer(player);

        UUID uniqueId = event.getEntity().getUniqueId();
        Organization mobOrganization = MobOrganizationStorage.mobOrganization(uniqueId);

        TextComponent didKillA = Component.text("[").color(NamedTextColor.WHITE)
                .append(Component.text(playerOrganization.getName())).color(NamedTextColor.GOLD)
                .append(Component.text("]").color(NamedTextColor.WHITE))
                .appendSpace()
                .append(Component.text(player.getName()).color(NamedTextColor.WHITE))
                .appendSpace()
                .append(Component.text("killed").color(NamedTextColor.WHITE))
                .appendSpace()
                .append(Component.text(entityName.toLowerCase()).color(NamedTextColor.RED));

        // If org, add organization to the end
        if (mobOrganization != null) {
            didKillA = didKillA.append(Component.text("[").color(NamedTextColor.WHITE))
                    .append(Component.text(mobOrganization.getName())).color(NamedTextColor.GOLD)
                    .append(Component.text("]").color(NamedTextColor.WHITE));
        }

        // Add entity name
        didKillA = didKillA.append(Component.text(entityName.toLowerCase()).color(NamedTextColor.RED));

        // Send the message
        Audience.audience(Bukkit.getOnlinePlayers()).sendMessage(didKillA);
    }

}
