package de.zeroxtv.zconomy.Commands;

import de.zeroxtv.zconomy.Accounts.PlayerAccount;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ZeroxTV
 */
public class Money extends BukkitCommand {


    public Money(String name) {
        super(name);
        this.description = "Zeigt an wie viel Geld du hast";
        this.usageMessage = "/money";
        this.setPermission("ZC.money");
        this.setAliases(Arrays.asList("balance", "bal"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            sender.sendMessage("Du hast " + PlayerAccount.getPlayerAccount(p).getBalance() + " Münzen");
        } else {
            sender.sendMessage("Du hast unendlich viele Münzen");
        }
        return true;
    }
}
