package me.hidden.powers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandManager implements CommandExecutor {

    protected final List<SubCommand> subCommands;

    protected CommandManager() {
        this.subCommands = new ArrayList<>();
    }

    @Override
    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
