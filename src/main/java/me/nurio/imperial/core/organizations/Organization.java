package me.nurio.imperial.core.organizations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.nurio.imperial.core.organizations.disk.OrganizationSaver;
import me.nurio.imperial.core.organizations.extensions.OnlineMembers;
import me.nurio.imperial.core.organizations.extensions.PlayerIsMember;
import me.nurio.minecraft.worldareas.areas.WorldArea;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class Organization implements PlayerIsMember, OnlineMembers {

    @Getter @NotNull private UUID uuid;
    @Getter @NotNull private String name;

    @Getter @NotNull private List<OfflinePlayer> members;

    @Getter @NotNull private WorldArea worldArea;

    @Getter @Setter
    @NotNull private int power;

    public double getStars() {
        double stars = 0;

        double starCost = 40_000;
        double remainingPower = power;

        while (remainingPower > 0) {
            if (remainingPower >= starCost) {
                stars++;
                remainingPower -= starCost;
                starCost = starCost * 1.25;
                continue;
            }

            stars+= remainingPower / starCost;
            remainingPower = 0;
        }

        return stars;
    }

    public void setName(String newName) {
        this.name = newName;
        OrganizationSaver.save(this);
    }

}
