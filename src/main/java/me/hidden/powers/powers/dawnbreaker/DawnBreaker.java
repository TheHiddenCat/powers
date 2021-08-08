package me.hidden.powers.powers.dawnbreaker;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.bowmaster.BowMasterConfiguration;
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

    public double getRayLength() {
        return getConfig("config", DawnBreakerConfiguration.class).getRayLength();
    }

    public double getRayDamage() {
        return getConfig("config", DawnBreakerConfiguration.class).getRayDamage();
    }
}
