package me.hidden.powers.powers.thaumaturge.spells;

import me.hidden.powers.Main;
import me.hidden.powers.powers.thaumaturge.Thaumaturge;
import me.hidden.powers.powers.thaumaturge.ThaumaturgeSpell;
import me.hidden.powers.powers.thaumaturge.ThaumaturgeSpellType;
import org.bukkit.FluidCollisionMode;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

public final class Thunder extends ThaumaturgeSpell {

    public Thunder(double fluxCost) {
        super(fluxCost);
    }

    @Override
    public void launch(Thaumaturge power, PlayerInteractEvent e) {
        var block = e.getPlayer().getTargetBlockExact(10, FluidCollisionMode.NEVER);
        if (block == null) return;
        if (block.getLightFromSky() < 15) return;
        var lightingStrike = block.getWorld().spawn(block.getLocation(), LightningStrike.class);
        lightingStrike.setMetadata(power.getSpellKey(), new FixedMetadataValue(Main.getPlugin(Main.class), ThaumaturgeSpellType.THUNDER));
    }

    @Override
    public void hit(Thaumaturge power, ProjectileHitEvent e) {

    }
}
