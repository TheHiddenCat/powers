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

    public double getDamage() { return getConfig("config", TransfusionConfiguration.class).getDamage(); }
    public double getHealth() { return getConfig("config", TransfusionConfiguration.class).getHealth(); }
    public double getDistance() { return getConfig("config", TransfusionConfiguration.class).getDistance(); }
    public int getMaxEnemiesHit() { return getConfig("config", TransfusionConfiguration.class).getMaxEnemiesHit(); }
}
