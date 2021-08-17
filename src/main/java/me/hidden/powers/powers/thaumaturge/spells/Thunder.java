package me.hidden.powers.powers.thaumaturge.spells;

import me.hidden.powers.Main;
import me.hidden.powers.powers.thaumaturge.Thaumaturge;
import me.hidden.powers.powers.thaumaturge.ThaumaturgeSpell;

import me.hidden.powers.powers.thaumaturge.ThaumaturgeSpellType;

import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

public final class Thunder extends ThaumaturgeSpell {

    public Thunder(double fluxCost) {
        super(fluxCost);
    }

    @Override
    public void launch(Thaumaturge power, PlayerInteractEvent e) {
        var nearby = e.getPlayer().getNearbyEntities(10, 3, 10);
        for (var entity : nearby) {
            if (!(entity instanceof LivingEntity livingEntity)) continue;
            if (!livingEntity.hasLineOfSight(e.getPlayer())) continue;
            var location = livingEntity.getLocation();
            var block = location.getBlock();
            var lightning = block.getWorld().strikeLightning(location);
            lightning.setMetadata(power.getSpellKey(), new FixedMetadataValue(Main.getPlugin(Main.class), ThaumaturgeSpellType.THUNDER));
            lightning.setMetadata(power.getThunderKey(), new FixedMetadataValue(Main.getPlugin(Main.class), e.getPlayer().getUniqueId()));
            lightning.getWorld().playSound(lightning.getLocation(), Sound.ENTITY_BEE_DEATH, 1.8f, 0.6f);
        }
    }

    @Override
    public void hit(Thaumaturge power, ProjectileHitEvent e) {

    }
}
