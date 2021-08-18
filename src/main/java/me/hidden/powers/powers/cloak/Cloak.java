package me.hidden.powers.powers.cloak;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public final class Cloak extends Power {
    public Cloak() {
        super();
        addEvent(CloakListener.class);
    }

    @Override
    public String getName() {
        return "Cloak";
    }

    @Override
    public String getDescription() {
        return "Become" + ChatColor.GOLD + " Invisible" + ChatColor.RESET + " while sneaking. When turning invisible resets enemy aggression if not wearing armor.";
    }

    @Override
    public void onRegister(UUID uuid) {
        var player = Bukkit.getPlayer(uuid);
        if (player != null) {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }

    @Override
    public void onUnregister(UUID uuid) {
        var player = Bukkit.getPlayer(uuid);
        if (player != null) {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.DEFENSIVE;
    }
}
