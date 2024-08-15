package me.nurio.imperial.core.protection;

import lombok.RequiredArgsConstructor;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@RequiredArgsConstructor
public class PermissionManager {

    @NotNull private OrganizationFactory organizationFactory;

    public boolean hasPermissionsAt(Player player, Location location) {
        // Below height 20 everything is allowed.
        if (location.getBlockY() <= 20) {
            return true;
        }

        // Check organization memberships
        List<Organization> organizations = organizationFactory.fromPlayer(player);

        // Outsiders doesn't have permissions
        if (organizations.isEmpty()) {
            OutsiderMessager.sendOutsiderMessage(player);
            return false;
        }

        // If there is no organization or for some reason, more than one.
        List<Organization> organizationsAtLoc = organizationFactory.fromLocation(location);
        if (organizationsAtLoc.size() != 1) {
            return false;
        }

        // If player belongs to that organization
        Organization locationOrganization = organizationsAtLoc.getFirst();
        return organizations.contains(locationOrganization);
    }

}
