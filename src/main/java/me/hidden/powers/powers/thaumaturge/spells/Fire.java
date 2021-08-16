package me.hidden.powers.powers.thaumaturge.spells;

import me.hidden.powers.powers.thaumaturge.Thaumaturge;
import me.hidden.powers.powers.thaumaturge.ThaumaturgeSpell;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.player.PlayerInteractEvent;

public final class Fire extends ThaumaturgeSpell {
    public Fire(double fluxCost) {
        super(fluxCost);
    }

    @Override
    public void execute(Thaumaturge power, PlayerInteractEvent e) {
        var player = e.getPlayer();
        var fireball = player.launchProjectile(SmallFireball.class, player.getLocation().getDirection().normalize());
        fireball.setIsIncendiary(false);
        fireball.setShooter(player);
        fireball.setYield(0);
    }
}
