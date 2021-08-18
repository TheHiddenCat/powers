package me.hidden.powers.powers.cloak;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class CloakListener implements Listener {

    private final Cloak power;
    private final Particle.DustOptions options;

    public CloakListener(Cloak power) {
        this.power = power;
        this.options = new Particle.DustOptions(Color.fromRGB(163, 178, 201), 1.5F);
    }

    @EventHandler
    public void onPlayerSneakEvent(PlayerToggleSneakEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();
        if (!power.playerHasPower(uuid)) return;

        var location = player.getEyeLocation();
        var world = player.getWorld();
        if (e.isSneaking()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false, true));
            player.setArrowsInBody(0);

            var hasArmor = false;
            var equipment = player.getEquipment();
            if (equipment != null) {
                for (var armor : equipment.getArmorContents()) {
                    if (armor == null) continue;
                    if (armor.getType() != Material.AIR) {
                        hasArmor = true;
                        break;
                    }
                }
            }

            if (!hasArmor) {
                for (var entity : player.getNearbyEntities(32, 8, 32)) {
                    if (!(entity instanceof Creature creature)) continue;
                    if (!(creature.getTarget() instanceof Player target)) continue;
                    if (!target.getUniqueId().equals(uuid)) continue;
                    creature.setTarget(null);
                }
            }
        }
        else {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        }

        world.spawnParticle(Particle.REDSTONE, location, 25, 0.5f, 0.5f, 0.5f, 0, options);
        world.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 0.8f, 0.7f);
    }
}
