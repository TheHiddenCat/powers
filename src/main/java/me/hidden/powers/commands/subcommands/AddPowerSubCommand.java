package me.hidden.powers.commands.subcommands;

import me.hidden.powers.commands.SubCommand;
import me.hidden.powers.config.PlayerConfiguration;
import me.hidden.powers.managers.PowerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class AddPowerSubCommand implements SubCommand {

    private final PowerManager powerManager;
    private final PlayerConfiguration playerConfiguration;

    public AddPowerSubCommand(PowerManager powerManager, PlayerConfiguration playerConfiguration) {
        this.powerManager = powerManager;
        this.playerConfiguration = playerConfiguration;
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
                sender.sendMessage(ChatColor.YELLOW + "Invalid Power name, make sure the name is correct!");
                return;
            }
            var uuid = sender.getUniqueId();
            if (power.playerHasPower(uuid)) {
                sender.sendMessage(ChatColor.YELLOW + "You already have " + power.getFancyName() + ChatColor.YELLOW + " assigned to your powers!");
                return;
            }

            powerManager.registerPlayer(uuid, power);
            playerConfiguration.addPower(uuid, power.getName());
            playerConfiguration.save();
            sender.sendMessage(ChatColor.YELLOW + power.getFancyName() + ChatColor.YELLOW + " added to player powers!");
        }
    }
}
