package me.hidden.powers.powers.doublejump;

import me.hidden.powers.powers.Cooldown;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public final class DoubleJumpListener implements Listener {

    private final DoubleJump power;

    private static final String JUMP_COOLDOWN_KEY = "doublejump.cooldown";
    private static final int JUMP_COOLDOWN = 4;

    public DoubleJumpListener(DoubleJump power) {
        this.power = power;
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        if (!power.playerHasPower(player.getUniqueId())) return;
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setDamage(e.getDamage() - 3);
            if (e.getDamage() <= 0) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        if (!power.playerHasPower(e.getPlayer().getUniqueId())) return;
        var player = e.getPlayer();
        var location = player.getLocation().subtract(0, 1, 0);
        var block = location.getBlock();
        if (block.getType().isSolid()) {
            power.playerTouchedGround(player.getUniqueId(), true);
        }
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEvent e) {
        if (!power.playerHasPower(e.getPlayer().getUniqueId())) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getItem() == null) return;
        if (e.getItem().getType() != Material.FEATHER) return;
        if (power.onCooldown(e.getPlayer().getUniqueId(), JUMP_COOLDOWN_KEY)) return;

        var player = e.getPlayer();
        var velocity = player.getVelocity();
        var location = player.getLocation().subtract(0, 1, 0);
        var ground = location.getBlock();

        if (ground.getType().isAir() && power.playerTouchedGround(player.getUniqueId())) {
            velocity.setY(0.55d);
            player.setFallDistance(-10f);
            player.setVelocity(velocity);
            var options = new Particle.DustOptions(Color.fromRGB(222, 233, 250), 1.4F);
            player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation(), 60, 0.6f, 0.3f, 0.6f, options);
            player.getWorld().playSound(location, Sound.BLOCK_SAND_BREAK, 2.0f, 0.8f);
            player.getWorld().playSound(location, Sound.ENTITY_SNOW_GOLEM_HURT, 1.0f, 0.7f);
            power.addCooldown(new Cooldown(player.getUniqueId(), JUMP_COOLDOWN_KEY, JUMP_COOLDOWN));
            power.playerTouchedGround(player.getUniqueId(), false);
        }
    }
}
