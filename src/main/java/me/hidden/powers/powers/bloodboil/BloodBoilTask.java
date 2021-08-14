package me.hidden.powers.powers.bloodboil;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

public final class BloodBoilTask extends BukkitRunnable {

    private static int BLOODBOIL_CASTING_TIME = 200;
    private static int BLOODBOIL_TICK_TIME = 5;
    private static float BLOODBOIL_MAX_HEIGHT = 5;
    private static float BLOODBOIL_HEIGHT_INCREMENT = .5f;

    private final BloodBoil power;
    private final LivingEntity enemy;
    private final Player player;
    private final Location location;
    private final Location cast;
    private int counter;
    private float height;

    public BloodBoilTask(BloodBoil power, Player player, LivingEntity enemy) {
        this.power = power;
        this.player = player;
        this.cast = player.getLocation();
        this.enemy = enemy;
        this.location = enemy.getLocation();
        this.counter = 0;
    }

    @Override
    public void run() {
        var pl = player.getLocation();
        if (pl.getX() != cast.getX() ||
            pl.getY() != cast.getY() ||
            pl.getZ() != cast.getZ()) {
            player.getWorld().playSound(pl, Sound.ENTITY_ENDERMAN_TELEPORT, 0.7f, 0.8f);
            cancel();
        }
        else {
            if (counter <= BLOODBOIL_CASTING_TIME) {
                if (height < BLOODBOIL_MAX_HEIGHT) {
                    height += BLOODBOIL_HEIGHT_INCREMENT;
                }
                location.setY(location.getY() + height);
                enemy.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
                counter += BLOODBOIL_TICK_TIME;
            } else {
                enemy.damage(50);
                enemy.getWorld().playSound(enemy.getEyeLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 0.8f);
                cancel();
            }
        }
    }
}
