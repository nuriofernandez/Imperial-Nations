package me.nurio.imperial.core.menus.playertoplayer;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.menus.playertoplayer.operations.acceptjoinrequest.joinrequest.AcceptJoinOrganization;
import me.nurio.imperial.core.menus.playertoplayer.operations.joinrequest.RequestJoinOrganization;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MemberToOutsiderMenu {

    private static final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    public static void openMenu(Player player, Player clickedPlayer) {
        SGMenu sgMenu = Imperial.getSpiGui().create("Organization menu", 1);
        Organization organization = organizationFactory.fromPlayer(player);

        if (RequestJoinOrganization.hasRequest(clickedPlayer, organization)) {
            SGButton acceptJoinBtn = new SGButton(
                new ItemBuilder(Material.KELP)
                    .name("Accept as a " + organization.getName() + " member.")
                    .build()
            ).withListener((InventoryClickEvent event) -> {
                player.closeInventory();
                AcceptJoinOrganization.acceptJoinRequest(player, clickedPlayer);
            });
            sgMenu.addButton(acceptJoinBtn);
        }

        player.openInventory(sgMenu.getInventory());
    }
}
