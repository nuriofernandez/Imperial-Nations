package me.nurio.imperial.core.menus.playertoplayer.operations.joinrequest;

import me.nurio.imperial.core.organizations.Organization;

import java.util.UUID;

public record OrganizationJoinRequest(
    UUID playerUniqueId,
    Organization targetOrganization,
    long time
) {

    /**
     * If time is 5 minutes old or more
     * @return
     */
    boolean isExpired() {
        return System.currentTimeMillis() < (time * (1000 * 60 * 5));
    }

}
