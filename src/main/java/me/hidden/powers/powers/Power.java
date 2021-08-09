package me.hidden.powers.powers;

import me.hidden.powers.Main;

import me.hidden.powers.config.PowerConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class Power {
    private final Set<Class<? extends Listener>> eventListeners;
    private final Set<UUID> players;
    private final Map<String, PowerConfiguration> configurations;
    private final List<Cooldown> cooldowns;

    public Power() {
        this.eventListeners = new HashSet<>();
        this.players = new HashSet<>();
        this.configurations = new HashMap<>();
        this.cooldowns = new ArrayList<>();
    }

    public abstract String getName();
    public abstract String getDescription();
    public abstract PowerType getPowerType();

    public Iterable<Class<? extends Listener>> getEventListeners() {
        return eventListeners;
    }
    public Iterable<UUID> getPlayers() {
        return players;
    }
    public Collection<Cooldown> getCooldowns() {
        return cooldowns;
    }
    public void addPlayer(UUID uuid) {
        players.add(uuid);
    }
    public void removePlayer(UUID uuid) {
        players.remove(uuid);
    }
    public long amountPlayers() {
        return players.size();
    }
    public void addCooldown(Cooldown cooldown) {
        for (var existingCooldown : cooldowns) {
            if (existingCooldown.getPlayer().equals(cooldown.getPlayer()) && existingCooldown.getKey().equals(cooldown.getKey())) {
                return;
            }
        }
        cooldowns.add(cooldown);
    }
    public void removeCooldown(Cooldown cooldown) {
        cooldowns.remove(cooldown);
    }
    public void removeCooldown(UUID player, String key) {
        cooldowns.removeIf(x ->  x.getPlayer().equals(player) && x.getKey().equals(key));
    }
    public boolean onCooldown(UUID player, String key) {
        return cooldowns.stream().anyMatch(x ->  x.getPlayer().equals(player) && x.getKey().equals(key));
    }

    public boolean playerHasPower(UUID playerUUID) {
        for (var uuid : players) {
            if (uuid.equals(playerUUID)) {
                return true;
            }
        }
        return false;
    }

    public String getFancyName() {
        var name = getName();
        var color = getPowerType().getColor();
        return color + name;
    }

    protected <T extends PowerConfiguration> T getConfig(String fileName, Class<T> clazz) {
        return clazz.cast(configurations.get(fileName));
    }

    protected void addEvent(Class<? extends Listener> eventClass) {
        this.eventListeners.add(eventClass);
    }

    protected void addConfig(String fileName, Class<? extends PowerConfiguration> configClass) {
        var plugin = Main.getPlugin(Main.class);
        var folder = new File(plugin.getDataFolder().getAbsolutePath() + "/" + getName());
        try {
            var constructor = configClass.getConstructor(File.class, String.class);
            var config = constructor.newInstance(folder, fileName + ".json");
            config.create(config.initialData());
            config.read();
            configurations.put(fileName, config);
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            Bukkit.getLogger().info("Failed to initialize power configuration with file name: " + fileName + ".json");
        }
    }
}
