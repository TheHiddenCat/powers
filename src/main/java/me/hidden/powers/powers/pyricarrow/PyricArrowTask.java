package me.hidden.powers.powers.pyricarrow;

import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.scheduler.BukkitRunnable;

public final class PyricArrowTask extends BukkitRunnable {

    private final PyricArrow power;
    private final Arrow arrow;

    private static final int ARROW_EFFECT_MAX_TIME = 800;

    private int counter;

    public PyricArrowTask(PyricArrow power, Arrow arrow) {
        this.power = power;
        this.arrow = arrow;
        this.counter = 0;
    }

    @Override
    public void run() {
        if (counter < ARROW_EFFECT_MAX_TIME || arrow.isInBlock() || arrow.isInWater()) {
            var location = arrow.getLocation();
            var world = arrow.getWorld();
            world.spawnParticle(Particle.FLAME, location, 0, 0.2f, 0.2f, 0.2f, 0);
            counter++;
        }
        else {
            cancel();
        }
    }
}
