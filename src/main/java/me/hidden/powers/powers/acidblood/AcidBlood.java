package me.hidden.powers.powers.acidblood;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class AcidBlood extends Power {

    public AcidBlood() {
        super();
        addConfig("config", AcidBloodConfiguration.class);
        addEvent(AcidBloodListener.class);
    }
    @Override
    public String getName() {
        return "AcidBlood";
    }

    @Override
    public String getDescription() {
        return "Something";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.DEFENSIVE;
    }

    public int getPoisonModifier() {
        return getConfig("config", AcidBloodConfiguration.class).getPoisonModifier();
    }

    public int getPoisonDuration() {
        return getConfig("config", AcidBloodConfiguration.class).getPoisonDuration();
    }
}
