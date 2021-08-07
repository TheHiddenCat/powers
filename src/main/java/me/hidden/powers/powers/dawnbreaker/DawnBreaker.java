package me.hidden.powers.powers.dawnbreaker;

import me.hidden.powers.powers.Power;
import org.bukkit.ChatColor;

public class DawnBreaker extends Power {

    public DawnBreaker() {
        super();
        addEvent(DawnBreakerListener.class);
    }

    @Override
    public String getName() {
        return "Dawnbreaker";
    }

    @Override
    public String getFancyName() {
        return ChatColor.RED + "Dawn Breaker";
    }

    @Override
    public String getDescription() {
        return "Some description";
    }
}
