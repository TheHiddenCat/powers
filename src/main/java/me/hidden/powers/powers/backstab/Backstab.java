package me.hidden.powers.powers.backstab;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;
import me.hidden.powers.powers.bloodboil.BloodBoilConfiguration;

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
        return "Damaging enemies from behind their back increases damage dealt by 50%";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.OFFENSIVE;
    }

    public double getDamageModifier() {
        return getConfig("config", BackstabConfiguration.class).getDamageModifier();
    }
}
