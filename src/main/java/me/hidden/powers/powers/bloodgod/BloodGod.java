package me.hidden.powers.powers.bloodgod;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;
import me.hidden.powers.powers.bloodboil.BloodBoil;
import me.hidden.powers.powers.transfusion.Transfusion;
import me.hidden.powers.powers.vampirism.Vampirism;

import java.util.List;

public final class BloodGod extends Power {

    private BloodGodTask task;

    public BloodGod() {
        super();
    }

    @Override
    public void onEnable() {
        task = new BloodGodTask(this);
        task.runTaskTimer(Main.getPlugin(Main.class), 0, 2);
    }

    @Override
    public void onDisable() {
        task.cancel();
    }

    @Override
    public String getName() {
        return "BloodGod";
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
