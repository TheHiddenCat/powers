package me.hidden.powers.powers.dawnbreaker;

import me.hidden.powers.Main;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.Damageable;

public final class DawnBreakerListener implements Listener {

    private final DawnBreaker power;
    private final Main plugin;

    public DawnBreakerListener(DawnBreaker power) {
        this.power = power;
        this.plugin = Main.getPlugin(Main.class);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        var player = e.getPlayer();
        if (!power.playerHasPower(player.getUniqueId())) return;
        var item = e.getItem();
        if (item == null) return;
        if (item.getType() != Material.GOLDEN_SWORD) return;
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            shootRay(player);
        }
        else if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            sweep(player);
        }
        damageSword(player);
    }

    @EventHandler
    public void onPlayerAttackEvent(EntityDamageByEntityEvent e) {
        if (! (e.getDamager() instanceof Player player)) return;
        if (! power.playerHasPower(player.getUniqueId())) return;
        var inventory = player.getInventory();
        var item = inventory.getItemInMainHand();
        if (item.getType() != Material.GOLDEN_SWORD) return;
        shootRay(player);
        damageSword(player);
    }

    private void damageSword(Player player) {
        if (player.getGameMode() == GameMode.CREATIVE) return;
        var sword = player.getInventory().getItemInMainHand();
        var metadata = sword.getItemMeta();
        if (metadata instanceof Damageable damageable) {
            damageable.setDamage(damageable.getDamage() + 1);
            if (damageable.getDamage() >= sword.getType().getMaxDurability()) {
                player.getInventory().removeItem(sword);
                return;
            }
            sword.setItemMeta(metadata);
        }
    }

    private void shootRay(Player player) {
        var world = player.getWorld();
        var location = player.getEyeLocation();
        new DawnBreakerRayTask(player, world, location, power.getRayLength(), power.getRayDamage()).runTaskTimer(plugin, 0, 1);
    }

    private void sweep(Player player) {
        var world = player.getWorld();
        var location = player.getEyeLocation();
        new DawnBreakerSweepTask(player, world, location, power.getSweepDamage(), power.getSweepForce()).runTaskTimer(plugin, 0, 1);
    }
}
