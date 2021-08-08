package me.hidden.powers.commands;

import me.hidden.powers.commands.subcommands.*;
import me.hidden.powers.config.PlayerConfiguration;
import me.hidden.powers.managers.PowerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class PowerCommands extends CommandManager {

    public PowerCommands(PowerManager powerManager, PlayerConfiguration playerConfiguration) {
        this.subCommands.add(new AddPowerSubCommand(powerManager, playerConfiguration));
        this.subCommands.add(new ListPowersSubCommand(powerManager, playerConfiguration));
        this.subCommands.add(new RemovePowerSubCommand(powerManager, playerConfiguration));
        this.subCommands.add(new ShowPowersSubCommand(powerManager, playerConfiguration));
        this.subCommands.add(new InfoPowerSubCommand(powerManager, playerConfiguration));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 0) {
                for (var subCommand : subCommands) {
                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        var result = subCommand.execute(player, args);
                        if (result) {
                            player.sendMessage(ChatColor.GREEN + "[Powers]");
                            sender.sendMessage(ChatColor.YELLOW + subCommand.getUsage());
                        }
                        return false;
                    }
                }
            }
            displayUsages(player);
        }
        return false;
    }

    private void displayUsages(Player player) {
        player.sendMessage(ChatColor.GREEN + "[Powers]");
        player.sendMessage(ChatColor.YELLOW + "/powers ");
        for (var subCommand : subCommands) {
            player.sendMessage(ChatColor.YELLOW + " |> " + subCommand.getUsage());
        }
    }
}
