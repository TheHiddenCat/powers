package me.hidden.powers.powers.vampirism;

import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public final class VampirismTask extends BukkitRunnable {

    private final Vampirism power;

    public VampirismTask(Vampirism power) {
        this.power = power;
    }

    @Override
    public void run() {
        var uuids = power.getPlayers();
        for (var uuid : uuids) {
            var player = Bukkit.getPlayer(uuid);
            if (player == null) continue;
            var world = player.getWorld();
            var time = world.getTime() % 24000;
            var block = player.getLocation().getBlock();
            var sunlight = block.getLightFromSky();
            var light = block.getLightLevel();

            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            player.removePotionEffect(PotionEffectType.SATURATION);
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.removePotionEffect(PotionEffectType.WEAKNESS);

            if (sunlight == 15 && (time >= 0 && time <= 12000) && !world.hasStorm()) {
                var weakness = new PotionEffect(PotionEffectType.WEAKNESS, 30, power.getWeaknessAmplifier(), true, true, true);
                player.addPotionEffect(weakness);
                player.setSaturation(0);
                player.setFireTicks(20);
            }

            if (light <= power.getLightLevel1()) {
                var strength = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 30, power.getStrengthAmplifier(), true, true, true);
                player.addPotionEffect(strength);
            }

            if (light <= power.getLightLevel2()) {
                var resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 30, power.getResistanceAmplifier(), true, true, true);
                player.addPotionEffect(resistance);
            }

            if (light <= power.getLightLevel3()) {
                var saturation = new PotionEffect(PotionEffectType.SATURATION, 30, power.getSaturationAmplifier(), true, true, true);
                player.addPotionEffect(saturation);
            }

            if (light <= power.getNightVisionLight()) {
                var nightvision = new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0, true, true, true);
                player.addPotionEffect(nightvision);
            }
        }
    }
}
