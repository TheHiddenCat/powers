package me.hidden.powers.powers.paladin;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class PaladinListener implements Listener {

    private final Paladin power;

    public PaladinListener(Paladin power) {
        this.power = power;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        if (!power.playerHasPower(player.getUniqueId())) return;
        if (player.getInventory().getItemInOffHand().getType() != Material.SHIELD) return;
        if (!(e.getDamager() instanceof LivingEntity damager)) return;
        damager.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0));
        damager.getWorld().spawnParticle(Particle.FLASH, damager.getEyeLocation(), 1);
    }
}
