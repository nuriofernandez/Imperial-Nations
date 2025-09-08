package me.nurio.imperial.core.power.config;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class Blocks {

    public static Map<Material, BlockPower> blocksMap = new HashMap<>();

    public static BlockPower getBlockPower(Material material) {
        if (!blocksMap.containsKey(material)) {
            blocksMap.put(
                    material,
                    new BlockPower(
                            material,
                            0,
                            0,
                            0
                    )
            );
        }
        return blocksMap.get(material);
    }

}
