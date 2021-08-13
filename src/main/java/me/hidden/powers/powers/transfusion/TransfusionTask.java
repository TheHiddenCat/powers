package me.hidden.powers.powers.transfusion;

import me.hidden.powers.util.MathUtils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public final class TransfusionTask extends BukkitRunnable {

    private static final int MAX_TIME = 20;

    private final Transfusion power;
    private final Player player;
    private final LivingEntity enemy;
    private final World world;
    private final Vector direction;
    private final Location location;
    private final double distancePerTick;
    private int counter;

    public TransfusionTask(Transfusion power, Player player, LivingEntity enemy) {
        this.power = power;
        this.player = player;
        this.enemy = enemy;
        this.world = enemy.getWorld();
        var start = enemy.getEyeLocation();
        var end = player.getEyeLocation();
        this.location = enemy.getEyeLocation();
        this.direction = MathUtils.getDirection(start, end);
        var distance = start.distance(end);
        this.distancePerTick = distance / MAX_TIME;
        this.counter = 0;
        Bukkit.getLogger().info("" + distance);
        Bukkit.getLogger().info("" + distancePerTick);
    }

    @Override
    public void run() {
        if (counter++ < MAX_TIME) {
            location.add(direction);
            Bukkit.getLogger().info("" + location.getX() + " " + location.getY() + " " + location.getZ());

            var options = new Particle.DustOptions(Color.fromRGB(222, 0, 0), 1.8F);
            world.spawnParticle(Particle.REDSTONE, location, 15, 0.2f,0.2f,0.2f, options);
            enemy.damage(power.getDamagePerTick(), player);
            var healed = player.getHealth() + power.getHealthPerTick();
            var max = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            player.setHealth(MathUtils.clamp(healed, 0, max));
        }
        else {
            cancel();
        }
    }
}
