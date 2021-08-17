package me.hidden.powers.powers.ichor;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class Ichor extends Power {

    public Ichor () {
        super();
        addConfig("config", IchorConfiguration.class);
        addEvent(IchorListener.class);
    }

    @Override
    public String getName() {
        return "Ichor";
    }

    @Override
    public String getDescription() {
        return "Something";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.UTILITY;
    }

    public double getGlowStoneDustHeal() { return getConfig("config", IchorConfiguration.class).getGlowStoneDustHeal(); }
    public double getGlowStoneHeal() { return getConfig("config", IchorConfiguration.class).getGlowStoneHeal(); }
    public int getConsumeCooldownTicks() { return getConfig("config", IchorConfiguration.class).getConsumeCooldownTicks(); }
    public int getGlowStoneConfusionTicks() { return getConfig("config", IchorConfiguration.class).getGlowStoneConfusionTicks(); }
    public int getGlowStoneDustDropChance() { return getConfig("config", IchorConfiguration.class).getGlowStoneDustDropChance(); }
}
