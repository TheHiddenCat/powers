package me.hidden.powers.commands.subcommands;

import me.hidden.powers.commands.SubCommand;
import me.hidden.powers.config.PlayerConfiguration;
import me.hidden.powers.managers.PowerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InfoPowerSubCommand implements SubCommand {

    private final PowerManager powerManager;
    private final PlayerConfiguration playerConfiguration;

    public InfoPowerSubCommand(PowerManager powerManager, PlayerConfiguration playerConfiguration) {
        this.powerManager = powerManager;
        this.playerConfiguration = playerConfiguration;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Display info about a certain power";
    }

    @Override
    public String getUsage() {
        return "/powers info <power_name>";
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

            sender.sendMessage(ChatColor.GREEN + "[Powers]");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.YELLOW + "[ " + power.getFancyName() +  ChatColor.YELLOW + " ]");
            sender.sendMessage(ChatColor.YELLOW + "+---------------------------------------------------+");
            sender.sendMessage(power.getDescription());
            sender.sendMessage(ChatColor.YELLOW + "+---------------------------------------------------+");
            sender.sendMessage("");
            return false;
        }
        return true;
    }
}
