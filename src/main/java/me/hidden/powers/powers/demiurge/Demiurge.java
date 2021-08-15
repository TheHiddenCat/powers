package me.hidden.powers.powers.demiurge;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class Demiurge extends Power {

    private DemiurgeTask task;

    public Demiurge() {
        super();
    }

    @Override
    public void onEnable() {
        task = new DemiurgeTask(this);
        task.runTaskTimer(Main.getPlugin(Main.class), 0, 2);
    }

    @Override
    public void onDisable() {
        task.cancel();
    }

    @Override
    public String getName() {
        return "Demiurge";
    }

    @Override
    public String getDescription() {
        return "Become a cosmic creator";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.ULTIMATE;
    }
}
