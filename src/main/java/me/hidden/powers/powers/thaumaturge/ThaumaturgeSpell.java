package me.hidden.powers.powers.thaumaturge;

import org.bukkit.event.player.PlayerInteractEvent;

public abstract class ThaumaturgeSpell {
    private final double fluxCost;
    protected ThaumaturgeSpell(double fluxCost) {
        this.fluxCost = fluxCost;
    }
    public double getFluxCost() { return fluxCost; }
    public abstract void execute(Thaumaturge power, PlayerInteractEvent e);
}
