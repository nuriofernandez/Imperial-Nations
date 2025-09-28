package me.nurio.imperial.core.menus.mapbench.dialog;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.handler.configuration.PrioritizedLifecycleEventHandlerConfiguration;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.input.TextDialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.DialogKeys;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.menus.mapbench.OrganizationRenamedMessage;
import me.nurio.imperial.core.organizations.Organization;
import me.nurio.imperial.core.organizations.OrganizationFactory;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RenameOrganizationDialog {

    public static final String DIALOG_KEY = "imperial:rename";
    static final String RENAME_INPUT_ID = "imperial_organization_name_input";

    public static void register(BootstrapContext context) {
        List<TextDialogInput> dialogInputs = new ArrayList();
        dialogInputs.add(DialogInput.text(RENAME_INPUT_ID, Component.text("Introduce empire's new name:")).build());

        DialogAction.CustomClickAction organizationRenameAction = DialogAction.customClick(
                (view, audience) -> {
                    // Do not execute if is not player
                    if (!(audience instanceof Player)) {
                        return;
                    }
                    Player player = (Player) audience;

                    // Validate new name
                    String organizationNameInput = view.getText(RENAME_INPUT_ID);
                    if (organizationNameInput.isBlank()) {
                        player.sendMessage("Please, provide a valid empire name.");
                        return;
                    }

                    if (organizationNameInput.length() < 2) {
                        player.sendMessage("New name is too short!");
                        return;
                    }

                    if (organizationNameInput.length() > 16) {
                        player.sendMessage("New name is too long!");
                        return;
                    }

                    OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();
                    boolean nameAlreadyInUse = organizationFactory.getOrganizations().stream()
                            .map(Organization::getName)
                            .anyMatch(organizationNameInput::equalsIgnoreCase);

                    if (nameAlreadyInUse){
                        player.sendMessage("This empire name is already taken!");
                        return;
                    }

                    Organization organization = organizationFactory.fromPlayer(player);
                    String oldOrganizationName = organization.getName();

                    // Update the empire name
                    organization.setName(organizationNameInput);

                    // Send a message about it to everyone
                    Component message = OrganizationRenamedMessage.build(player, organization, oldOrganizationName);
                    Audience.audience(Bukkit.getOnlinePlayers()).sendMessage(message);
                },
                ClickCallback.Options.builder().uses(ClickCallback.UNLIMITED_USES).build()
        );

        var dialogConfig = RegistryEvents.DIALOG.compose().newHandler(event -> event.registry().register(
                DialogKeys.create(Key.key(DIALOG_KEY)),
                builder -> builder
                        .base(DialogBase.builder(Component.text("Rename your Empire"))
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
                                        Component.text("Cancel empire renaming."),
                                        100,
                                        null
                                )
                        ))
        ));

        context.getLifecycleManager().registerEventHandler(dialogConfig);
    }

    public static void show(Player player) {
        Dialog dialog = RegistryAccess.registryAccess().getRegistry(RegistryKey.DIALOG).get(Key.key(DIALOG_KEY));
        Audience.audience(player).showDialog(dialog);
    }

}
