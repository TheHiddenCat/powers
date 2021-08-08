package me.hidden.powers.powers.bowmaster;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class BowMaster extends Power {

    public BowMaster() {
        super();
        addEvent(BowMasterListener.class);
        addConfig("config", BowMasterConfiguration.class);
    }

    @Override
    public String getName() {
        return "Bowmaster";
    }

    @Override
    public String getDescription() {
        return "Some description...";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.OFFENSIVE;
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
