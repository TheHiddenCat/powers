package me.hidden.powers.powers.thaumaturge;

import me.hidden.powers.powers.thaumaturge.spells.Blizzard;
import me.hidden.powers.powers.thaumaturge.spells.Fire;
import me.hidden.powers.powers.thaumaturge.spells.Thunder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;
import java.util.UUID;

public final class ThaumaturgeListener implements Listener {

    private final Thaumaturge power;
    private final Map<ThaumaturgeSpellType, ThaumaturgeSpell> spells;


    public ThaumaturgeListener(Thaumaturge power) {
        this.power = power;
        this.spells = Map.of(
                ThaumaturgeSpellType.FIRE, new Fire(power.getFireFluxCost()),
                ThaumaturgeSpellType.BLIZZARD, new Blizzard(power.getBlizzardFluxCost()),
                ThaumaturgeSpellType.THUNDER, new Thunder(power.getThunderFluxCost())
        );
    }

    /*
    @EventHandler
    public void onStrike(LightningStrikeEvent e) {
        var lightningStrike = e.getLightning();
        var metadata = lightningStrike.getMetadata(power.getSpellKey());
        if (metadata.size() == 0) return;
        var value = (ThaumaturgeSpellType) metadata.get(0).value();
        if (value != ThaumaturgeSpellType.THUNDER) return;
        lightningStrike.setSilent(true);
    }
    */

    @EventHandler
    public void onPlayerDamageEvent(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        if (!power.playerHasPower(player.getUniqueId())) return;
        if (!(e.getDamager() instanceof LightningStrike lightningStrike)) return;

        var metadata = lightningStrike.getMetadata(power.getThunderKey());
        if (metadata.size() == 0) return;

        var value = metadata.get(0).asString();
        var uuid = UUID.fromString(value);

        if (uuid.equals(player.getUniqueId())) {
            e.setDamage(0);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (!power.playerHasPower(e.getPlayer().getUniqueId())) return;
        if (e.getItem() == null) return;
        if (e.getItem().getType() != Material.BOOK) return;

        var action = e.getAction();
        var player = e.getPlayer();
        var uuid = player.getUniqueId();
        var spellType = power.getSelectedSpell(player.getUniqueId());
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            var nextSpell = ThaumaturgeSpellType.cycle(spellType);
            power.setSelectedSpell(uuid, nextSpell);
            player.sendMessage(ChatColor.GREEN + "[Powers] " +  ChatColor.RESET + "Selected spell: " + ChatColor.GOLD + nextSpell.name());
            player.playSound(player.getEyeLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 0.8f, 0.8f);
        }
        else if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            if (power.isOverloaded(uuid)) {
                player.playSound(player.getEyeLocation(), Sound.ENTITY_BEE_HURT, 1.2f, 0.3f);
                return;
            }
            var actualSpell =  spells.get(spellType);
            if (actualSpell == null) return;
            actualSpell.launch(power, e);
            var overload = power.incrementFlux(uuid, actualSpell.getFluxCost());
            if (overload) {
                power.addOverloaded(uuid);
                player.playSound(player.getEyeLocation(), Sound.ENTITY_BEE_HURT, 1.2f, 0.1f);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0, false, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 0, false, true, true));
            }
        }
    }

    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent e) {
        var projectile = e.getEntity();
        if (!projectile.hasMetadata(power.getSpellKey())) return;
        var metadata = projectile.getMetadata(power.getSpellKey());
        if (metadata.size() == 0) return;
        var value = metadata.get(0).asString();
        var spell = spells.get(ThaumaturgeSpellType.valueOf(value));
        if (spell == null) return;
        spell.hit(power, e);
    }
}
