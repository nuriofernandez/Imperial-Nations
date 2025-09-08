package me.nurio.imperial.core.power.config.disk;

import me.nurio.bukkit.configuration.files.GrechConfig;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.power.config.BlockPower;
import me.nurio.imperial.core.power.config.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Collection;
import java.util.stream.Collectors;

public class BlockPowerLoader {

    public static void loadAll() {
        Bukkit.getLogger().info("Loading block power config: power-config.yml");

        GrechConfig config = new GrechConfig(Imperial.getPlugin(), "power-config");

        config.getConfig().getKeys(false).stream()
                .map(it -> parseKey(config, it))
                .forEach(blockPower -> {
                    Blocks.blocksMap.put(blockPower.getMaterial(), blockPower);
                });
    }

    public static BlockPower parseKey(GrechConfig config, String key) {
        Bukkit.getLogger().info("power-config.yml > Reading '"+key+"'");

        return new BlockPower(
                Material.getMaterial(
                        key
                ),
                (float) config.getConfig().getDouble(key+".underground"),
                (float) config.getConfig().getDouble(key+".overworld"),
                (float) config.getConfig().getDouble(key+".sky")
        );
    }

}
