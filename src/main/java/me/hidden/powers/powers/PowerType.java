package me.hidden.powers.powers;

import org.bukkit.ChatColor;

public enum PowerType {
    UTILITY(ChatColor.GREEN, "Utility powers are powers that impact have impact on mobility, gathering or other non combat related tasks."),
    DEFENSIVE(ChatColor.BLUE, "Defensive powers are powers that gives you more ways to mitigate incoming damage."),
    OFFENSIVE(ChatColor.RED , "Offensive powers are powers that give you more ways of dealing damage."),
    SPECIAL(ChatColor.LIGHT_PURPLE, "Special powers are powers that grants you a mix of utility, defensive and/or offensive capabilities."),
    ULTIMATE(ChatColor.GOLD, "Ultimate powers are powers that require other powers in order to be acquired. Ultimate powers are pure visually and do not offer any special bonuses.");

    private final ChatColor color;
    private final String description;

    PowerType(ChatColor color, String description) {
        this.color = color;
        this.description = description;
    }

    public ChatColor getColor() {
        return color;
    }
    public String getDescription() { return description; }
}
