package me.nurio.imperial.core.areas;

import me.nurio.imperial.core.organizations.Organization;
import me.nurio.minecraft.worldareas.areas.BlockArea;
import org.bukkit.Location;

public class AreaCenterUtil {

    public static boolean isCloseToAnyCenter(Organization organization, Location location) {
        for (BlockArea area : organization.getWorldArea().getAreas()) {
            if (isCloseToCenter(area, location)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCloseToCenter(BlockArea area, Location location) {
        return distanceToCenter(area, location) <= 25;
    }

    public static double distanceToCenter(BlockArea area, Location location) {
        Location start = area.getStart();
        Location end = area.getEnd();

        Location center = new Location(
            area.getWorld(),
            (start.getX()+end.getX())/2,
            location.getY(),
            (start.getZ()+end.getZ())/2
        );

        return location.distance(center);
    }

}
