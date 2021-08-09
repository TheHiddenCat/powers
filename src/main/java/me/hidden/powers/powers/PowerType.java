package me.hidden.powers.powers;

import org.bukkit.ChatColor;

public enum PowerType {
    UTILITY(ChatColor.GREEN),
    DEFENSIVE(ChatColor.BLUE),
    OFFENSIVE(ChatColor.RED),
    SPECIAL(ChatColor.LIGHT_PURPLE);

    private final ChatColor color;

    PowerType(ChatColor color) {
        this.color = color;
    }

    public ChatColor getColor() {
        return color;
    }
}
