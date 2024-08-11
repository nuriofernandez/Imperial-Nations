package me.nurio.imperial.core.organizations.extensions;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public interface PlayerIsMember {

    List<OfflinePlayer> getMembers();

    default boolean isMember(Player player) {
        for (OfflinePlayer member : getMembers()) {
            if (member.getUniqueId().equals(player.getUniqueId())) {
                return true;
            }
        }

        return false;
    }

}
