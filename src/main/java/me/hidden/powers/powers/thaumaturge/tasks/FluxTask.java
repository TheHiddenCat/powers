package me.hidden.powers.powers.thaumaturge.tasks;

import me.hidden.powers.powers.thaumaturge.Thaumaturge;
import org.bukkit.scheduler.BukkitRunnable;

public final class FluxTask extends BukkitRunnable {

    private final Thaumaturge power;

    public FluxTask(Thaumaturge power) {
        this.power = power;
    }

    @Override
    public void run() {
        for (var uuid : power.getPlayers()) {
            power.incrementFlux(uuid, -1);
            if (power.isOverloaded(uuid)) {
                var progress = power.getFluxProgress(uuid);
                if (progress <= 0.1d) {
                    power.removeOverloaded(uuid);
                }
            }
        }
    }
}
