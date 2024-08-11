package me.nurio.imperial.core.areas;


import me.nurio.imperial.core.organizations.Organization;
import me.nurio.minecraft.worldareas.areas.BlockArea;
import me.nurio.minecraft.worldareas.areas.WorldArea;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class ClaimManager {

    public static void claim(
        Location location, Material material, Organization organization
    ) {
        BlockArea blockArea = createBlockArea(location, material);
        WorldArea worldArea = organization.getWorldArea();
        worldArea.getAreas().add(blockArea);
        worldArea.save();
    }

    private static BlockArea createBlockArea(Location location, Material material) {
        int range = ClaimMaterials.claimingMaterialPower(material);

        World world = location.getWorld();
        int blockX = location.getBlockX();
        int blockZ = location.getBlockZ();

        Location start = new Location(
            world,
            blockX + range,
            20,
            blockZ + range
        );

        Location end = new Location(
            world,
            blockX - range,
            world.getMaxHeight(),
            blockZ - range
        );

        return new BlockArea(start, end);
    }

}
