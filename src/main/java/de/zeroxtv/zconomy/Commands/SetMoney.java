package de.zeroxtv.zconomy.Commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

/**
 * Created by Leon on 12.01.2017.
 */
public class SetMoney extends BukkitCommand {

    protected SetMoney(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return false;
    }
}
