package me.hidden.powers.powers.transfusion;

import me.hidden.powers.util.MathUtils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public final class TransfusionTask extends BukkitRunnable {

    private static final int MAX_TIME = 200;
    private static final double MAX_VELOCITY = 3.0d;
    private static final double VELOCITY_INCREMENT = 0.01d;

    private final Transfusion power;
    private final Player player;
    private final World world;
    private final Location location;
    private int counter;
    private double velocity;

    public TransfusionTask(Transfusion power, Player player, LivingEntity enemy) {
        this.power = power;
        this.player = player;
        this.world = enemy.getWorld();
        this.location = enemy.getEyeLocation();
        this.counter = 0;
        this.velocity = 0.6d;
    }

    @Override
    public void run() {
        if (counter++ < MAX_TIME) {
            var direction = MathUtils.getDirection(location, player.getEyeLocation()).multiply(velocity);
            location.add(direction);
            var options = new Particle.DustOptions(Color.fromRGB(222, 0, 0), 1.6F);
            world.spawnParticle(Particle.REDSTONE, location, 15, 0.2f,0.2f,0.2f, options);
            world.spawnParticle(Particle.BLOCK_CRACK, location, 3, 0.2f,0.2f,0.2f, Material.REDSTONE_BLOCK.createBlockData());
            velocity += VELOCITY_INCREMENT;
            velocity = MathUtils.clamp(velocity, 0.0d, MAX_VELOCITY);
            if (player.getBoundingBox().contains(location.toVector())) {
                hit();
            }
        }
        else {
            hit();
        }
    }

    private void hit() {
        var heal = player.getHealth() + power.getHealth();
        var max = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        player.setHealth(MathUtils.clamp(heal, 0, max));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1.0f, 1.0f);
        cancel();
    }
}
