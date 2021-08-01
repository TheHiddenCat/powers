package me.hidden.powers.managers;

import me.hidden.powers.models.BowMaster;
import me.hidden.powers.models.Power;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class PowerManager {

    private final Plugin plugin;
    private final List<Power> powers;

    public PowerManager(Plugin plugin) {
        this.plugin = plugin;
        this.powers = new ArrayList<>();
        this.powers.add(new BowMaster());
    }

    public Iterable<Power> getPowers() {
        return powers;
    }

    public Power getPower(String name) {
        for (var power : powers) {
            if (power.getName().equalsIgnoreCase(name)) {
                return power;
            }
        }
        return null;
    }

    public <T extends Power> T getPower(Class<T> clazz) {
        for (var power : powers) {
            if (power.getClass() == clazz) {
                return (T) power;
            }
        }
        return null;
    }

    public void registerPlayer(UUID uuid, Power power) {
        if (power == null) return;

        power.addPlayer(uuid);

        if (power.amountPlayers() == 1) {
            try {
                registerPowerEvents(power);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void unregisterPlayer(UUID uuid, Power power) {
        if (power == null) return;

        power.removePlayer(uuid);

        if (power.amountPlayers() == 0) {
            try {
                unregisterPowerEvents(power);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerPowerEvents(Power power) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var listeners = power.getEventListeners();
        for (var listener : listeners) {
            var constructor = listener.getConstructor(power.getClass());
            var instance = constructor.newInstance(power);
            plugin.getServer().getPluginManager().registerEvents(instance, plugin);
        }
    }

    public void unregisterPowerEvents(Power power) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var listeners = power.getEventListeners();
        for (var listener : listeners) {
            var constructor = listener.getConstructor(power.getClass());
            var instance = constructor.newInstance(power);
            HandlerList.unregisterAll(instance);
        }
    }
}
