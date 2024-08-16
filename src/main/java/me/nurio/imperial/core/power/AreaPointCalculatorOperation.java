package me.nurio.imperial.core.power;

import lombok.RequiredArgsConstructor;
import me.nurio.minecraft.worldareas.areas.BlockArea;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class AreaPointCalculatorOperation {

    private final BlockArea area;

    public List<Location> getSpecialBlocks() {
        World world = area.getStart().getWorld();

        List<Location> blocks = new ArrayList<>();
        for (int y : getAxisRange(Location::getBlockY)) {
            for (int x : getAxisRange(Location::getBlockX)) {
                for (int z : getAxisRange(Location::getBlockZ)) {
                    Location location = new Location(world, x, y, z);

                    if (PowerCalculator.isPowerBlock(location)) {
                        blocks.add(location);
                    }
                }
            }
        }

        return blocks;
    }

    private List<Integer> getAxisRange(Function<Location, Integer> axis) {
        Location start = area.getStart();
        Location end = area.getEnd();
        int a = axis.apply(start);
        int b = axis.apply(end);
        return IntStream.range(Math.min(a, b), Math.max(a, b) + 1)
            .boxed().collect(Collectors.toList());
    }

}