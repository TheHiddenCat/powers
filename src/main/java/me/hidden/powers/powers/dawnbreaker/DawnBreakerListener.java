package me.hidden.powers.powers.dawnbreaker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class DawnBreakerListener implements Listener {

    private final DawnBreaker dawnBreaker;

    public DawnBreakerListener(DawnBreaker dawnBreaker) {
        this.dawnBreaker = dawnBreaker;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {

    }
}
