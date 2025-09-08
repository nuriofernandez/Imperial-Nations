package me.nurio.imperial.core.power;

import me.nurio.imperial.core.power.config.BlockPower;
import me.nurio.imperial.core.power.config.Blocks;
import org.bukkit.Location;
import org.bukkit.Material;

public class PowerFromLocation {

    public static boolean has(Location location) {
        return power(location) > 0;
    }

    public static int power(Location location) {
        Material material = location.getBlock().getType();
        int y = location.getBlockY();

        BlockPower blockPower = Blocks.getBlockPower(material);

        // Underground
        if (y <= 60) {
            return (int) blockPower.getUndergroundPower();
        }

        // Overworld
        if (y <= 100) {
            return (int) blockPower.getOverworldPower();
        }

        // Sky
        return (int) blockPower.getSkyPower();
    }

}
