package me.hidden.powers.powers.vampirism;

import me.hidden.powers.Main;
import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;
import org.bukkit.ChatColor;

public final class Vampirism extends Power {

    private VampirismTask task;

    public Vampirism() {
        super();
        addConfig("config", VampirismConfiguration.class);
    }

    @Override
    public String getName() {
        return "Vampirism";
    }

    @Override
    public String getDescription() {
        return "Being a vampire makes you " + ChatColor.RED + "vulnerable to the sun " + ChatColor.RESET +
                ", but " + ChatColor.GREEN + "strong while surrounded by darkness " + ChatColor.RESET + ". Grants  " +
                ChatColor.GOLD + "Strength, Resistance, Saturation and Night Vision " + ChatColor.RESET +
                "based on light level. When in contact with sunlight become afflicted with burn damage and " +
                ChatColor.GOLD + "Weakness." + ChatColor.RESET;
    }

    @Override
    public void onEnable() {
        task = new VampirismTask(this);
        task.runTaskTimer(Main.getPlugin(Main.class), 0, 20);
    }

    @Override
    public void onDisable() {
        task.cancel();
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.SPECIAL;
    }

    public int getNightVisionLight() { return getConfig("config", VampirismConfiguration.class).getNightVisionLight(); }
    public int getWeaknessAmplifier() { return getConfig("config", VampirismConfiguration.class).getWeaknessAmplifier(); }
    public int getLightLevel1() { return getConfig("config", VampirismConfiguration.class).getLightLevel1(); }
    public int getLightLevel2() {
        return getConfig("config", VampirismConfiguration.class).getLightLevel2();
    }
    public int getLightLevel3() { return getConfig("config", VampirismConfiguration.class).getLightLevel3(); }
    public int getStrengthAmplifier() { return getConfig("config", VampirismConfiguration.class).getStrengthAmplifier(); }
    public int getResistanceAmplifier() { return getConfig("config", VampirismConfiguration.class).getResistanceAmplifier(); }
    public int getSaturationAmplifier() { return getConfig("config", VampirismConfiguration.class).getSaturationAmplifier(); }

}
