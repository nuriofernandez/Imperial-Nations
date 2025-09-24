package me.nurio.imperial.core.mobprotection;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import me.nurio.imperial.core.protection.PermissionManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.List;
import java.util.UUID;

public class MobSpawnListener implements Listener {

    private final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();
    private final PermissionManager permissionManager = new PermissionManager(organizationFactory);

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        UUID uniqueId = event.getEntity().getUniqueId();
        List<Organization> organizationAtLocation = organizationFactory.fromLocation(event.getLocation());

        if (organizationAtLocation.isEmpty()) {
            return;
        }

        MobOrganizationStorage.linkMobToOrganization(uniqueId, organizationAtLocation.getFirst());
    }

    @EventHandler
    public void useEntity(PlayerInteractEntityEvent eve) {
        if (eve.getRightClicked() instanceof Player) return;

        Player player =  eve.getPlayer();
        if (!player.isSneaking()) return;

        Entity entity = eve.getRightClicked();

        Organization mobOrganization = MobOrganizationStorage.mobOrganization(entity);

        if (mobOrganization == null) return;

        player.sendMessage(
                Component.text("This mob belongs to ").color(TextColor.color(39, 245, 183))
                        .append(Component.text(mobOrganization.getName()).color(NamedTextColor.GOLD))
        );
    }

}
