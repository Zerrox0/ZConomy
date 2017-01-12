package de.zeroxtv.zconomy;

import de.zeroxtv.zconomy.Commands.Money;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public final class ZConomy extends JavaPlugin {

    private static ZConomy instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        registerCommands();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ZConomy getInstance() {
        return instance;
    }

    private void registerCommands() {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register("money", new Money("money"));


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
