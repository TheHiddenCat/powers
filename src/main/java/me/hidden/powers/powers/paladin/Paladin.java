package me.hidden.powers.powers.paladin;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class Paladin extends Power {
    @Override
    public String getName() {
        return "Paladin";
    }

    @Override
    public String getDescription() {
        return "Something";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.DEFENSIVE;
    }
}
