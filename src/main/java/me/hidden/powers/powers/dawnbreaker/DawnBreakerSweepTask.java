package me.hidden.powers.powers.dawnbreaker;

import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public final class DawnBreakerSweepTask extends BukkitRunnable {

    private final List<LivingEntity> hitEntities;
    private final Player player;
    private final World world;
    private final Location location;
    private final double sweepDamage;
    private final double sweepForce;
    private double counter;

    public DawnBreakerSweepTask(Player player, World world, Location location, double sweepDamage, double sweepForce) {
        this.player = player;
        this.world = world;
        this.location = location;
        this.sweepDamage = sweepDamage;
        this.sweepForce = sweepForce;
        this.counter = 0;
        this.hitEntities = new ArrayList<>();
    }

    @Override
    public void run() {
        if (counter <= 7) {
            var spawn = new Location(world, location.getX(), location.getY(), location.getZ());
            spawn.setX(location.getX() + Math.cos(counter) * 3.0d);
            spawn.setZ(location.getZ() + Math.sin(counter) * 3.0d);

            var options = new Particle.DustOptions(Color.fromRGB(255, 221, 145), 2.0F);
            world.spawnParticle(Particle.REDSTONE, spawn, 12, 0.3f,0.3f,0.3f, options);
            world.spawnParticle(Particle.FIREWORKS_SPARK, spawn, 4, 0.5f, 0.5f, 0.5f, 0);
            world.playSound(location, Sound.BLOCK_GLASS_BREAK, 0.2f, 0.6f);

            var rotation = player.getLocation();
            rotation.setYaw(player.getLocation().getYaw() + 36);
            player.teleport(rotation);

            var nearby = world.getNearbyEntities(location, 3, 3, 3, (x) -> x instanceof LivingEntity);
            for (var entity : nearby) {
                if (entity == player) continue;
                var livingEntity = (LivingEntity) entity;
                var contains = hitEntities.stream().anyMatch(x -> x == livingEntity);
                if (contains) continue;
                var unit = livingEntity.getLocation().toVector().subtract(location.toVector()).normalize();
                livingEntity.setVelocity(unit.multiply(sweepForce));
                livingEntity.damage(sweepDamage, player);
                world.spawnParticle(Particle.FLASH, livingEntity.getLocation(), 0, 0f, 0f, 0f, 0);
                world.spawnParticle(Particle.SWEEP_ATTACK, livingEntity.getLocation(), 1, 0f,0f,0f);
                hitEntities.add(livingEntity);
            }

            counter+=0.7d;
        }
        else {
            cancel();
        }
    }
}
