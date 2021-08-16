package me.hidden.powers.powers.thaumaturge;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;
import me.hidden.powers.powers.dawnbreaker.DawnBreakerConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Thaumaturge extends Power {

    private final Map<UUID, BossBar> fluxBars;
    private final Map<UUID, ThaumaturgeSpellType> selectedSpell;

    public Thaumaturge() {
        super();
        fluxBars = new HashMap<>();
        selectedSpell = new HashMap<>();
        addConfig("config", ThaumaturgeConfiguration.class);
        addEvent(ThaumaturgeListener.class);
    }

    public ThaumaturgeSpellType getSelectedSpell(UUID player) {
        return selectedSpell.get(player);
    }

    public void setSelectedSpell(UUID player, ThaumaturgeSpellType spell) {
        selectedSpell.put(player, spell);
    }

    public NamespacedKey getBarKey(UUID player) {
        return new NamespacedKey(Main.getPlugin(Main.class), "flux.bar." + player.toString());
    }

    @Override
    public void onRegister(UUID playerUUID) {
        var key = getBarKey(playerUUID);
        var bar = Bukkit.createBossBar(key, "Flux", BarColor.PURPLE, BarStyle.SEGMENTED_10);
        var player = Bukkit.getPlayer(playerUUID);
        if (player != null) {
            bar.addPlayer(player);
            fluxBars.put(playerUUID, bar);
        }
        selectedSpell.put(playerUUID, ThaumaturgeSpellType.FIRE);
    }

    @Override
    public void onUnregister(UUID playerUUID) {
        var player = Bukkit.getPlayer(playerUUID);
        if (player != null) {
            var bar = fluxBars.get(playerUUID);
            bar.removeAll();
            bar.setVisible(false);
            Bukkit.removeBossBar(getBarKey(playerUUID));
            fluxBars.remove(playerUUID);
        }
        selectedSpell.remove(playerUUID);
    }

    @Override
    public String getName() {
        return "Thaumaturge";
    }

    @Override
    public String getDescription() {
        return "Magic go brr";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.OFFENSIVE;
    }

    public double getFireFluxCost() {
        return getConfig("config", ThaumaturgeConfiguration.class).getFireFluxCost();
    }

    public double getBlizzardFluxCost() {
        return getConfig("config", ThaumaturgeConfiguration.class).getBlizzardFluxCost();
    }

    public double getThunderFluxCost() {
        return getConfig("config", ThaumaturgeConfiguration.class).getThunderFluxCost();
    }
}
