package de.zeroxtv.zconomy.Commands;

import de.zeroxtv.zconomy.ZObjects.ZItemLoader;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

/**
 * Created by ZeroxTV
 */
public class addItem extends BukkitCommand {

    public addItem() {
        super("additem");
        super.setDescription("Setze ein neues Item auf die ZItem Liste");
        super.setPermission("ZC.admin.additem");
        super.setUsage("/additem <o|r|n|b> <value> [offerValueMin] [offerValueMax] [requestValueMin] [requestValueMin]");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if (args[0].equalsIgnoreCase("n")) {
            ZItemLoader.createItem(ZItemLoader.defaultConfigFile,
                    player.getInventory().getItemInMainHand(),
                    Double.valueOf(args[1]),
                    0D,
                    0D,
                    0D,
                    0D);
        } else if (args[0].equalsIgnoreCase("o")) {
            ZItemLoader.createItem(ZItemLoader.defaultConfigFile,
                    player.getInventory().getItemInMainHand(),
                    Double.valueOf(args[1]),
                    Double.valueOf(args[2]),
                    Double.valueOf(args[3]),
                    0D,
                    0D);
        } else if (args[0].equalsIgnoreCase("r")) {
            ZItemLoader.createItem(ZItemLoader.defaultConfigFile,
                    player.getInventory().getItemInMainHand(),
                    Double.valueOf(args[1]),
                    0D,
                    0D,
                    Double.valueOf(args[2]),
                    Double.valueOf(args[3]));
        } else if (args[0].equalsIgnoreCase("b")) {
            ZItemLoader.createItem(ZItemLoader.defaultConfigFile,
                    player.getInventory().getItemInMainHand(),
                    Double.valueOf(args[1]),
                    Double.valueOf(args[2]),
                    Double.valueOf(args[3]),
                    Double.valueOf(args[4]),
                    Double.valueOf(args[5]));
        }


        return false;
    }
}
