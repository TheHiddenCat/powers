package me.hidden.powers.managers;

import me.hidden.powers.powers.Power;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public final class PowerManager {
    private static final String powersPackagePath = "me.hidden.powers.powers";

    private final Plugin plugin;
    private final List<Power> powers;

    public PowerManager(Plugin plugin) {
        this.plugin = plugin;
        this.powers = new ArrayList<>();
        this.loadPowers();
        this.logAmountOfPowers();
    }

    private void logAmountOfPowers() {
        Bukkit.getLogger().info("Loaded " + powers.size() + " powers");
    }

    private void loadPowers() {
        try {
            var classes = findPowerClasses();
            for (var clazz : classes) {
                var constructor = clazz.getConstructor();
                var instance = constructor.newInstance();
                powers.add(instance);
            }
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            if (e instanceof NoSuchMethodException) {
                Bukkit.getLogger().info("Did you provide a constructor for this power? Otherwise make sure that you call super() in the constructor of your power");
            }
        }
    }

    private Set<Class<? extends Power>> findPowerClasses() {
        var classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        var reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(powersPackagePath))));

        return reflections.getSubTypesOf(Power.class);
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
