package me.hidden.powers.powers.bloodboil;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Cooldown;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public final class BloodBoilListener implements Listener {

    private static final String BLOODBOIL_COOLDOWN_KEY = "bloodboil.cooldown";
    private static final int BLOODBOIL_COOLDOWN = 300;

    private final BloodBoil power;

    public BloodBoilListener(BloodBoil power) {
        this.power = power;
    }

    @EventHandler
    public void onPlayerInteractWithEntity(PlayerInteractEntityEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();
        var hand = e.getHand();
        if (hand != EquipmentSlot.HAND) return;
        if (!power.playerHasPower(uuid)) return;
        if (!player.isSneaking()) return;
        if (!(e.getRightClicked() instanceof LivingEntity enemy)) return;
        if (power.onCooldown(uuid, BLOODBOIL_COOLDOWN_KEY)) return;
        var inventory = player.getInventory();
        var item = inventory.getItemInMainHand();
        if (item.getType() != Material.QUARTZ) return;

        new BloodBoilTask(power, player, enemy).runTaskTimer(Main.getPlugin(Main.class), 0, 2);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 0.8f, 0.1f);
        power.addCooldown(new Cooldown(uuid, BLOODBOIL_COOLDOWN_KEY, BLOODBOIL_COOLDOWN, false));
    }
}
