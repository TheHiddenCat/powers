package me.hidden.powers.powers.backstab;

import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public final class BackstabListener implements Listener {

    private final Backstab power;
    private final Particle.DustOptions options;

    public BackstabListener(Backstab power) {
        this.power = power;
        this.options = new Particle.DustOptions(Color.fromRGB(222, 0, 0), 1.2F);
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (! (e.getDamager() instanceof Player player)) return;
        if (! (e.getEntity() instanceof LivingEntity enemy)) return;
        if (!power.playerHasPower(player.getUniqueId())) return;

        var playerDirection = player.getLocation().getDirection();
        var enemyDirection = enemy.getLocation().getDirection();
        if (playerDirection.dot(enemyDirection) > 0) {
            var damage = e.getDamage() * power.getDamageModifier();
            e.setDamage(damage);
            var world = enemy.getWorld();
            var location = enemy.getEyeLocation();
            world.spawnParticle(Particle.BLOCK_CRACK, location, 7, 0.3f, 0.3f, 0.3f, 0, Material.REDSTONE_BLOCK.createBlockData());
            world.playSound(location, Sound.BLOCK_ANVIL_PLACE, 0.1f, 1.9f);
        }
    }
}
