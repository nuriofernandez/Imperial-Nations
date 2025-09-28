package me.nurio.imperial.core.menus.mapbench.dialog;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
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
import me.nurio.imperial.core.organizations.disk.OrganizationSaver;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LeaveOrganizationDialog {

    public static final String DIALOG_KEY = "imperial:leave_empire";

    public static void register(BootstrapContext context) {
        DialogAction.CustomClickAction organizationLeaveAction = DialogAction.customClick(
                (view, audience) -> {
                    // Do not execute if is not player
                    if (!(audience instanceof Player)) {
                        return;
                    }
                    Player player = (Player) audience;
                    OrganizationFactory organizationFactory = Imperial.getOrganizationFactory();
                    Organization organization = organizationFactory.fromPlayer(player);

                    if (organization == null) {
                        return;
                    }

                    organization.getMembers().remove(Bukkit.getOfflinePlayer(player.getUniqueId()));
                    OrganizationSaver.save(organization);

                    player.sendMessage("You left "+organization.getName());
                },
                ClickCallback.Options.builder().uses(ClickCallback.UNLIMITED_USES).build()
        );

        var dialogConfig = RegistryEvents.DIALOG.compose().newHandler(event -> event.registry().register(
                DialogKeys.create(Key.key(DIALOG_KEY)),
                builder -> builder
                        .base(DialogBase.builder(Component.text("Leave the empire"))
                                .build())
                        .type(DialogType.confirmation(
                                ActionButton.create(
                                        Component.text("Confirm", TextColor.color(0xAEFFC1)),
                                        Component.text("Click to leave the empire."),
                                        100,
                                        organizationLeaveAction
                                ),
                                ActionButton.create(
                                        Component.text("Cancel", TextColor.color(0xFFA0B1)),
                                        Component.text("Nothing will happen."),
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
