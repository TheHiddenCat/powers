package me.hidden.powers.events;

import me.hidden.powers.managers.PowerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class PlayerConnectionListener implements Listener {

    private final PowerManager powerManager;

    public PlayerConnectionListener(PowerManager powerManager) {
        this.powerManager = powerManager;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();

    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();

    }
}
