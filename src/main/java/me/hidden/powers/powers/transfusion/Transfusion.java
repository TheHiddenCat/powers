package me.hidden.powers.powers.transfusion;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class Transfusion extends Power {

    public Transfusion() {
        super();
        addEvent(TransfusionListener.class);
        addConfig("config", TransfusionConfiguration.class);
    }

    @Override
    public String getName() {
        return "Transfusion";
    }

    @Override
    public String getDescription() {
        return "Something";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.OFFENSIVE;
    }

    public double getDamagePerTick() { return getConfig("config", TransfusionConfiguration.class).getDamagePerTick(); }
    public double getHealthPerTick() { return getConfig("config", TransfusionConfiguration.class).getHealthPerTick(); }
}
