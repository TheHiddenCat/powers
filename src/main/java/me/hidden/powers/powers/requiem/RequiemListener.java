package me.hidden.powers.powers.requiem;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Cooldown;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public final class RequiemListener implements Listener {

    private final Requiem power;

    private static final String REQUIEM_COOLDOWN_KEY = "requiem.cooldown";
    private static final int REQUIEM_COOLDOWN = 600;

    public RequiemListener(Requiem power) {
        this.power = power;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();
        var hand = e.getHand();
        if (hand != EquipmentSlot.HAND) return;
        if (!power.playerHasPower(uuid)) return;
        if (!player.isSneaking()) return;

        var inventory = player.getInventory();
        var item = inventory.getItemInMainHand();
        if (item.getType() != Material.AMETHYST_SHARD) return;
        if (power.onCooldown(uuid, REQUIEM_COOLDOWN_KEY)) return;

        new RequiemTask(power, player).runTaskTimer(Main.getPlugin(Main.class), 0, 2);

        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 0.7f, 2.0f);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 0.5f, 0.1f);
        power.addCooldown(new Cooldown(uuid, REQUIEM_COOLDOWN_KEY, REQUIEM_COOLDOWN, false));
    }
}
