package de.zeroxtv.zconomy;

import de.zeroxtv.zconomy.Accounts.PlayerAccount;
import de.zeroxtv.zconomy.Commands.Money;
import de.zeroxtv.zconomy.Commands.Pay;
import de.zeroxtv.zconomy.Commands.SetMoney;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public final class ZConomy extends JavaPlugin {

    private static ZConomy instance;
    private static ConsoleCommandSender logger;
    public final static String cPrefix = "[ZConomy] ";

    @Override
    public void onEnable() {
        logger = this.getServer().getConsoleSender();
        log(ChatColor.GREEN + "ZConomy activated!");
        instance = this;
        registerCommands();

    }

    @Override
    public void onDisable() {
        log("Saving Player accounts...");
        PlayerAccount.saveAll();
        log(ChatColor.RED + "ZConomy deactivated!");
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
            commandMap.register("setmoney", new SetMoney("setmoney"));
            commandMap.register("pay", new Pay("pay"));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void log(String message) {
        logger.sendMessage(cPrefix + message);
    }
}
