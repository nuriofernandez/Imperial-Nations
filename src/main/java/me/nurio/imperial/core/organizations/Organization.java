package me.nurio.imperial.core.organizations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class Organization {

    @Getter @NotNull private UUID uuid;
    @Getter @NotNull private String name;

    @Getter @NotNull private List<OfflinePlayer> members;

}
