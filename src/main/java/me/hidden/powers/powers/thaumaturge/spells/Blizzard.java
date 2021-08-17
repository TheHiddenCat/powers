package me.hidden.powers.powers.thaumaturge.spells;

import me.hidden.powers.Main;
import me.hidden.powers.powers.thaumaturge.Thaumaturge;
import me.hidden.powers.powers.thaumaturge.ThaumaturgeSpell;
import me.hidden.powers.powers.thaumaturge.ThaumaturgeSpellType;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Blizzard extends ThaumaturgeSpell {

    public Blizzard(double fluxCost) {
        super(fluxCost);
    }

    @Override
    public void launch(Thaumaturge power, PlayerInteractEvent e) {
        var player = e.getPlayer();
        var snowball = player.launchProjectile(Snowball.class, player.getEyeLocation().getDirection().normalize());
        snowball.setShooter(player);
        snowball.setVelocity(snowball.getVelocity().multiply(2.0d));
        snowball.setMetadata(power.getSpellKey(), new FixedMetadataValue(Main.getPlugin(Main.class), ThaumaturgeSpellType.BLIZZARD));
        player.playSound(player.getEyeLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 0.5f, 0.8f);
    }

    @Override
    public void hit(Thaumaturge power, ProjectileHitEvent e) {
        var projectile = e.getEntity();
        var entity = e.getHitEntity();
        var location = projectile.getLocation();
        var shooter = (Entity) projectile.getShooter();
        var world = location.getWorld();
        if (world == null) return;

        var range = power.getBlizzardAoe();
        var options = new Particle.DustOptions(Color.fromRGB(176, 214, 245), 2.0F);
        for (int i = 0; i < 10; i++) {
            world.spawnParticle(Particle.REDSTONE, location, 5, range, range, range, options);
            world.spawnParticle(Particle.SNOW_SHOVEL, location, 5, range,range,range);
        }

        var nearby = world.getNearbyEntities(location,range, range, range);
        if (entity != null) {
            nearby.add(entity);
        }
        for (var hit : nearby) {
            if (hit == shooter) continue;
            if (hit instanceof LivingEntity livingEntity) {
                var pos = livingEntity.getEyeLocation();
                livingEntity.damage(power.getBlizzardDamage(), shooter);
                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, power.getBlizzardSlowDuration(), power.getBlizzardSlowAmplifier(), false, true, true));
                livingEntity.getWorld().spawnParticle(Particle.BLOCK_CRACK, pos, 10, 0, 0, 0, Material.SNOW_BLOCK.createBlockData());
                livingEntity.getWorld().playSound(pos, Sound.BLOCK_GLASS_BREAK, 0.5f, 0.1f);
            }
        }
    }
}
