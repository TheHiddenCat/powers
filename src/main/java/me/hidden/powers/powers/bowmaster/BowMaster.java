package me.hidden.powers.powers.bowmaster;

import me.hidden.powers.powers.Power;
import org.bukkit.ChatColor;

public final class BowMaster extends Power {

    public BowMaster() {
        super();
        addEvent(BowMasterListener.class);
        addConfig("config", BowMasterConfiguration.class);
    }

    @Override
    public String getName() {
        return "BowMaster";
    }

    @Override
    public String getFancyName() {
        return ChatColor.RED + "Bow Master";
    }

    @Override
    public String getDescription() {
        return "Some description...";
    }

    public double getArrowDamage() {
        return getConfig("config", BowMasterConfiguration.class).getArrowDamage();
    }

    public double getArrowNoConsume() {
        return getConfig("config", BowMasterConfiguration.class).getArrowNoConsume();
    }

    public double getArrowVelocity() {
        return getConfig("config", BowMasterConfiguration.class).getArrowVelocity();
    }
}
