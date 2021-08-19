package me.hidden.powers.powers.veinminer;

import me.hidden.powers.Main;
import me.hidden.powers.util.IntegerReference;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public final class VeinminerListener implements Listener {

    private final Veinminer power;

    private final Map<UUID, Location> markedBlocks;

    public VeinminerListener(Veinminer power) {
        this.power = power;
        this.markedBlocks = new HashMap<>();
    }

    @EventHandler
    public void onOreMarkEvent(PlayerInteractEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();
        if(!power.playerHasPower(uuid)) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        var equipment = player.getEquipment();
        ItemStack tool = null;
        if (equipment != null) {
            tool = equipment.getItemInMainHand();
        }
        if (tool == null) return;
        if (!isPickaxe(tool.getType())) return;
        var block = e.getClickedBlock();
        if (block == null) return;
        if (!isOre(block.getType())) return;
        var location = block.getLocation();
        markedBlocks.put(uuid, location);

        var world = location.getWorld();
        if (world != null) {
            var options = new Particle.DustOptions(Color.fromRGB(255, 145, 0), 1.5F);
            world.spawnParticle(Particle.REDSTONE, location.add(0.5f, 0.5f, 0.5f), 5, 0.3f, 0.3f, 0.3f, 0, options);
            world.playSound(location, Sound.BLOCK_AMETHYST_CLUSTER_PLACE, 0.8f, 1.2f);
        }
    }

    @EventHandler
    public void onBlockMinedEvent(BlockBreakEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();
        if(!power.playerHasPower(uuid)) return;
        var block = e.getBlock();
        var location = block.getLocation();
        var markedLocation = markedBlocks.get(uuid);
        if (markedLocation == null) return;
        if (!isOre(markedLocation.getBlock().getType())) return;
        if (markedLocation.getX() != location.getX() &&
                markedLocation.getY() != location.getY() &&
                markedLocation.getZ() != location.getZ() &&
                markedLocation.getWorld() != location.getWorld()) return;

        var equipment = player.getEquipment();
        ItemStack tool = null;
        if (equipment != null) {
            tool = equipment.getItemInMainHand();
        }
        var blocks = new LinkedList<Block>();
        scanBlocks(block, block.getType(), new IntegerReference(0), blocks, 64);
        new VeinMinerTask(blocks, tool).runTaskTimer(Main.getPlugin(Main.class), 0, 1);
    }

    private void scanBlocks(final Block block, final Material material, final IntegerReference counter, final LinkedList<Block> blocks, final int limit) {

        if (!blocks.contains(block) && block.getType() == material && counter.value < limit) {
            blocks.add(block);
            counter.value++;
        }
        else {
            return;
        }

        var relatives = new Block[] {
            block.getRelative(1, 0, 0),
            block.getRelative(0, 1, 0),
            block.getRelative(0, 0, 1),
            block.getRelative(1, 1, 0),
            block.getRelative(0, 1, 1),
            block.getRelative(1, 1, 1),
            block.getRelative(-1, 0, 0),
            block.getRelative(0, -1, 0),
            block.getRelative(0, 0, -1),
            block.getRelative(-1, -1, 0),
            block.getRelative(0, -1, -1),
            block.getRelative(-1, -1, -1),
        };

        for (var relative : relatives) {
            scanBlocks(relative, material, counter, blocks, limit);
        }
    }

    private boolean isPickaxe(Material material) {
        return material == Material.WOODEN_PICKAXE ||
                material == Material.STONE_PICKAXE ||
                material == Material.GOLDEN_PICKAXE ||
                material == Material.IRON_PICKAXE ||
                material == Material.DIAMOND_PICKAXE ||
                material == Material.NETHERITE_PICKAXE;
    }

    private boolean isOre(Material material) {
        return material == Material.COAL_ORE ||
                material == Material.COPPER_ORE ||
                material == Material.IRON_ORE ||
                material == Material.REDSTONE_ORE ||
                material == Material.GOLD_ORE ||
                material == Material.LAPIS_ORE ||
                material == Material.DIAMOND_ORE ||
                material == Material.EMERALD_ORE ||

                material == Material.DEEPSLATE_COAL_ORE ||
                material == Material.DEEPSLATE_COPPER_ORE ||
                material == Material.DEEPSLATE_IRON_ORE ||
                material == Material.DEEPSLATE_REDSTONE_ORE ||
                material == Material.DEEPSLATE_GOLD_ORE ||
                material == Material.DEEPSLATE_LAPIS_ORE ||
                material == Material.DEEPSLATE_DIAMOND_ORE ||
                material == Material.DEEPSLATE_EMERALD_ORE ||

                material == Material.NETHER_GOLD_ORE ||
                material == Material.NETHER_QUARTZ_ORE;
    }
}
