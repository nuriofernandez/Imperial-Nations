package me.nurio.imperial.core.power.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
public class BlockPower {

    Material material;

    float undergroundPower;
    float overworldPower;
    float skyPower;

}
