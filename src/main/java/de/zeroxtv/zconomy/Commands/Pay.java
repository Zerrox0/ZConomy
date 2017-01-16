package de.zeroxtv.zconomy.Commands;

import de.zeroxtv.zconomy.Accounts.PlayerAccount;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

/**
 * Created by Leon on 13.01.2017.
 */
public class Pay extends BukkitCommand {

    public Pay(String name) {
        super(name);
        this.description = "Überweise einem Spieler Münzen";
        this.usageMessage = "/setmoney <Player> <amount>";
        this.setPermission("ZC.money.pay");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length != 2) {
            return false;
        }
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Player playerTo = Bukkit.getPlayer(args[0]);
            PlayerAccount.getPlayerAccount(p).transact(playerTo, Double.valueOf(args[1]));
            p.sendMessage("Du hast " + args[1] + " Münzen an " + playerTo.getName() + " überwiesen");
            playerTo.sendMessage(p.getName() + " hat dir " + args [1] + "Münzen überwiesen");
            return true;
        } else {
            sender.sendMessage("Du kannst keine Überweisungen tätigen");
            return true;
        }
    }
}
