package me.hidden.powers.tasks;

import me.hidden.powers.managers.PowerManager;

import me.hidden.powers.powers.Cooldown;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class PowerCooldownsTask extends BukkitRunnable {

    private final PowerManager powerManager;

    public PowerCooldownsTask(PowerManager powerManager) {
        this.powerManager = powerManager;
    }

    public int getTickInterval() {
        return 2;
    }

    @Override
    public void run() {
        for (var power : powerManager.getPowers()) {
            for (var cooldown : power.getCooldowns()) {
                cooldown.decreaseTime(getTickInterval());
                if (cooldown.isDone()) {
                    power.removeCooldown(cooldown);
                }
            }
        }
    }
}
