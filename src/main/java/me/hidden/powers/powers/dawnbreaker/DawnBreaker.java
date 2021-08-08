package me.hidden.powers.powers.dawnbreaker;

import me.hidden.powers.powers.Power;

import me.hidden.powers.powers.PowerType;

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
    public PowerType getPowerType() {
        return PowerType.OFFENSIVE;
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
