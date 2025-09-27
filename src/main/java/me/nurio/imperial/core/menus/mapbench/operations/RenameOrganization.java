package me.nurio.imperial.core.menus.mapbench.operations;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.input.TextDialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import me.nurio.imperial.core.menus.mapbench.OrganizationRenamedMessage;
import me.nurio.imperial.core.organizations.Organization;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RenameOrganization {

    static final String RENAME_INPUT_ID = "organization_name_input";

    public static void rename(Player player, Organization organization) {
        List<TextDialogInput> asd = new ArrayList();
        asd.add(DialogInput.text(RENAME_INPUT_ID, Component.text("Rename your Empire")).build());

        DialogAction.CustomClickAction organizationRenameAction = DialogAction.customClick(
                (view, audience) -> {
                    String organizationNameInput = view.getText(RENAME_INPUT_ID);
                    String oldOrganizationName = organization.getName();
                    if (audience instanceof Player) {
                        // Update the empire name
                        organization.setName(organizationNameInput);

                        // Send a message about it to everyone
                        Component message = OrganizationRenamedMessage.build(player, organization, oldOrganizationName);
                        Audience.audience(Bukkit.getOnlinePlayers()).sendMessage(message);
                    }
                },
                ClickCallback.Options.builder().uses(1).lifetime(ClickCallback.DEFAULT_LIFETIME).build()
        );

        Dialog dialog = Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(Component.text("Organization settings"))
                        .inputs(asd)
                        .build())
                .type(DialogType.confirmation(
                        ActionButton.create(
                                Component.text("Confirm", TextColor.color(0xAEFFC1)),
                                Component.text("Click to confirm the new name."),
                                100,
                                organizationRenameAction
                        ),
                        ActionButton.create(
                                Component.text("Discard", TextColor.color(0xFFA0B1)),
                                Component.text("Click to discard your input."),
                                100,
                                null
                        )
                ))

        );

        player.showDialog(dialog);
    }
}
