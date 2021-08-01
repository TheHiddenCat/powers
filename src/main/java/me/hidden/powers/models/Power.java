package me.hidden.powers.models;

import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class Power {
    protected final Set<Class<? extends Listener>> eventListeners;
    protected final Set<UUID> players;

    public Power() {
        this.eventListeners = new HashSet<>();
        this.players = new HashSet<>();
    }

    public abstract long getId();
    public abstract String getName();
    public abstract String getFancyName();
    public abstract String getDescription();

    public Iterable<Class<? extends Listener>> getEventListeners() {
        return eventListeners;
    }
    public Iterable<UUID> getPlayers() {
        return players;
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

    public boolean playerHasPower(UUID playerUUID) {
        for (var uuid : players) {
            if (uuid == playerUUID) {
                return true;
            }
        }
        return false;
    }
}
