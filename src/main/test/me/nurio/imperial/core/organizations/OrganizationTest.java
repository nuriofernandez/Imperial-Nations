package me.nurio.imperial.core.organizations;

import me.nurio.minecraft.worldareas.areas.WorldArea;

import java.util.ArrayList;
import java.util.UUID;

public class OrganizationTest {

    public static void main(String[] args) {
        Organization org = new Organization(
                UUID.randomUUID(),
                "Nu",
                new ArrayList<>(),
                new WorldArea("", UUID.randomUUID(), new ArrayList<>()),
                2000000
        );

        double stars = org.getStars();
        System.out.printf("%.2f",stars);
    }
}
