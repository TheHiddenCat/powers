package me.hidden.powers.powers.veinminer;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.LinkedList;

public final class VeinMinerTask extends BukkitRunnable {

    private final LinkedList<Block> blocks;
    private final ItemStack tool;
    private float pitch;

    public VeinMinerTask(LinkedList<Block> blocks, ItemStack tool) {
        this.blocks = blocks;
        this.tool = tool;
        this.pitch = 0.3f;
    }

    @Override
    public void run() {
        if (!blocks.isEmpty()) {
            var block = blocks.getFirst();
            var material = block.getType();
            var location = block.getLocation();
            var world = block.getWorld();
            for (var item : block.getDrops(tool)) {
                world.dropItemNaturally(location, item);
            }

            world.spawnParticle(Particle.BLOCK_CRACK, location.add(0.5f, 0.5f, 0.5f), 15, 0.3f, 0.3f, 0.3f, 0, material.createBlockData());
            world.playSound(location, Sound.BLOCK_AMETHYST_CLUSTER_PLACE, 0.8f, pitch);
            pitch += 0.02f;
            block.setType(Material.AIR);
            blocks.pop();
        }
        else {
            cancel();
        }
    }
}
