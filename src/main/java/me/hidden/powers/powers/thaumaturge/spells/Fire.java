package me.hidden.powers.powers.thaumaturge.spells;

import me.hidden.powers.Main;
import me.hidden.powers.powers.thaumaturge.Thaumaturge;
import me.hidden.powers.powers.thaumaturge.ThaumaturgeSpell;
import me.hidden.powers.powers.thaumaturge.ThaumaturgeSpellType;
import org.bukkit.Sound;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

public final class Fire extends ThaumaturgeSpell {

    public Fire(double fluxCost) {
        super(fluxCost);
    }

    @Override
    public void launch(Thaumaturge power, PlayerInteractEvent e) {
        var player = e.getPlayer();
        var fireball = player.launchProjectile(SmallFireball.class, player.getEyeLocation().getDirection().normalize());
        fireball.setIsIncendiary(false);
        fireball.setShooter(player);
        fireball.setYield(0);
        fireball.setMetadata(power.getSpellKey(), new FixedMetadataValue(Main.getPlugin(Main.class), ThaumaturgeSpellType.FIRE));
        player.playSound(player.getEyeLocation(), Sound.ENTITY_BLAZE_SHOOT, 0.8f, 0.7f);
    }

    @Override
    public void hit(Thaumaturge power, ProjectileHitEvent e) {

    }
}
