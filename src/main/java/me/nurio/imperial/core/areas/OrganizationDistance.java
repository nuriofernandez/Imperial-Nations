package me.nurio.imperial.core.areas;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.organizations.Organization;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class OrganizationDistance {

    /**
     * Since areas will be created from 120x120, radius 60,
     * we set a limit of 300, that will be (distance:250)-(radius:120)=(separation:130)
     */
    private static final double MINIMUM_DISTANCE_TO_ANOTHER_ORGANIZATION = 250D;

    public static boolean isCloseToAnyOtherOrganization(Organization self, Location location) {
        for (Organization organization : Imperial.getOrganizationFactory().getOrganizations()) {
            if (organization == self) continue;

            double distance = distance(location, organization);
            if (distance <= MINIMUM_DISTANCE_TO_ANOTHER_ORGANIZATION) {
                return true;
            }
        }

        return false;
    }

    public static double distance(Location location, Organization organization) {
        return organization.getWorldArea().getAreas().stream()
            .map(area -> AreaCenterUtil.distanceToCenter(area, location))
            .min(Double::compareTo).orElse(-1D);
    }

}
