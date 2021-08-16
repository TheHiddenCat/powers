package me.hidden.powers.powers.thaumaturge.spells;

import me.hidden.powers.powers.thaumaturge.Thaumaturge;
import me.hidden.powers.powers.thaumaturge.ThaumaturgeSpell;
import org.bukkit.FluidCollisionMode;
import org.bukkit.event.player.PlayerInteractEvent;

public final class Thunder extends ThaumaturgeSpell {
    public Thunder(double fluxCost) {
        super(fluxCost);
    }

    @Override
    public void execute(Thaumaturge power, PlayerInteractEvent e) {
        var block = e.getPlayer().getTargetBlockExact(20, FluidCollisionMode.NEVER);
        if (block == null) return;
        if (block.getLightFromSky() < 15) return;
        block.getWorld().strikeLightning(block.getLocation());
    }
}
