package me.hidden.powers.powers.acidblood;

import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public final class AcidBloodImmunityTask extends BukkitRunnable {

    private final AcidBlood power;

    public AcidBloodImmunityTask(AcidBlood power) {
        this.power = power;
    }

    @Override
    public void run() {
        var uuids = power.getPlayers();
        for (var uuid : uuids) {
            var player = Bukkit.getPlayer(uuid);
            if (player != null) {
                if (player.hasPotionEffect(PotionEffectType.POISON)) {
                    player.removePotionEffect(PotionEffectType.POISON);
                }
            }
        }
    }
}
