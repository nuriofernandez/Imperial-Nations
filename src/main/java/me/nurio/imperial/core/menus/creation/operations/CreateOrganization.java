package me.nurio.imperial.core.menus.creation.operations;

import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.areas.ClaimManager;
import me.nurio.imperial.core.menus.creation.OrganizationCreatedMessage;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import me.nurio.imperial.core.organizations.disk.OrganizationSaver;
import me.nurio.minecraft.worldareas.GrechAreas;
import me.nurio.minecraft.worldareas.areas.WorldArea;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateOrganization {

    private static final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    public static void createOrganization(Player player, Location location, String name) {
        Organization currentOrganization = organizationFactory.fromPlayer(player);
        if (currentOrganization != null) {
            Bukkit.getLogger().warning("Tried to create an empire being on one already.");
            return;
        }

        // Update the empire name
        List<OfflinePlayer> members = new ArrayList<>();
        members.add(player);

        // Create world area
        WorldArea worldArea = new WorldArea(name, UUID.randomUUID(), new ArrayList<>());
        GrechAreas.getWorldAreaFactory().addWorldArea(worldArea);
        worldArea.save();

        Organization newOrganization = new Organization(UUID.randomUUID(),name, members, worldArea, 0);
        OrganizationSaver.save(newOrganization);

        organizationFactory.addOrganization(newOrganization);

        // Execute claim
        ClaimManager.claim(location, Material.WHITE_BANNER, newOrganization);

        // Send a lightning!
        location.getWorld().strikeLightningEffect(location);

        // Send a message about it to everyone
        Component message = OrganizationCreatedMessage.build(player, newOrganization);
        Audience.audience(Bukkit.getOnlinePlayers()).sendMessage(message);
    }
}
