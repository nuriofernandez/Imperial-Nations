package me.nurio.imperial.core.mobprotection;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import me.nurio.imperial.core.protection.PermissionManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class MobPermissions {

    private static final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();
    private static final PermissionManager permissionManager = new PermissionManager(organizationFactory);

    public static boolean canUseMob(Player player, Entity entity) {
        boolean hasLocationPerms = permissionManager.hasPermissionsAt(player, player.getLocation());
        if (hasLocationPerms) {
            return true;
        }

        Organization mobOrganization = MobOrganizationStorage.mobOrganization(entity);
        Organization playerOrganization = organizationFactory.fromPlayer(player);

        if (playerOrganization == null) return false;
        if (mobOrganization == null) return true;

        if (mobOrganization.equals(playerOrganization)) {
            return true;
        }

        List<Organization> mobLocationOrganizations = organizationFactory.fromLocation(entity.getLocation());
        if (mobLocationOrganizations.isEmpty()) {
            return true;
        }

        if (!mobOrganization.equals(mobLocationOrganizations.getFirst())) {
            return true;
        }

        return false;
    }

}
