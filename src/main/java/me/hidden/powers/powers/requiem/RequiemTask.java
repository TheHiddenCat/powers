package me.hidden.powers.powers.requiem;

import me.hidden.powers.powers.bloodboil.BloodBoil;
import me.hidden.powers.util.MathUtils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public final class RequiemTask extends BukkitRunnable {
    private static final int REQUIEM_CASTING_TIME = 180;
    private static final int REQUIEM_TICK_TIME = 2;
    private static final int REQUIEM_DAMAGE_TICK_MOD = 20;

    private final Requiem power;
    private final Player player;
    private final Location castLocation;
    private final List<Location> pentagramPoints;
    private int counter;

    public RequiemTask(Requiem power, Player player) {
        this.power = power;
        this.player = player;
        this.castLocation = player.getLocation();
        this.pentagramPoints = calculatePentagram(player.getLocation());
        this.counter = 0;
    }

    @Override
    public void run() {
        if (player.isDead()) {
            stop();
        }
        if (!player.isSneaking()) {
            stop();
        }
        else {
            if (counter <= REQUIEM_CASTING_TIME) {
                effects();
                player.setWalkSpeed(0);

                if (counter % REQUIEM_DAMAGE_TICK_MOD == 0) {
                    var dust = new Particle.DustOptions(Color.fromRGB(255, 221, 145), 2.0f);
                    for (var entity : player.getNearbyEntities(12, 4, 12)) {
                        if (!(entity instanceof LivingEntity enemy)) continue;
                        if (enemy instanceof Player enemyPlayer) {
                            if (enemyPlayer.getGameMode() == GameMode.CREATIVE) continue;
                        }
                        if (enemy.isDead()) continue;
                        var world = enemy.getWorld();
                        var location = enemy.getEyeLocation();
                        world.spawnParticle(Particle.REDSTONE, location, 15, 0.5f, 0.5f, 0.5f, 0, dust);
                        world.spawnParticle(Particle.FLASH, location, 1, 0, 0, 0);
                        world.playSound(location, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1.2f, 1.0f);
                        enemy.damage(2, player);
                    }
                }
                counter += REQUIEM_TICK_TIME;
            } else {
                stop();
            }
        }
    }

    private void stop() {
        player.getWorld().playSound(player.getEyeLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0f, 0.2f);
        player.setWalkSpeed(0.2f);
        cancel();
    }

    private void effects() {
        renderPentagram();
        renderCircle(5.0d);
    }

    private List<Location> calculatePentagram(Location loc) {
        loc.subtract(new Vector(5, 0, 2));
        final var points = new ArrayList<Location>();
        final var d144 = Math.toRadians(144d);
        var angle = 0d;
        for (var i = 0; i < 5; i++) {
            var x2 = loc.getX() + (Math.cos(angle) * 10.0d);
            var z2 = loc.getZ() + (Math.sin(-angle) * 10.0d);
            var dest = new Location(loc.getWorld(), x2, loc.getY(), z2);
            var dir = MathUtils.getDirection(loc, dest);
            for (var l = 0.0d; l < 10d; l += 0.5d) {
                var add = new Vector().copy(dir).multiply(l);
                var point = loc.clone().add(add);
                points.add(point);
            }
            loc.setX(dest.getX());
            loc.setZ(dest.getZ());
            angle -= d144;
        }
        return points;
    }

    private void renderPentagram() {
        var dust = new Particle.DustOptions(Color.fromRGB(255, 221, 145), 1.25F);
        for (var point : pentagramPoints) {
            point.getWorld().spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
        }
    }

    private void renderCircle(double radius) {
        var dust = new Particle.DustOptions(Color.fromRGB(171, 141, 73), 1.0f);
        for (var d = 0; d <= 50; d += 1.8d) {
            var spawn = new Location(castLocation.getWorld(), castLocation.getX(), castLocation.getY(), castLocation.getZ());
            spawn.setX(castLocation.getX() + Math.cos(d) * radius);
            spawn.setZ(castLocation.getZ() + Math.sin(d) * radius);
            player.getWorld().spawnParticle(Particle.REDSTONE, spawn, 1, 0, 0, 0, 0, dust);
        }
    }
}
