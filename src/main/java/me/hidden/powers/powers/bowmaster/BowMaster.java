package me.hidden.powers.powers.bowmaster;

import me.hidden.powers.powers.Power;
import org.bukkit.ChatColor;

public class BowMaster extends Power {
    private double damageModifier = 1.2d;
    private double arrowRecoveryModifier = 0.2d;
    private double arrowSpeedModifier = 1.3d;

    public BowMaster() {
        super();
        this.eventListeners.add(BowMasterListener.class);
    }

    @Override
    public long getId() {
        return 1;
    }

    @Override
    public String getName() {
        return "BowMaster";
    }

    @Override
    public String getFancyName() {
        return ChatColor.GREEN + "Bow Master";
    }

    @Override
    public String getDescription() {
        return "Some description...";
    }

    public double getDamageModifier() {
        return damageModifier;
    }

    public double getArrowRecoveryModifier() {
        return arrowRecoveryModifier;
    }

    public double getArrowSpeedModifier() {
        return arrowSpeedModifier;
    }

    public void setDamageModifier(double damageModifier) {
        this.damageModifier = damageModifier;
    }

    public void setArrowRecoveryModifier(double arrowRecoveryModifier) {
        this.arrowRecoveryModifier = arrowRecoveryModifier;
    }

    public void setArrowSpeedModifier(double arrowSpeedModifier) {
        this.arrowSpeedModifier = arrowSpeedModifier;
    }
}
