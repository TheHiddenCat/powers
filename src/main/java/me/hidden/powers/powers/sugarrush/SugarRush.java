package me.hidden.powers.powers.sugarrush;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;
import me.hidden.powers.util.RomanConverter;
import org.bukkit.ChatColor;

public final class SugarRush extends Power {

    public SugarRush() {
        super();
        addConfig("config", SugarRushConfiguration.class);
        addEvent(SugarRushListener.class);
    }

    @Override
    public String getName() {
        return "SugarRush";
    }

    @Override
    public String getDescription() {
        return "Consuming sugar gives you" + ChatColor.GOLD +
                " Speed " + ChatColor.BLUE + RomanConverter.toRoman(getSpeedAmplifier() + 1) + ChatColor.RESET +
                " for " + ChatColor.GREEN + (getSpeedDuration() / 20) + ChatColor.RESET + " second(s)";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.UTILITY;
    }

    public int getSpeedAmplifier() { return getConfig("config", SugarRushConfiguration.class).getSpeedAmplifier(); }
    public int getSpeedDuration() { return getConfig("config", SugarRushConfiguration.class).getSpeedDuration(); }
}
