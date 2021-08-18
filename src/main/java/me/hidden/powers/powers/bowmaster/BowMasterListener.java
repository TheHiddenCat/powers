package me.hidden.powers.powers.bowmaster;

import me.hidden.powers.Main;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public final class BowMasterListener implements Listener {

    private final BowMaster bowMaster;
    private final Random random;
    private static final String BOWMASTER_ENTITY_EXPLODED = "bowmaster.entity.exploded";

    public BowMasterListener(BowMaster bowMaster) {
        this.bowMaster = bowMaster;
        this.random = new Random();
    }

    @EventHandler
    public void onPlayerShootEvent(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        if (!bowMaster.playerHasPower(player.getUniqueId())) return;

        var arrow = (Arrow) e.getProjectile();
        var velocity = arrow.getVelocity().multiply(bowMaster.getArrowVelocity());
        arrow.setVelocity(velocity);

        var arrowRecovered = random.nextDouble();
        if (arrowRecovered > bowMaster.getArrowNoConsume()) {
            e.setConsumeItem(false);
        }

        arrow.setDamage(arrow.getDamage() * bowMaster.getArrowDamage());
    }

    @EventHandler
    public void onEntityDamageByArrow(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Arrow arrow)) return;
        if (!(arrow.getShooter() instanceof Player player)) return;
        if (!bowMaster.playerHasPower(player.getUniqueId())) return;
        if (!(e.getEntity() instanceof LivingEntity enemy)) return;

        var arrowsAmount = enemy.getArrowsInBody();
        var metadata = enemy.getMetadata(BOWMASTER_ENTITY_EXPLODED);
        var didExplodeBefore = metadata.size() > 0 && metadata.get(0).asBoolean();
        if (!didExplodeBefore) {
            if (arrowsAmount + 1 >= bowMaster.getArrowsBodyAmount()) {
                e.setDamage(e.getDamage() + bowMaster.getArrowsBodyExplodeDamage());
                var location = enemy.getEyeLocation();
                var world = enemy.getWorld();
                enemy.setArrowsInBody(0);
                enemy.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, bowMaster.getArrowExplodeWeaknessDuration(), bowMaster.getArrowExplodeWeaknessAmplifier(), true, true, true));
                world.playSound(location, Sound.BLOCK_ANVIL_LAND, 0.2f, 1.8f);
                world.spawnParticle(Particle.FLASH, location, 1);
                enemy.setMetadata(BOWMASTER_ENTITY_EXPLODED, new FixedMetadataValue(Main.getPlugin(Main.class), true));
            }
        }
    }
}
