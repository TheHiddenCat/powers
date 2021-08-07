package me.hidden.powers.commands.subcommands;

import me.hidden.powers.commands.SubCommand;
import me.hidden.powers.config.PlayerConfiguration;
import me.hidden.powers.managers.PowerManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class RemovePowerSubCommand implements SubCommand {

    private final PowerManager powerManager;
    private final PlayerConfiguration playerConfiguration;

    public RemovePowerSubCommand(PowerManager powerManager, PlayerConfiguration playerConfiguration) {
        this.powerManager = powerManager;
        this.playerConfiguration = playerConfiguration;
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
                sender.sendMessage(ChatColor.YELLOW + "Invalid Power name, make sure the name is correct!");
                return;
            }
            var uuid = sender.getUniqueId();
            if (!power.playerHasPower(uuid)) {
                sender.sendMessage(ChatColor.YELLOW + "You do not have this power!");
                return;
            }
            powerManager.unregisterPlayer(uuid, power);
            playerConfiguration.removePower(uuid, power.getName());
            playerConfiguration.save();
            sender.sendMessage(ChatColor.YELLOW + "Removed " + power.getFancyName() + ChatColor.YELLOW + " from player powers!");
        }
    }
}
