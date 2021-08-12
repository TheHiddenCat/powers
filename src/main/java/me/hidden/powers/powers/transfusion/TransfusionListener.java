package me.hidden.powers.powers.transfusion;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Cooldown;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public final class TransfusionListener implements Listener {

    private final Transfusion power;

    private static final String TRANSFUSION_COOLDOWN_KEY = "transfusion.suck";
    private static final int TRANSFUSION_COOLDOWN = 40;

    public TransfusionListener(Transfusion power) {
        this.power = power;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEntityEvent e) {
        var player = e.getPlayer();
        var item = player.getInventory().getItemInMainHand();

        if ( power.onCooldown(player.getUniqueId(), TRANSFUSION_COOLDOWN_KEY)) return;
        if (! power.playerHasPower(player.getUniqueId())) return;
        if (! (e.getRightClicked() instanceof LivingEntity livingEntity)) return;
        if ( item.getType() != Material.AIR) return;

        new TransfusionTask(power, player, livingEntity).runTaskTimer(Main.getPlugin(Main.class), 0, 1);
        power.addCooldown(new Cooldown(player.getUniqueId(), TRANSFUSION_COOLDOWN_KEY, TRANSFUSION_COOLDOWN));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.5f, 2.0f);
    }
}
