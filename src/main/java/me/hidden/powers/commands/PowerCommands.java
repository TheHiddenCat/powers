package me.hidden.powers.commands;

import me.hidden.powers.commands.subcommands.AddPowerSubCommand;
import me.hidden.powers.commands.subcommands.ListPowersSubCommand;
import me.hidden.powers.commands.subcommands.RemovePowerSubCommand;
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
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.YELLOW + "/powers ");
                for (var subCommand : subCommands) {
                    player.sendMessage(ChatColor.YELLOW + " |> " + subCommand.getName());
                }
                return false;
            }
            else {
                for (var subCommand : subCommands) {
                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        subCommand.execute(player, args);
                    }
                }
            }
        }
        return true;
    }
}
