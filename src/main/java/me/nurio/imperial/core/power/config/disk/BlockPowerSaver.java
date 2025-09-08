package me.nurio.imperial.core.power.config.disk;

import me.nurio.bukkit.configuration.files.GrechConfig;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.power.config.BlockPower;
import me.nurio.imperial.core.power.config.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class BlockPowerSaver {

    public static void saveAll() {
        Bukkit.getLogger().info("Saving block power config: power-config.yml");

        GrechConfig config = new GrechConfig(Imperial.getPlugin(), "power-config");

        Blocks.blocksMap.values().stream().forEach(block -> {
            saveMaterial(config, block);
        });

        // Save config
        config.save();
    }

    public static void saveMaterial(GrechConfig config, BlockPower blockPower) {
        String material = blockPower.getMaterial().name();
        Bukkit.getLogger().info("power-config.yml > Saving '" + material + "'");

        config.set(material + ".underground", blockPower.getUndergroundPower());
        config.set(material + ".overworld", blockPower.getOverworldPower());
        config.set(material + ".sky", blockPower.getSkyPower());
    }

}
