package me.nurio.imperial.core.organizations;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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

    public List<Organization> fromPlayer(Player player) {
        return organizations.stream()
            .filter(organization -> organization.isMember(player))
            .toList();
    }

}
