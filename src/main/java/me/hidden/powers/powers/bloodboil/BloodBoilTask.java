package me.hidden.powers.powers.bloodboil;

import me.hidden.powers.util.MathUtils;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public final class BloodBoilTask extends BukkitRunnable {

    private static final int BLOODBOIL_CASTING_TIME = 200;
    private static final int BLOODBOIL_TICK_TIME = 2;
    private static final float BLOODBOIL_MAX_HEIGHT = 2.0f;

    private static final int BLOODBOIL_CIRCLE_1 = 0;
    private static final int BLOODBOIL_CIRCLE_2 = 66;
    private static final int BLOODBOIL_CIRCLE_3 = 132;

    private final BloodBoil power;
    private final LivingEntity enemy;
    private final Player player;
    private final Location castLocation;
    private final List<Location> pentagramPoints;
    private int counter;

    public BloodBoilTask(BloodBoil power, Player player, LivingEntity enemy) {
        this.power = power;
        this.player = player;
        this.enemy = enemy;
        this.castLocation = enemy.getLocation();
        this.counter = 0;
        this.pentagramPoints = calculatePentagram(enemy.getLocation());
    }

    @Override
    public void run() {
        if (enemy.isDead()) {
            stop();
        }
        if (!player.isSneaking()) {
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.7f, 0.4f);
            stop();
        }
        else {
            if (counter <= BLOODBOIL_CASTING_TIME) {
                effects();
                enemy.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 0, false, false, false));
                enemy.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 1, false, false, false));
                var currentLocation = enemy.getLocation();
                currentLocation.setX(castLocation.getX());
                currentLocation.setZ(castLocation.getZ());
                if (currentLocation.getY() > castLocation.getY() + BLOODBOIL_MAX_HEIGHT) {
                    currentLocation.setY(currentLocation.getY() - 0.2f);
                }
                enemy.teleport(currentLocation);
                counter += BLOODBOIL_TICK_TIME;
            } else {
                enemy.damage(power.getDamage(), player);
                var dust = new Particle.DustOptions(Color.fromRGB(222, 0, 0), 1.8f);
                enemy.getWorld().playSound(enemy.getEyeLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 0.3f);
                enemy.getWorld().playSound(enemy.getEyeLocation(), Sound.ENTITY_PLAYER_BREATH, 1.0f, 0.1f);
                enemy.getWorld().spawnParticle(Particle.BLOCK_CRACK, enemy.getEyeLocation(), 50, 0.8f, 0.8f, 0.8f, Material.REDSTONE_BLOCK.createBlockData());
                enemy.getWorld().spawnParticle(Particle.REDSTONE, enemy.getEyeLocation(), 50, 0.8f, 0.8f, 0.8f, dust);
                stop();
            }
        }
    }

    private void stop() {
        enemy.removePotionEffect(PotionEffectType.LEVITATION);
        enemy.removePotionEffect(PotionEffectType.WEAKNESS);
        cancel();
    }

    private void effects() {
        renderPentagram();

        if (counter >= BLOODBOIL_CIRCLE_1) {
            renderCircle(enemy, 4.0d);
        }
        if (counter >= BLOODBOIL_CIRCLE_2) {
            renderCircle(enemy, 3.0d);
        }
        if (counter >= BLOODBOIL_CIRCLE_3) {
            renderCircle(enemy, 2.0d);
        }
    }

    private List<Location> calculatePentagram(Location loc) {
        loc.subtract(new Vector(5, 0, 2));
        final var points = new ArrayList<Location>();
        final var d144 = Math.toRadians(144);
        var angle = 0d;
        for (var i = 0; i < 5; i++) {
            var x2 = loc.getX() + (Math.cos(angle) * 10.0d);
            var z2 = loc.getZ() + (Math.sin(-angle) * 10.0d);
            var dest = new Location(loc.getWorld(), x2, loc.getY(), z2);
            var dir = MathUtils.getDirection(loc, dest);
            for (double l = 0; l < 10; l += 0.5) {
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
        var dust = new Particle.DustOptions(Color.fromRGB(222, 0, 0), 1.25f);
        for (var point : pentagramPoints) {
            point.getWorld().spawnParticle(Particle.REDSTONE, point, 1, 0, 0, 0, 0, dust);
        }
    }

    private void renderCircle(LivingEntity livingEntity, double radius) {
        var dust = new Particle.DustOptions(Color.fromRGB(122, 29, 29), 1);
        for (var d = 0; d <= 50; d += 1.8d) {
            var spawn = new Location(castLocation.getWorld(), castLocation.getX(), castLocation.getY() + 1.0f, castLocation.getZ());
            spawn.setX(castLocation.getX() + Math.cos(d) * radius);
            spawn.setZ(castLocation.getZ() + Math.sin(d) * radius);
            livingEntity.getWorld().spawnParticle(Particle.REDSTONE, spawn, 1, 0, 0, 0, 0, dust);
        }
    }
}
