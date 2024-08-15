package me.nurio.imperial.core.power;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class PowerCalculator {

    private static final float UNDERGROUND_MULTIPLIER = 0.5F;
    private static final float SKY_MULTIPLIER = 1.5F;

    public static Map<Material, Float> dynamicPowerBlocksMap = new HashMap<>() {{
        put(Material.COBBLESTONE, 1F);

        // TODO add more wood types
        put(Material.OAK_PLANKS, 3F);
        put(Material.DARK_OAK_PLANKS, 3F);
        put(Material.ACACIA_PLANKS, 3F);
    }};

    public static Map<Material, Float> staticPowerBlocksMap = new HashMap<>() {{
        put(Material.RAIL, 10F);
        put(Material.CHEST, 100F);
        put(Material.WHEAT, 10F);

        // TODO Add more wood types
        put(Material.OAK_SIGN, 10F);
        put(Material.DARK_OAK_SIGN, 10F);
        put(Material.ACACIA_SIGN, 10F);
    }};

    public static int powerFromLocation(Location location) {
        Material material = location.getBlock().getType();

        float dynamicPower = dynamicPowerBlocksMap.getOrDefault(material, 0F);
        float staticPower = staticPowerBlocksMap.getOrDefault(material, 0F);

        int y = location.getBlockY();

        // Underground
        if (y <= 60) {
            return Math.round(
                (dynamicPower * UNDERGROUND_MULTIPLIER) + staticPower
            );
        }

        // Overworld
        if (y <= 100) {
            return Math.round(
                dynamicPower + staticPower
            );
        }

        // Sky
        return Math.round(
            (dynamicPower * SKY_MULTIPLIER) + staticPower
        );

    }

}
