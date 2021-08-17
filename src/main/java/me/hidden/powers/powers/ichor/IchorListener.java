package me.hidden.powers.powers.ichor;

import me.hidden.powers.powers.Cooldown;
import me.hidden.powers.util.MathUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Random;

public final class IchorListener implements Listener {

    private final Ichor power;
    private final Random random;
    private final Particle.DustOptions dust;

    private static final String ICHOR_COOLDOWN_KEY = "ichor.consumed";

    public IchorListener(Ichor power) {
        this.power = power;
        this.random = new Random();
        this.dust = new Particle.DustOptions(Color.fromRGB(255, 221, 145), 1.2F);
    }

    @EventHandler
    public void onPlayerDamageEvent(EntityDamageByEntityEvent e) {
        if (! (e.getEntity() instanceof Player player)) return;
        var uuid = player.getUniqueId();
        if (!power.playerHasPower(uuid)) return;
        if (e.isCancelled()) return;
        if (e.getDamage() == 0) return;

        var spray = random.nextInt(power.getGlowStoneDustDropChance()) == 0;
        if (spray) {
            var world = player.getWorld();
            var location = player.getEyeLocation();
            world.spawnParticle(Particle.REDSTONE, location, 5, 0.5f, 0.5f, 0.5f, dust);
            world.playSound(location, Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 0.3f, 0.7f);
            var item = world.dropItemNaturally(location, new ItemStack(Material.GLOWSTONE_DUST));
            item.setPickupDelay(20);
        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();
        var action = e.getAction();
        var item = e.getItem();

        if (!power.playerHasPower(uuid)) return;
        if (power.onCooldown(uuid, ICHOR_COOLDOWN_KEY)) return;
        if (item == null) return;

        var type = item.getType();
        if (type != Material.GLOWSTONE && type != Material.GLOWSTONE_DUST) return;

        var max = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        var location = player.getEyeLocation();

        if (player.getHealth() == max) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Your health is full"));
            return;
        }

        if (type == Material.GLOWSTONE_DUST && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
            var heal = player.getHealth() + power.getGlowStoneDustHeal();
            player.setHealth(MathUtils.clamp(heal, 0, max));
            player.spawnParticle(Particle.BLOCK_CRACK, location, 3, 0.2f,0.2f,0.2f, Material.GLOWSTONE.createBlockData());
            player.spawnParticle(Particle.REDSTONE, location, 10, 0.5f, 0.5f, 0.5f, dust);
            player.playSound(location, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 0.7f, 0.9f);
        }
        else if (type == Material.GLOWSTONE && (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)) {
            var heal = player.getHealth() + power.getGlowStoneHeal();
            player.setHealth(MathUtils.clamp(heal, 0, max));
            player.spawnParticle(Particle.BLOCK_CRACK, location, 3, 0.2f,0.2f,0.2f, Material.GLOWSTONE.createBlockData());
            player.spawnParticle(Particle.REDSTONE, location, 20, 1f, 1f, 1f, dust);
            player.playSound(location, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 0.9f, 1.4f);
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, power.getGlowStoneConfusionTicks(), 0, false, false, true));
        }
        else {
            return;
        }

        if (player.getGameMode() != GameMode.CREATIVE) {
            if (item.getAmount() > 1) {
                item.setAmount(item.getAmount() - 1);
            } else {
                player.getInventory().removeItem(item);
            }
        }

        power.addCooldown(new Cooldown(player.getUniqueId(), ICHOR_COOLDOWN_KEY, power.getConsumeCooldownTicks(), power.getConsumeCooldownTicks() < 60));
    }
}
