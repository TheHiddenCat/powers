package me.hidden.powers.powers.transfusion;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class TransfusionListener implements Listener {

    private final Transfusion power;

    private static final String TRANSFUSION_COOLDOWN_KEY = "transfusion.suck";
    private static final int TRANSFUSION_COOLDOWN = 40;

    public TransfusionListener(Transfusion power) {
        this.power = power;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (e.getItem() == null) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getItem().getType() != Material.FERMENTED_SPIDER_EYE) return;

        var player = e.getPlayer();
        if (! power.playerHasPower(player.getUniqueId())) return;
        if ( power.onCooldown(player.getUniqueId(), TRANSFUSION_COOLDOWN_KEY)) return;

        var d = power.getDistance();
        var entities = player.getNearbyEntities(d, d, d);
        var hit = 0;
        for (var entity : entities) {
            if (hit >= power.getMaxEnemiesHit()) break;
            if (! (entity instanceof LivingEntity enemy)) continue;
            if (player.hasLineOfSight(entity)) {
                new TransfusionTask(power, player, enemy).runTaskTimer(Main.getPlugin(Main.class), 0, 1);
                enemy.damage(power.getDamage(), player);
                hit++;
            }
        }
        if (hit > 0) {
            power.addCooldown(new Cooldown(player.getUniqueId(), TRANSFUSION_COOLDOWN_KEY, TRANSFUSION_COOLDOWN));
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 0.5f, 1.2f);
        }
    }
}
