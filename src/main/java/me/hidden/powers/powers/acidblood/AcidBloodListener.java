package me.hidden.powers.powers.acidblood;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class AcidBloodListener implements Listener {

    private final AcidBlood power;

    public AcidBloodListener(AcidBlood power) {
        this.power = power;
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (! (e.getEntity() instanceof Player player)) {
            return;
        }
        if (! (e.getDamager() instanceof LivingEntity enemy)) {
            return;
        }
        if (!power.playerHasPower(player.getUniqueId())) {
            return;
        }
        if (!enemy.hasPotionEffect(PotionEffectType.POISON)) {
            var potionEffect = new PotionEffect(PotionEffectType.POISON, power.getPoisonDuration(), power.getPoisonModifier(), true, true, true);
            enemy.addPotionEffect(potionEffect);
        }
    }
}
