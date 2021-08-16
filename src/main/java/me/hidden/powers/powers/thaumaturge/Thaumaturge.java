package me.hidden.powers.powers.thaumaturge;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;
import me.hidden.powers.powers.thaumaturge.tasks.FluxTask;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import java.util.*;

public final class Thaumaturge extends Power {

    private final Map<UUID, BossBar> fluxBars;
    private final Map<UUID, ThaumaturgeSpellType> selectedSpell;
    private final Set<UUID> overloaded;

    private final String spellKey = "power.thaumaturge.spell";

    private FluxTask task;

    public Thaumaturge() {
        super();
        fluxBars = new HashMap<>();
        selectedSpell = new HashMap<>();
        overloaded = new HashSet<>();
        addConfig("config", ThaumaturgeConfiguration.class);
        addEvent(ThaumaturgeListener.class);
    }

    public void addOverloaded(UUID player) {
        overloaded.add(player);
    }

    public void removeOverloaded(UUID player) {
        overloaded.remove(player);
    }

    public boolean isOverloaded(UUID player) {
        return overloaded.contains(player);
    }

    public double getFluxProgress(UUID player) {
        var bar = getFluxBar(player);
        if (bar == null) return 0;
        return bar.getProgress() * getMaxFlux();
    }

    public boolean incrementFlux(UUID player, double amount) {
        var bar = getFluxBar(player);
        if (bar == null) return false;
        var flux = bar.getProgress() * getMaxFlux();
        flux += amount;
        if (flux >= getMaxFlux()) {
            bar.setProgress(1);
            return true;
        }
        if (flux <= 0) {
            bar.setProgress(0);
        }
        else {
            bar.setProgress(flux / getMaxFlux());
        }
        return false;
    }


    public ThaumaturgeSpellType getSelectedSpell(UUID player) {
        return selectedSpell.get(player);
    }

    public void setSelectedSpell(UUID player, ThaumaturgeSpellType spell) {
        selectedSpell.put(player, spell);
    }

    public BossBar getFluxBar(UUID player) {
        return fluxBars.get(player);
    }

    public NamespacedKey getBarKey(UUID player) {
        return new NamespacedKey(Main.getPlugin(Main.class), "flux.bar." + player.toString());
    }

    @Override
    public void onEnable() {
        task = new FluxTask(this);
        task.runTaskTimer(Main.getPlugin(Main.class), 0, 20);
    }

    @Override
    public void onDisable() {
        task.cancel();
    }

    @Override
    public void onRegister(UUID playerUUID) {
        var key = getBarKey(playerUUID);
        var player = Bukkit.getPlayer(playerUUID);
        var bar = Bukkit.getBossBar(key);
        if (bar == null) {
            bar = Bukkit.createBossBar(key, "Flux", BarColor.PURPLE, BarStyle.SEGMENTED_10);
            bar.addPlayer(player);
            bar.setProgress(0);
        }
        fluxBars.put(playerUUID, bar);
        selectedSpell.put(playerUUID, ThaumaturgeSpellType.FIRE);
    }

    @Override
    public void onUnregister(UUID playerUUID) {
        var player = Bukkit.getPlayer(playerUUID);
        if (player != null) {
            var bar = getFluxBar(playerUUID);
            bar.removeAll();
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

    public String getSpellKey() {
        return spellKey;
    }

    public double getFireFluxCost() {
        return getConfig("config", ThaumaturgeConfiguration.class).getFireFluxCost();
    }

    public double getBlizzardFluxCost() {
        return getConfig("config", ThaumaturgeConfiguration.class).getBlizzardFluxCost();
    }
    public double getBlizzardDamage() {
        return getConfig("config", ThaumaturgeConfiguration.class).getBlizzardDamage();
    }
    public double getBlizzardAoe() {
        return getConfig("config", ThaumaturgeConfiguration.class).getBlizzardAoe();
    }
    public int getBlizzardSlowDuration() {
        return getConfig("config", ThaumaturgeConfiguration.class).getBlizzardSlowDuration();
    }
    public int getBlizzardSlowAmplifier() {
        return getConfig("config", ThaumaturgeConfiguration.class).getBlizzardSlowAmplifier();
    }

    public double getThunderFluxCost() {
        return getConfig("config", ThaumaturgeConfiguration.class).getThunderFluxCost();
    }

    public double getMaxFlux() {
        return getConfig("config", ThaumaturgeConfiguration.class).getMaxFlux();
    }
}
