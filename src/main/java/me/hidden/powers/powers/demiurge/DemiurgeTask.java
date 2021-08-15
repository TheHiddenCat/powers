package me.hidden.powers.powers.demiurge;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public final class DemiurgeTask extends BukkitRunnable {

    private final Demiurge power;
    private final Particle.DustOptions options;
    private final Random random;

    public DemiurgeTask(Demiurge power) {
        this.power = power;
        this.options = new Particle.DustOptions(Color.fromRGB(255, 221, 145), 0.5F);
        this.random = new Random();
    }

    @Override
    public void run() {
        var uuids = power.getPlayers();
        for (var uuid : uuids) {
            var player = Bukkit.getPlayer(uuid);
            if (player == null) continue;
            renderCrown(player);
        }
    }

    private void renderCrown(Player player) {
        var location = player.getEyeLocation().add(new Vector(0d, 0.5d, 0d));
        for (var angle = 0d; angle <= 15d; angle += 0.5d) {
            var point = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
            point.setX(location.getX() + Math.cos(angle) * 0.5d);
            point.setZ(location.getZ() + Math.sin(angle) * 0.5d);
            player.getWorld().spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, options);
            if (random.nextInt(150) == 0) {
                player.getWorld().spawnParticle(Particle.WHITE_ASH, point, 3, 0.8f,0.8f,0.8f);
            }
        }
    }
}
