package me.hidden.powers.powers.pyricarrow;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class PyricArrow extends Power {

    public PyricArrow() {
        super();
        addEvent(PyricArrowListener.class);
    }

    @Override
    public String getName() {
        return "PyricArrow";
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
