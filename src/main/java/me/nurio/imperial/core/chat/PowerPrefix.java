package me.nurio.imperial.core.chat;

import me.nurio.imperial.core.organizations.Organization;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import java.text.DecimalFormat;

public class PowerPrefix {

    private static DecimalFormat decimalFormat = new DecimalFormat("#.0");

    public static Component getPower(Organization organization) {
        return Component.text("✦" +powerDecimal(organization))
            .color(TextColor.color(230, 245, 125));
    }

    private static String powerDecimal(Organization organization) {
        double power = organization.getPower();
        double stars = power/10000;

        return decimalFormat.format(stars);
    }

}
