package me.hidden.powers.powers.dawnbreaker;

import me.hidden.powers.powers.Power;

import me.hidden.powers.powers.PowerType;
import org.bukkit.ChatColor;

public final class DawnBreaker extends Power {

    public DawnBreaker() {
        super();
        addEvent(DawnBreakerListener.class);
        addConfig("config", DawnBreakerConfiguration.class);
    }

    @Override
    public String getName() {
        return "Dawnbreaker";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.OFFENSIVE;
    }

    @Override
    public String getDescription() {
        return ChatColor.GREEN + "Left-clicking or swinging " + ChatColor.RESET + "with a " + ChatColor.BLUE + "golden sword" + ChatColor.RESET +
                " projects a " + ChatColor.GOLD + "beam of primordial light" + ChatColor.RESET + " which deals " +
                ChatColor.RED + getRayDamage() + ChatColor.RESET + " damage to anything in its trajectory. " +
                ChatColor.GREEN + "Right-clicking" + ChatColor.RESET + " while holding a " + ChatColor.BLUE + "golden sword" +
                ChatColor.RESET + " makes you do" + " a " + ChatColor.GOLD + "sweep attack" + ChatColor.RESET +
                " which pushes enemies back while also dealing " + ChatColor.RED + getSweepDamage() + ChatColor.RESET + " damage.";
    }

    public double getSweepForce() { return getConfig("config", DawnBreakerConfiguration.class).getSweepForce(); }

    public double getSweepDamage() { return getConfig("config", DawnBreakerConfiguration.class).getSweepDamage(); }

    public double getRayLength() {
        return getConfig("config", DawnBreakerConfiguration.class).getRayLength();
    }

    public double getRayDamage() {
        return getConfig("config", DawnBreakerConfiguration.class).getRayDamage();
    }
}
