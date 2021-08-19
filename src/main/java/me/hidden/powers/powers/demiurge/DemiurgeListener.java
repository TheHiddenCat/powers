package me.hidden.powers.powers.demiurge;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public final class DemiurgeListener implements Listener {
    private final Demiurge power;

    public DemiurgeListener(Demiurge power) {
        this.power = power;
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        var player = e.getPlayer();
        var uuid = player.getUniqueId();
        if (!power.playerHasPower(uuid)) return;
        var to = e.getTo();
        if (to == null) return;
        var from = e.getFrom();
        if (from.getX() == to.getX() && from.getZ() == to.getZ() && from.getY() == to.getY()) return;

        power.addMoving(uuid);
    }
}
