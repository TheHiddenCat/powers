package me.hidden.powers.commands.subcommands;

import me.hidden.powers.commands.SubCommand;
import me.hidden.powers.config.PlayerConfiguration;
import me.hidden.powers.managers.PowerManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class ListPowersSubCommand implements SubCommand {

    private final PowerManager powerManager;
    private final PlayerConfiguration playerConfiguration;

    public ListPowersSubCommand(PowerManager powerManager, PlayerConfiguration playerConfiguration) {
        this.powerManager = powerManager;
        this.playerConfiguration = playerConfiguration;
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Display all available powers";
    }

    @Override
    public String getUsage() {
        return "/powers list";
    }

    @Override
    public boolean execute(Player sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage(ChatColor.GREEN + "[Powers]");
            var message = new StringBuilder();
            for (var power : powerManager.getPowers()) {
                message.append(power.getFancyName()).append(" ");
            }
            sender.sendMessage(message.toString());
            sender.sendMessage("");
            return false;
        }
        return true;
    }
}
