package me.hidden.powers.powers.quickreflex;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public final class QuickReflexListener implements Listener {

    private final QuickReflex power;

    public QuickReflexListener(QuickReflex power) {
        this.power = power;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        if (!power.playerHasPower(player.getUniqueId())) return;
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) return;
        if (!(e.getDamager() instanceof Projectile projectile)) return;

        var shooter = (LivingEntity) projectile.getShooter();
        if (shooter == null) return;

        var location = player.getEyeLocation();
        var shooterLocation = shooter.getEyeLocation().toVector().subtract(location.toVector());
        var dot = shooterLocation.normalize().dot(location.getDirection());
        var playerIsLooking = dot > 0.99d;

        if (!playerIsLooking) return;

        var material = getMaterialFromProjectile(projectile);
        if (material == null) return;

        player.getInventory().addItem(new ItemStack(material));
        player.playSound(location, Sound.ENTITY_ARROW_SHOOT, 0.8f, 0.8f);
        player.getWorld().spawnParticle(Particle.SWEEP_ATTACK, location, 1);
        projectile.remove();
        e.setCancelled(true);
    }

    private Material getMaterialFromProjectile(Projectile projectile) {
        Material material = null;

        if (projectile instanceof Arrow) {
            material = Material.ARROW;
        }
        else if (projectile instanceof Snowball) {
            material = Material.SNOWBALL;
        }
        else if (projectile instanceof Egg) {
            material = Material.EGG;
        }

        return material;
    }
}
