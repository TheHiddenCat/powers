package me.hidden.powers.powers.dawnbreaker;

import me.hidden.powers.powers.Power;

import org.bukkit.ChatColor;

public final class DawnBreaker extends Power {

    public DawnBreaker() {
        super();
        addEvent(DawnBreakerListener.class);
        addConfig("config", DawnBreakerConfiguration.class);
    }

    @Override
    public String getName() {
        return "Dawnbreaker";
    }

    @Override
    public String getFancyName() {
        return ChatColor.RED + "Dawn Breaker";
    }

    @Override
    public String getDescription() {
        return "Some description";
    }

    public double getSweepForce() { return getConfig("config", DawnBreakerConfiguration.class).getSweepForce(); }

    public double getSweepDamage() { return getConfig("config", DawnBreakerConfiguration.class).getSweepDamage(); }

    public double getRayLength() {
        return getConfig("config", DawnBreakerConfiguration.class).getRayLength();
    }

    public double getRayDamage() {
        return getConfig("config", DawnBreakerConfiguration.class).getRayDamage();
    }
}
