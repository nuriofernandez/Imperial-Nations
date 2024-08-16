package me.nurio.imperial.core.worldborder;

import me.nurio.imperial.core.Imperial;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class DynamicWorldBorder {

    public static void start() {
        int organizationsCount = Imperial.getOrganizationFactory().getOrganizations().size();
        double worldSize = 400 + (200 * organizationsCount);

        for (World world : Bukkit.getWorlds()) {
            if (world.getEnvironment().equals(World.Environment.NETHER)) {
                world.getWorldBorder().setSize(worldSize / 8);
            }
            if (world.getEnvironment().equals(World.Environment.NORMAL)) {
                world.getWorldBorder().setSize(worldSize);
            }
        }
    }

}
