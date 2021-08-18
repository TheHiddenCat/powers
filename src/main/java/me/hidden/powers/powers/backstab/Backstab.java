package me.hidden.powers.powers.backstab;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;
import org.bukkit.ChatColor;

public final class Backstab extends Power {

    public Backstab() {
        super();
        addConfig("config", BackstabConfiguration.class);
        addEvent(BackstabListener.class);
    }

    @Override
    public String getName() {
        return "Backstab";
    }

    @Override
    public String getDescription() {
        return "Damage is increased by " + ChatColor.GREEN + "+" +
                (getDamageModifier() * 100d - 100d) + "% " + ChatColor.RESET + "when hitting enemies from behind.";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.OFFENSIVE;
    }

    public double getDamageModifier() {
        return getConfig("config", BackstabConfiguration.class).getDamageModifier();
    }
}
