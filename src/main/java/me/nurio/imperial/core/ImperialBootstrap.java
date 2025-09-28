package me.nurio.imperial.core;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import me.nurio.imperial.core.menus.MenusModule;
import org.bukkit.plugin.java.JavaPlugin;

public class ImperialBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(BootstrapContext context) {
        MenusModule.register(context);
    }

    @Override
    public JavaPlugin createPlugin(PluginProviderContext context) {
        context.getLogger().info("Starting imperial as a bootstrapped plugin!");
        return new Imperial();
    }

}
