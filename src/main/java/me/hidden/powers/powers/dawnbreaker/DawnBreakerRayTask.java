package me.hidden.powers.powers.dawnbreaker;

import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public final class DawnBreakerRayTask extends BukkitRunnable {

    private final List<LivingEntity> hitEntities;
    private final Player player;
    private final World world;
    private final Location location;
    private final double rayLength;
    private final double rayDamage;
    private int counter;

    public DawnBreakerRayTask(Player player, World world, Location location, double rayLength, double rayDamage) {
        this.player = player;
        this.world = world;
        this.location = location;
        this.rayLength = rayLength;
        this.rayDamage = rayDamage;
        this.counter = 0;
        this.hitEntities = new ArrayList<>();
    }

    @Override
    public void run() {
        if (counter < rayLength) {
            location.add(location.getDirection().multiply(2d));

            var options = new Particle.DustOptions(Color.fromRGB(255, 221, 145), 2.0F);
            world.spawnParticle(Particle.REDSTONE, location, 15, 0.3f,0.3f,0.3f, options);
            world.spawnParticle(Particle.FIREWORKS_SPARK, location, 4, 0.5f, 0.5f, 0.5f, 0);
            world.playSound(location, Sound.BLOCK_GLASS_BREAK, 0.2f, 0.6f);

            var nearby = world.getNearbyEntities(location, 2, 2, 2, (x) -> x instanceof LivingEntity);
            for (var entity : nearby) {
                if (entity == player) continue;
                var livingEntity = (LivingEntity) entity;
                var contains = hitEntities.stream().anyMatch(x -> x == livingEntity);
                if (contains) continue;
                livingEntity.damage(rayDamage);
                world.spawnParticle(Particle.FLASH, livingEntity.getLocation(), 0, 0, 0, 0, 0);
                hitEntities.add(livingEntity);
            }
            counter++;
        }
        else {
            cancel();
        }
    }
}
