package me.hidden.powers.powers;

import java.util.UUID;

public class Cooldown {

    private final UUID player;
    private final String key;
    private int ticks;
    private boolean isSilent;
    private String message;

    public Cooldown(UUID player, String key, int ticks) {
        this.player = player;
        this.key = key;
        this.ticks = ticks;
        this.isSilent = true;
    }

    public Cooldown(UUID player, String key, int ticks, boolean isSilent) {
        this.player = player;
        this.key = key;
        this.ticks = ticks;
        this.isSilent = isSilent;
    }

    public boolean silent() {
        return isSilent;
    }

    public void silent(boolean isSilent) {
        this.isSilent = isSilent;
    }

    public UUID getPlayer() {
        return player;
    }

    public void decreaseTime(int ticks) {
        this.ticks -= ticks;
    }

    public boolean isDone() {
        return this.ticks <= 0;
    }

    public int getTicks() {
        return ticks;
    }

    public String getKey() {
        return key;
    }
}
