package me.hidden.powers.powers.powerminer;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

import me.hidden.powers.util.RomanConverter;
import org.bukkit.ChatColor;

public final class PowerMiner extends Power {

    public PowerMiner() {
        super();
        addConfig("config", PowerMinerConfiguration.class);
        addEvent(PowerMinerListener.class);
    }

    @Override
    public String getName() {
        return "PowerMiner";
    }

    @Override
    public String getDescription() {
        return "Receives a stacking" + ChatColor.GOLD + " Haste " + ChatColor.RESET +
                "effect after mining any ore vein up to tier " + ChatColor.BLUE +
                RomanConverter.toRoman(getHasteMaxModifier() + 1) + ChatColor.RESET +
                " for " + ChatColor.GREEN + (getHasteDuration() / 20) + ChatColor.RESET +
                " seconds. Mining any ore also resets the effect duration.";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.UTILITY;
    }

    public int getHasteMaxModifier() {
        return getConfig("config", PowerMinerConfiguration.class).getHasteMaxModifier();
    }

    public int getHasteDuration() {
        return getConfig("config", PowerMinerConfiguration.class).getHasteDuration();
    }
}
