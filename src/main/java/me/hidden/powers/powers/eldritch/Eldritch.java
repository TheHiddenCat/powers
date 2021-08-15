package me.hidden.powers.powers.eldritch;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;
import me.hidden.powers.powers.bloodboil.BloodBoil;
import me.hidden.powers.powers.transfusion.Transfusion;
import me.hidden.powers.powers.vampirism.Vampirism;

import java.util.List;

public final class Eldritch extends Power {

    private EldritchTask task;

    public Eldritch() {
        super();
    }

    @Override
    public void onEnable() {
        task = new EldritchTask(this);
        task.runTaskTimer(Main.getPlugin(Main.class), 0, 2);
    }

    @Override
    public void onDisable() {
        task.cancel();
    }

    @Override
    public String getName() {
        return "Eldritch";
    }

    @Override
    public String getDescription() {
        return "Become a god of eld";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.ULTIMATE;
    }

    @Override
    public Iterable<Class<? extends Power>> requiredPowers() {
        return List.of(Transfusion.class, BloodBoil.class, Vampirism.class);
    }
}
