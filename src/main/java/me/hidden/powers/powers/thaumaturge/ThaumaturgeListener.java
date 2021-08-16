package me.hidden.powers.powers.thaumaturge;

import me.hidden.powers.powers.thaumaturge.spells.Blizzard;
import me.hidden.powers.powers.thaumaturge.spells.Fire;
import me.hidden.powers.powers.thaumaturge.spells.Thunder;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;

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
            power.setSelectedSpell(uuid, ThaumaturgeSpellType.cycle(spellType));
        }
        else if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            var actualSpell =  spells.get(spellType);
            if (actualSpell == null) return;
            actualSpell.execute(power, e);
        }
    }
}
