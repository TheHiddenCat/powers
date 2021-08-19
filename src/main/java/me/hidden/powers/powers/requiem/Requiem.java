package me.hidden.powers.powers.requiem;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class Requiem extends Power {

    public Requiem() {
        super();
        addEvent(RequiemListener.class);
    }

    @Override
    public String getName() {
        return "Requiem";
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
