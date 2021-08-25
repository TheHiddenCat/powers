package me.hidden.powers.powers.pyricarrow;

import me.hidden.powers.Main;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;

public final class PyricArrowListener implements Listener {

    private final PyricArrow power;

    private static final String PYRICARROW_ARROW_KEY = "pyricarrow.arrow.key";


    public PyricArrowListener(PyricArrow power) {
        this.power = power;
    }

    @EventHandler
    public void onArrowShootEvent(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        if (!power.playerHasPower(player.getUniqueId())) return;
        if (!(e.getProjectile() instanceof Arrow arrow)) return;

        arrow.setFireTicks(Integer.MAX_VALUE);
        arrow.setMetadata(PYRICARROW_ARROW_KEY, new FixedMetadataValue(Main.getPlugin(Main.class), true));
        new PyricArrowTask(power, arrow).runTaskTimer(Main.getPlugin(Main.class), 0, 2);
    }

    @EventHandler
    public void onArrowHitEvent(ProjectileHitEvent e) {
        if (!(e.getEntity() instanceof Arrow arrow)) return;
        if (!arrow.hasMetadata(PYRICARROW_ARROW_KEY)) return;

    }
}
