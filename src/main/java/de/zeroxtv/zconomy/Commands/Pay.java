package de.zeroxtv.zconomy.Commands;

import de.zeroxtv.zconomy.Accounts.PlayerAccount;
import de.zeroxtv.zcore.OtherUtil.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

/**
 * Created by ZeroxTV
 */
public class Pay extends BukkitCommand {

    public Pay(String name) {
        super(name);
        this.description = "Überweise einem Spieler Münzen";
        this.usageMessage = "/pay <Player> <amount>";
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
            args[1].replace(',', '.');
            double trans = NumberUtils.parseDouble(Double.valueOf(args[1]),2);
            PlayerAccount.getPlayerAccount(p).transact(playerTo, trans);
            p.sendMessage("Du hast " + trans + " Münzen an " + playerTo.getName() + " überwiesen");
            playerTo.sendMessage(p.getName() + " hat dir " + trans + "Münzen überwiesen");
            return true;
        } else {
            sender.sendMessage("Du kannst keine Überweisungen tätigen");
            return true;
        }
    }
}
