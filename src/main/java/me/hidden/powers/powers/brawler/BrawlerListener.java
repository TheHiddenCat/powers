package me.hidden.powers.powers.brawler;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public final class BrawlerListener implements Listener {

    private final Brawler power;

    public BrawlerListener(Brawler power) {
        this.power = power;
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (! (e.getDamager() instanceof Player player)) return;
        if (!power.playerHasPower(player.getUniqueId())) return;
        if (! (e.getEntity() instanceof LivingEntity enemy)) return;
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) return;
        var damage = e.getDamage() * power.getDamageModifier();
        e.setDamage(damage);
        enemy.getWorld().spawnParticle(Particle.BLOCK_CRACK, enemy.getLocation().add(0, 1, 0), 4, 0.3f,0.3f,0.3f, Material.REDSTONE_BLOCK.createBlockData());
        enemy.getWorld().playSound(enemy.getLocation(), Sound.ENTITY_PLAYER_ATTACK_STRONG, 0.6f, 0.6f);
    }
}
