package me.hidden.powers.commands.subcommands;

import me.hidden.powers.commands.SubCommand;
import me.hidden.powers.managers.PowerManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class ListPowersSubCommand implements SubCommand {

    private final PowerManager powerManager;

    public ListPowersSubCommand(PowerManager powerManager) {
        this.powerManager = powerManager;
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
        return "powers list";
    }

    @Override
    public void execute(Player sender, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "[Powers]");
        var message = new StringBuilder();
        for (var power : powerManager.getPowers()) {
            message.append(power.getFancyName()).append(" ");
        }
        sender.sendMessage(message.toString());
    }
}
