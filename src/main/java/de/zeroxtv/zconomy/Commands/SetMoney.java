package de.zeroxtv.zconomy.Commands;

import de.zeroxtv.zconomy.Accounts.PlayerAccount;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

/**
 * Created by ZeroxTV
 */
public class SetMoney extends BukkitCommand {

    public SetMoney(String name) {
        super(name);
        this.description = "Setze deinen Kontostand";
        this.usageMessage = "/setmoney [Player] <amount>";
        this.setPermission("ZC.money.set");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length == 2) {
            Player toSet = Bukkit.getPlayer(args[0]);
            PlayerAccount.getPlayerAccount(toSet).setBalance(Double.valueOf(args[1]));
            toSet.sendMessage("Dein Kontostand wurde auf " + args[1] + " Münzen gesetzt");
            return true;
        } else if (args.length == 1) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                PlayerAccount.getPlayerAccount(p).setBalance(Double.valueOf(args[0]));
                p.sendMessage("Dein Kontostand wurde auf " + args[0] + " Münzen gesetzt");
                return true;
            } else {
                sender.sendMessage("Du hast immer noch unendlich Münzen");
                return true;
            }
        } else {
            return false;
        }
    }
}
