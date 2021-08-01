package me.hidden.powers.commands.subcommands;

import me.hidden.powers.commands.SubCommand;
import me.hidden.powers.managers.PowerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class AddPowerSubCommand implements SubCommand {

    private final PowerManager powerManager;

    public AddPowerSubCommand(PowerManager powerManager) {
        this.powerManager = powerManager;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Add a power to the player";
    }

    @Override
    public String getUsage() {
        return "powers add <power_name>";
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
            powerManager.registerPlayer(sender.getUniqueId(), power);
            sender.sendMessage(ChatColor.GREEN + power.getFancyName() + " added to player powers!");
        }
    }
}
