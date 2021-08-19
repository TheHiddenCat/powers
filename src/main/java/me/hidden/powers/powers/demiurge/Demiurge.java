package me.hidden.powers.powers.demiurge;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;
import me.hidden.powers.powers.bloodboil.BloodBoil;
import me.hidden.powers.powers.dawnbreaker.DawnBreaker;
import me.hidden.powers.powers.ichor.Ichor;
import me.hidden.powers.powers.requiem.Requiem;
import me.hidden.powers.powers.transfusion.Transfusion;
import me.hidden.powers.powers.vampirism.Vampirism;
import org.bukkit.Location;

import java.util.*;

public final class Demiurge extends Power {

    private DemiurgeTask task;
    private final Set<UUID> moving;

    public Demiurge() {
        super();
        addEvent(DemiurgeListener.class);
        moving = new HashSet<>();
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

    @Override
    public Iterable<Class<? extends Power>> requiredPowers() {
        return List.of(DawnBreaker.class, Requiem.class, Ichor.class);
    }

    public void addMoving(UUID player) {
        moving.add(player);
    }

    public void removeMoving(UUID player) {
        moving.remove(player);
    }

    public boolean isMoving(UUID player) {
        return moving.contains(player);
    }
}
