package me.hidden.powers;

import me.hidden.powers.commands.PowerCommands;
import me.hidden.powers.commands.autocomplete.PowerCommandsCompletion;
import me.hidden.powers.config.PlayerConfiguration;
import me.hidden.powers.events.PlayerConnectionListener;
import me.hidden.powers.managers.PowerManager;

import me.hidden.powers.tasks.PowerCooldownsTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;

public final class Main extends JavaPlugin {

    private PowerManager powerManager;

    private PlayerConfiguration playerConfiguration;

    @Override
    public void onEnable() {
        initializePluginDirectory();
        initializeConfigurations();
        initializeManagers();
        initializeListeners();
        initializeCommands();
        initializeTasks();

        displayNumberOfPowers();
        handleReload();
    }

    private void initializeTasks() {
        var cooldownsTask = new PowerCooldownsTask(powerManager);
        cooldownsTask.runTaskTimer(this, 0, cooldownsTask.getTickInterval());
    }

    private void initializeConfigurations() {
        playerConfiguration = new PlayerConfiguration(getDataFolder(), "players.json");
        playerConfiguration.create("{\"data\":[]}");
        playerConfiguration.read();
    }

    private void initializeCommands() {
        getCommand("powers").setExecutor(new PowerCommands(powerManager, playerConfiguration));
        getCommand("powers").setTabCompleter(new PowerCommandsCompletion(powerManager));
    }

    private void initializeManagers() {
        powerManager = new PowerManager(this);
    }

    private void displayNumberOfPowers() {
        Bukkit.getLogger().info("Loaded " + powerManager.getAmountOfPowersLoaded() + " powers");
    }

    private void handleReload() {
        var players = getServer().getOnlinePlayers();
        for (var player : players) {
            var uuid = player.getUniqueId();
            var data = playerConfiguration.getPlayer(uuid);
            if (data != null) {
                var powers = (JSONArray) data.get("powers");
                for (var entry : powers) {
                    var powerName = (String) entry;
                    var power = powerManager.getPower(powerName);
                    if (power != null) {
                        powerManager.registerPlayer(uuid, power);
                    }
                }
            }
        }
    }

    private void initializeListeners() {
        getServer().getPluginManager().registerEvents(new PlayerConnectionListener(powerManager, playerConfiguration), this);
    }

    private void initializePluginDirectory() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
    }
}
