package me.hidden.powers;

import me.hidden.powers.commands.PowerCommands;
import me.hidden.powers.events.PlayerConnectionListener;
import me.hidden.powers.managers.PowerManager;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private PowerManager powerManager;

    @Override
    public void onEnable() {
        initializePluginDirectory();
        initializeManagers();
        initializeListeners();
        initializeCommands();
    }

    private void initializeCommands() {
        getCommand("powers").setExecutor(new PowerCommands(powerManager));
    }

    private void initializeManagers() {
        powerManager = new PowerManager(this);
    }

    private void initializeListeners() {
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(powerManager), this);
    }

    private void initializePluginDirectory() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
    }
}
