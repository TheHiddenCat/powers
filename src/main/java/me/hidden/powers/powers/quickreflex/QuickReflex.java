package me.hidden.powers.powers.quickreflex;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class QuickReflex extends Power {

    public QuickReflex() {
        super();
        addEvent(QuickReflexListener.class);
    }

    @Override
    public String getName() {
        return "QuickReflex";
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
