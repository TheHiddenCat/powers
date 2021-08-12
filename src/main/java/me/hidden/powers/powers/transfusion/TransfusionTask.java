package me.hidden.powers.powers.transfusion;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public final class TransfusionTask extends BukkitRunnable {

    private static final int MAX_TIME = 20;

    private final Transfusion power;
    private final Player player;
    private final LivingEntity enemy;
    private final World world;
    private int counter;

    public TransfusionTask(Transfusion power, Player player, LivingEntity enemy) {
        this.power = power;
        this.player = player;
        this.enemy = enemy;
        this.world = enemy.getWorld();
        this.counter = 0;
    }

    @Override
    public void run() {
        if (counter++ < MAX_TIME) {
            var location = enemy.getEyeLocation();
            var options = new Particle.DustOptions(Color.fromRGB(222, 0, 0), 1.6F);
            world.spawnParticle(Particle.REDSTONE, location, 15, 0,0,0, options);
            enemy.damage(power.getDamage(), player);
            player.setSaturation(player.getSaturation() + power.getSaturation());
            Bukkit.getLogger().info("" + player.getSaturation());
        }
        else {
            player.setFoodLevel(player.getFoodLevel() + 2);
            cancel();
        }
    }
}
