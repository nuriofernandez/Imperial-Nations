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
import io.papermc.paper.registry.set.RegistryKeySet;
import io.papermc.paper.registry.set.RegistrySet;
import me.nurio.imperial.core.Imperial;
import me.nurio.imperial.core.menus.mapbench.OrganizationRenamedMessage;
import me.nurio.imperial.core.organizations.Organization;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Registry;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrganizationSettingsDialog {

    public static final String DIALOG_KEY = "imperial:settings";

    public static void register(BootstrapContext context) {

        final RegistryKeySet<Dialog> options = RegistrySet.keySet(
                RegistryKey.DIALOG,
                // Arbitrary keys of enchantments to store in the key set.
                DialogKeys.create(Key.key(RenameOrganizationDialog.DIALOG_KEY)),
                DialogKeys.create(Key.key(LeaveOrganizationDialog.DIALOG_KEY))
        );

        var dialogConfig = RegistryEvents.DIALOG.compose().newHandler(event -> event.registry().register(
                DialogKeys.create(Key.key(DIALOG_KEY)),
                builder -> builder
                        .base(DialogBase.builder(Component.text("Empire settings")).build())
                        .type(
                                DialogType.dialogList(options).build()
                        ))
        );

        context.getLifecycleManager().registerEventHandler(dialogConfig);
    }

    public static void show(Player player) {
        Dialog dialog = RegistryAccess.registryAccess().getRegistry(RegistryKey.DIALOG).get(Key.key(DIALOG_KEY));
        Audience.audience(player).showDialog(dialog);
    }

}
