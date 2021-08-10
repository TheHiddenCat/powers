package me.hidden.powers.powers.vampirism;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class VampirismListener implements Listener {

    private final Vampirism power;

    public VampirismListener(Vampirism power) {
        this.power = power;
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        if (!power.playerHasPower(e.getPlayer().getUniqueId())) return;

        var player = e.getPlayer();
        var world = player.getWorld();
        var time = world.getTime() % 24000;
        var block = player.getLocation().getBlock();
        var sunlight = block.getLightFromSky();
        var light = block.getLightLevel();

        player.removePotionEffect(PotionEffectType.HUNGER);
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        player.removePotionEffect(PotionEffectType.SATURATION);
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        player.removePotionEffect(PotionEffectType.WEAKNESS);

        if (sunlight == 15 && (time >= 0 && time <= 12000)) {
            var hunger = new PotionEffect(PotionEffectType.HUNGER, Integer.MAX_VALUE, power.getSunlightHunger(), true, true, true);
            var weakness = new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, power.getSunlightWeakness(), true, true, true);
            player.addPotionEffect(hunger);
            player.addPotionEffect(weakness);
            player.setSaturation(0);
            player.setExhaustion(3);
        }

        if (light >= power.getDarknessTier1LightLower() && light <= power.getDarknessTier1LightUpper()) {
            var saturation = new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, power.getDarknessTier1Saturation(), true, true, true);
            player.addPotionEffect(saturation);
        }

        if (light >= power.getDarknessTier2LightLower() && light <= power.getDarknessTier2LightUpper()) {
            var saturation = new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, power.getDarknessTier2Saturation(), true, true, true);
            var strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, power.getDarknessTier2Strength(), true, true, true);
            player.addPotionEffect(saturation);
            player.addPotionEffect(strength);
        }

        if (light >= power.getDarknessTier3LightLower() && light <= power.getDarknessTier3LightUpper()) {
            var saturation = new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, power.getDarknessTier3Saturation(), true, true, true);
            var strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, power.getDarknessTier3Strength(), true, true, true);
            player.addPotionEffect(saturation);
            player.addPotionEffect(strength);
        }

        if (light <= power.getNightVisionLight()) {
            var nightvision = new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true, true, true);
            player.addPotionEffect(nightvision);
        }
    }
}
