package me.hidden.powers.listeners;

import me.hidden.powers.models.BowMaster;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.Random;

public final class BowMasterListener implements Listener {

    private final BowMaster bowMaster;
    private final Random random;

    public BowMasterListener(BowMaster bowMaster) {
        this.bowMaster = bowMaster;
        this.random = new Random();
    }

    @EventHandler
    public void onPlayerShootEvent(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        if (!bowMaster.playerHasPower(player.getUniqueId())) return;

        var arrow = (Arrow) e.getProjectile();
        var velocity = arrow.getVelocity().multiply(bowMaster.getArrowSpeedModifier());
        arrow.setVelocity(velocity);

        var arrowRecovered = random.nextDouble();
        if (arrowRecovered > bowMaster.getArrowRecoveryModifier()) {
            e.setConsumeItem(false);
        }

        arrow.setDamage(arrow.getDamage() * bowMaster.getDamageModifier());
    }
}
