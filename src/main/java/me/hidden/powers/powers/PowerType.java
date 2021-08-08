package me.hidden.powers.powers;

import org.bukkit.ChatColor;

public enum PowerType {
    PASSIVE(ChatColor.GREEN),
    DEFENSIVE(ChatColor.BLUE),
    OFFENSIVE(ChatColor.RED),
    SPECIAL(ChatColor.GOLD);

    private final ChatColor color;

    PowerType(ChatColor color) {
        this.color = color;
    }

    public ChatColor getColor() {
        return color;
    }
}
