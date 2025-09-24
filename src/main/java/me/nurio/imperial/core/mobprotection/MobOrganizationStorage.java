package me.nurio.imperial.core.mobprotection;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MobOrganizationStorage {

    private static final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    public static Map<UUID, Organization> mobOrganizations = new HashMap<>();

    public static Organization mobOrganization(UUID uuid) {
        return mobOrganizations.get(uuid);
    }

    public static Organization mobOrganization(Entity entity) {
        Organization organization = mobOrganization(entity.getUniqueId());
        if (organization == null) {
            Location location = entity.getLocation();
            List<Organization> organizations = organizationFactory.fromLocation(location);
            if (organizations.isEmpty()) return null;
            Organization locationOrganization = organizations.getFirst();
            linkMobToOrganization(entity.getUniqueId(), locationOrganization);
            return locationOrganization;
        }
        return organization;
    }

    public static void linkMobToOrganization(UUID uuid, Organization organization) {
        mobOrganizations.putIfAbsent(uuid, organization);
    }


}
