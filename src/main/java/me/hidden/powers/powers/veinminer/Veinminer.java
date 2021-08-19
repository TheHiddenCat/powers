package me.hidden.powers.powers.veinminer;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class Veinminer extends Power {

    public Veinminer() {
        super();
        addEvent(VeinminerListener.class);
    }

    @Override
    public String getName() {
        return "Veinminer";
    }

    @Override
    public String getDescription() {
        return "Ores go brrr";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.UTILITY;
    }
}
