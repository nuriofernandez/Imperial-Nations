package me.nurio.imperial.core.organizations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.nurio.imperial.core.organizations.extensions.OnlineMembers;
import me.nurio.imperial.core.organizations.extensions.PlayerIsMember;
import me.nurio.minecraft.worldareas.areas.WorldArea;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class Organization implements PlayerIsMember, OnlineMembers {

    @Getter @NotNull private UUID uuid;
    @Getter @NotNull private String name;

    @Getter @NotNull private List<OfflinePlayer> members;

    @Getter @NotNull private WorldArea worldArea;

    @Getter @NotNull private int power;

}
