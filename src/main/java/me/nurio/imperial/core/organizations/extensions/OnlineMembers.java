package me.nurio.imperial.core.organizations.extensions;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public interface OnlineMembers {

    List<OfflinePlayer> getMembers();

    default List<Player> getOnlineMembers() {
       return getMembers().stream()
            .filter(OfflinePlayer::isOnline)
            .map(OfflinePlayer::getPlayer)
            .toList();
    }

}
