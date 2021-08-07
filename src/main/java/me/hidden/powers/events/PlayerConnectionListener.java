package me.hidden.powers.events;

import me.hidden.powers.config.PlayerConfiguration;
import me.hidden.powers.managers.PowerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.event.player.PlayerQuitEvent;
import org.json.simple.JSONArray;

public final class PlayerConnectionListener implements Listener {

    private final PowerManager powerManager;
    private final PlayerConfiguration playerConfiguration;

    public PlayerConnectionListener(PowerManager powerManager, PlayerConfiguration playerConfiguration) {
        this.powerManager = powerManager;
        this.playerConfiguration = playerConfiguration;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        var player = e.getPlayer();
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
        else {
            playerConfiguration.addPlayer(uuid);
            playerConfiguration.save();
        }
    }

    @EventHandler
    public void onPlayerExitEvent(PlayerQuitEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();
        var powers = powerManager.getPowers(uuid);
        for (var power : powers) {
            powerManager.unregisterPlayer(uuid, power);
        }
    }
}
