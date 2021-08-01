package me.hidden.powers.commands;

import org.bukkit.entity.Player;

public interface SubCommand {
    String getName();
    String getDescription();
    String getUsage();
    void execute(Player sender, String[] args);
}
