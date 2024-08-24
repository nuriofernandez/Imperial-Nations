package me.nurio.imperial.core.menus.playertoplayer;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.menus.playertoplayer.operations.joinrequest.RequestJoinOrganization;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OutsiderToMemberMenu {

    private static final OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();

    public static void openMenu(Player player, Player clickedPlayer) {
        SGMenu sgMenu = Imperial.getSpiGui().create("Organization menu", 1);
        // Create a button
        SGButton requestJoinBtn = new SGButton(
            new ItemBuilder(Material.KELP)
                .name("Request to join")
                .build()
        ).withListener((InventoryClickEvent event) -> {
            player.closeInventory();
            Organization targetOrganization = organizationFactory.fromPlayer(clickedPlayer);
            RequestJoinOrganization.requestToJoin(player, targetOrganization);
        });
        sgMenu.addButton(requestJoinBtn);

        player.openInventory(sgMenu.getInventory());
    }

}
