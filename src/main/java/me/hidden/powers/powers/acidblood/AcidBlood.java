package me.hidden.powers.powers.acidblood;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;
import me.hidden.powers.util.RomanConverter;
import org.bukkit.ChatColor;

public final class AcidBlood extends Power {

    private AcidBloodImmunityTask task;

    public AcidBlood() {
        super();
        addConfig("config", AcidBloodConfiguration.class);
        addEvent(AcidBloodListener.class);
    }
    @Override
    public String getName() {
        return "AcidBlood";
    }

    @Override
    public String getDescription() {
        return "Physical attacks from enemies afflicts " +
                ChatColor.GOLD + "Poison" + ChatColor.RESET + " " +
                ChatColor.BLUE + RomanConverter.toRoman(getPoisonModifier() + 1) + ChatColor.RESET + " for " +
                ChatColor.GREEN + (getPoisonDuration() / 20) + ChatColor.RESET +
                " seconds. Also makes you immune to " + ChatColor.GOLD + "Poison" + ChatColor.RESET + ".";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.DEFENSIVE;
    }

    @Override
    public void onEnable() {
        task = new AcidBloodImmunityTask(this);
        task.runTaskTimer(Main.getPlugin(Main.class), 0, 20);
    }

    @Override
    public void onDisable() {

    }

    public int getPoisonModifier() {
        return getConfig("config", AcidBloodConfiguration.class).getPoisonModifier();
    }

    public int getPoisonDuration() {
        return getConfig("config", AcidBloodConfiguration.class).getPoisonDuration();
    }
}
