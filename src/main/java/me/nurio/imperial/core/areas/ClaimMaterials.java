package me.nurio.imperial.core.areas;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class ClaimMaterials {

    private static final Map<Material, Integer> materials = new HashMap<>() {{
        put(Material.WHITE_BED, 50); // Default bed

        put(Material.WHITE_BANNER, 40); // Initial claiming item

        put(Material.ORANGE_BED, 60);
        put(Material.MAGENTA_BED, 60);
        put(Material.LIGHT_BLUE_BED, 60);
        put(Material.YELLOW_BED, 60);
        put(Material.LIME_BED, 60);
        put(Material.PINK_BED, 60);
        put(Material.GRAY_BED, 60);
        put(Material.LIGHT_GRAY_BED, 60);
        put(Material.CYAN_BED, 60);
        put(Material.PURPLE_BED, 60);
        put(Material.BLUE_BED, 60);
        put(Material.BROWN_BED, 60);
        put(Material.GREEN_BED, 60);
        put(Material.RED_BED, 60);
        put(Material.BLACK_BED, 60);
    }};

    public static int claimingMaterialPower(Material material) {
        return materials.getOrDefault(material, 0);
    }

    public static boolean isClaimingMaterial(Material material) {
        return claimingMaterialPower(material) > 0;
    }
}
