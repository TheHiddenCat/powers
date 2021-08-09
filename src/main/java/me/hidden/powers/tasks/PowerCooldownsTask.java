package me.hidden.powers.tasks;

import me.hidden.powers.managers.PowerManager;

import me.hidden.powers.powers.Cooldown;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class PowerCooldownsTask extends BukkitRunnable {

    private final Plugin plugin;
    private final PowerManager powerManager;
    private final List<Cooldown> done;

    public PowerCooldownsTask(Plugin plugin, PowerManager powerManager) {
        this.plugin = plugin;
        this.powerManager = powerManager;
        this.done = new ArrayList<>();
    }

    public int getTickInterval() {
        return 2;
    }

    @Override
    public void run() {
        done.clear();
        var players = plugin.getServer().getOnlinePlayers();
        for (var player : players) {
            for (var power : powerManager.getPowers(player.getUniqueId())) {
                for (var cooldown : power.getCooldowns()) {
                    cooldown.decreaseTime(getTickInterval());
                    if (cooldown.isDone()) {
                        done.add(cooldown);
                    }
                }
                for (var cooldown : done) {
                    power.removeCooldown(cooldown);
                }
            }
        }
    }
}
