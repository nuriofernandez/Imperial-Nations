package me.nurio.imperial.core.menus.creation.operations;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.input.TextDialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.menus.creation.OrganizationCreatedMessage;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CreateOrganizationOperation {

    static final String CREATE_NAME_INPUT_ID = "organization_creation_name_input";

    public static void createOrganization(Player player, Location foundingBlockLocation) {
        List<TextDialogInput> dialogInputs = new ArrayList();
        dialogInputs.add(DialogInput.text(CREATE_NAME_INPUT_ID, Component.text("Name your Empire")).build());

        DialogAction.CustomClickAction organizationRenameAction = DialogAction.customClick(
                (view, audience) -> {
                    String organizationNameInput = view.getText(CREATE_NAME_INPUT_ID);
                    if (audience instanceof Player) {
                        CreateOrganization.createOrganization(player, foundingBlockLocation, organizationNameInput);
                    }
                },
                ClickCallback.Options.builder().uses(1).lifetime(ClickCallback.DEFAULT_LIFETIME).build()
        );

        Dialog dialog = Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(Component.text("Empire settings"))
                        .inputs(dialogInputs)
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
