package me.nurio.imperial.core.organizations;

import lombok.Getter;
import me.nurio.imperial.core.Imperial;
import me.nurio.minecraft.worldareas.GrechAreas;
import me.nurio.minecraft.worldareas.areas.WorldArea;
import me.nurio.minecraft.worldareas.areas.WorldAreaFactory;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OrganizationFactory {

    @Getter private List<Organization> organizations = new ArrayList<>();

    /**
     * Registers a new organization to the list of organization.
     *
     * @param organization Organization to register.
     */
    public void addOrganization(@NotNull Organization organization) {
        organizations.add(organization);
    }

    @Nullable
    public Organization fromPlayer(Player player) {
        return organizations.stream()
            .filter(organization -> organization.isMember(player))
            .findFirst().orElse(null);
    }

    public List<Organization> fromLocation(Location location) {
        WorldAreaFactory worldAreaFactory = GrechAreas.getWorldAreaFactory();
        List<WorldArea> worldAreas = worldAreaFactory.fromLocation(location);
        if (worldAreas.isEmpty()) {
            return new ArrayList<>();
        }

        return getOrganizations().stream()
            .filter(organization -> worldAreas.contains(organization.getWorldArea()))
            .toList();
    }

}
