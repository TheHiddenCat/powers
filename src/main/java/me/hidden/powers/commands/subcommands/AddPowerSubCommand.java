package me.hidden.powers.commands.subcommands;

import me.hidden.powers.commands.SubCommand;
import me.hidden.powers.config.PlayerConfiguration;
import me.hidden.powers.managers.PowerManager;
import me.hidden.powers.powers.Power;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

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
        return "/powers add <power_name>";
    }

    @Override
    public boolean execute(Player sender, String[] args) {
        if (args.length == 2) {
            var powerName = args[1];
            var power = powerManager.getPower(powerName);
            if (power == null) {
                sender.sendMessage(ChatColor.GREEN + "[Powers]");
                sender.sendMessage(ChatColor.YELLOW + "Invalid Power name, make sure the name is correct!");
                return false;
            }
            var uuid = sender.getUniqueId();
            if (power.playerHasPower(uuid)) {
                sender.sendMessage(ChatColor.GREEN + "[Powers]");
                sender.sendMessage(ChatColor.YELLOW + "You already have " + power.getFancyName() + ChatColor.YELLOW + " assigned to your powers!");
                return false;
            }
            var missingPowers = new ArrayList<String>();
            for (var required : power.requiredPowers()) {
                var requiredPower = powerManager.getPower(required);
                if (requiredPower == null) continue;
                if (!requiredPower.playerHasPower(uuid)) {
                    missingPowers.add(requiredPower.getFancyName());
                }
            }
            if (missingPowers.size() > 0) {
                sender.sendMessage(ChatColor.GREEN + "[Powers]");
                sender.sendMessage(ChatColor.YELLOW + "In order to add " + power.getFancyName() + ChatColor.YELLOW + " you must have the following powers: ");
                for (var missingPower : missingPowers) {
                    sender.sendMessage(ChatColor.YELLOW + "|> " + missingPower);
                }
                return false;
            }

            powerManager.registerPlayer(uuid, power);
            playerConfiguration.addPower(uuid, power.getName());
            playerConfiguration.save();
            sender.sendMessage(ChatColor.GREEN + "[Powers]");
            sender.sendMessage(ChatColor.YELLOW + power.getFancyName() + ChatColor.YELLOW + " added to player powers!");
            return false;
        }
        return true;
    }
}
