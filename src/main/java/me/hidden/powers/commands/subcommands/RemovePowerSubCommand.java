package me.hidden.powers.commands.subcommands;

import me.hidden.powers.commands.SubCommand;
import me.hidden.powers.managers.PowerManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class RemovePowerSubCommand implements SubCommand {

    private final PowerManager powerManager;

    public RemovePowerSubCommand(PowerManager powerManager) {
        this.powerManager = powerManager;
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Remove a specific power from this player";
    }

    @Override
    public String getUsage() {
        return "powers remove <power_name>";
    }

    @Override
    public void execute(Player sender, String[] args) {
        if (args.length == 2) {
            var powerName = args[1];
            var power = powerManager.getPower(powerName);
            if (power == null) {
                sender.sendMessage("Invalid Power name, make sure the name is correct!");
                return;
            }
            powerManager.unregisterPlayer(sender.getUniqueId(), power);
            sender.sendMessage(ChatColor.RED + "Removed " + power.getFancyName() + ChatColor.RED + " from player powers!");
        }
    }
}
