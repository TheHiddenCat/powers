package me.hidden.powers.powers.powerminer;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;

public final class PowerMinerListener implements Listener {

    private final PowerMiner power;
    private final Collection<Material> oreTypes = List.of(Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE,
            Material.NETHER_QUARTZ_ORE, Material.NETHER_GOLD_ORE, Material.DIAMOND_ORE, Material.LAPIS_ORE,
            Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.COPPER_ORE, Material.DEEPSLATE_COAL_ORE,
            Material.DEEPSLATE_COPPER_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.DEEPSLATE_EMERALD_ORE,
            Material.DEEPSLATE_GOLD_ORE, Material.DEEPSLATE_IRON_ORE, Material.DEEPSLATE_LAPIS_ORE,
            Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE_ORE);

    public PowerMinerListener(PowerMiner power) {
        this.power = power;
    }

    @EventHandler
    public void onBlockMinedEvent(BlockBreakEvent e) {
        var player = e.getPlayer();
        if (!power.playerHasPower(player.getUniqueId())) return;
        if (!isOre(e.getBlock())) return;
        var item = player.getInventory().getItemInMainHand();
        if (item.getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0) return;

        var modifier = 0;
        var oldModifier = 0;
        var existingEffect = player.getPotionEffect(PotionEffectType.FAST_DIGGING);
        if (existingEffect != null) {
            oldModifier = existingEffect.getAmplifier();
            modifier = existingEffect.getAmplifier();
            if (modifier < power.getHasteMaxModifier()) {
                modifier++;
            }
            else {
                modifier = power.getHasteMaxModifier();
            }
        }
        var potionEffect = new PotionEffect(PotionEffectType.FAST_DIGGING, power.getHasteDuration(), modifier, true, true, true);
        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
        player.addPotionEffect(potionEffect);

        if (oldModifier < 2) {
            var options = new Particle.DustOptions(Color.fromRGB(255, 145, 0), 2.5F);
            e.getBlock().getWorld().spawnParticle(Particle.REDSTONE, e.getBlock().getLocation().add(0.5f, 0.5f, 0.5f), 15, 0f,0f,0f, options);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8f, (float) (0.5 * modifier));
        }
    }

    private boolean isOre(Block block) {
        return oreTypes.contains(block.getType());
    }
}
