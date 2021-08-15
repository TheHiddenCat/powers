package me.hidden.powers.powers.sugarrush;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class SugarRushListener implements Listener {

    private final SugarRush power;

    public SugarRushListener(SugarRush power) {
        this.power = power;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();
        var item = e.getItem();
        if (!power.playerHasPower(uuid)) return;
        if (item == null) return;
        if (item.getType() != Material.SUGAR) return;

        if (player.getGameMode() != GameMode.CREATIVE) {
            if (item.getAmount() > 1) {
                item.setAmount(item.getAmount() - 1);
            } else {
                player.getInventory().removeItem(item);
            }
        }

        var location = player.getEyeLocation();
        player.spawnParticle(Particle.BLOCK_CRACK, location, 3, 0.2f,0.2f,0.2f, Material.SNOW_BLOCK.createBlockData());
        player.playSound(location, Sound.ENTITY_PLAYER_BURP, 1.0f, 1.2f);
        player.removePotionEffect(PotionEffectType.SPEED);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, power.getSpeedDuration(), power.getSpeedAmplifier(), false, false, true));
    }
}
