package me.hidden.powers.powers.bloodboil;

import me.hidden.powers.Main;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public final class BloodBoilListener implements Listener {

    private static String BLOODBOIL_COOLDOWN_KEY = "bloodboil.cooldown";
    private static int BLOODBOIL_COOLDOWN = 300;

    private final BloodBoil power;

    public BloodBoilListener(BloodBoil power) {
        this.power = power;
    }

    @EventHandler
    public void onPlayerInteractWithEntity(PlayerInteractEntityEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();
        if (!power.playerHasPower(uuid)) return;
        if (power.onCooldown(uuid, BLOODBOIL_COOLDOWN_KEY)) return;
        if (! (e.getRightClicked() instanceof LivingEntity enemy)) return;
        var inventory = player.getInventory();
        var item = inventory.getItemInMainHand();
        if (item.getType() != Material.QUARTZ) return;

        new BloodBoilTask(power, player, enemy).runTaskTimer(Main.getPlugin(Main.class), 0, 5);
    }
}
