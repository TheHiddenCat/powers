package me.hidden.powers.powers.thaumaturge;

import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class ThaumaturgeSpell {
    private final double fluxCost;
    protected ThaumaturgeSpell(double fluxCost) {
        this.fluxCost = fluxCost;
    }
    public double getFluxCost() { return fluxCost; }
    public abstract void launch(Thaumaturge power, PlayerInteractEvent e);
    public abstract void hit(Thaumaturge power, ProjectileHitEvent e);
}
