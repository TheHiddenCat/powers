package me.hidden.powers.powers.bloodboil;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class BloodBoil extends Power {

    public BloodBoil() {
        super();
        addEvent(BloodBoilListener.class);
    }

    @Override
    public String getName() {
        return "BloodBoil";
    }

    @Override
    public String getDescription() {
        return "Something";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.OFFENSIVE;
    }
}
