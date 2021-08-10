package me.hidden.powers.powers.vampirism;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public final class Vampirism extends Power {

    public Vampirism() {
        super();
        addConfig("config", VampirismConfiguration.class);
        addEvent(VampirismListener.class);
    }

    @Override
    public String getName() {
        return "Vampirism";
    }

    @Override
    public String getDescription() {
        return "Being a vampire makes you vulnerable to the sun, but strong while surrounded by darkness. Grants Strength, Saturation and Night Vision based on light level. Become afflicted with Hunger and Weakness while in contact with sunlight.";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.SPECIAL;
    }

    public int getNightVisionLight() {
        return getConfig("config", VampirismConfiguration.class).getNightVisionLight();
    }

    public int getSunlightHunger() {
        return getConfig("config", VampirismConfiguration.class).getSunlightHunger();
    }

    public int getSunlightWeakness() {
        return getConfig("config", VampirismConfiguration.class).getSunlightWeakness();
    }

    public int getDarknessTier3Strength() {
        return getConfig("config", VampirismConfiguration.class).getDarknessTier3Strength();
    }

    public int getDarknessTier2Strength() {
        return getConfig("config", VampirismConfiguration.class).getDarknessTier2Strength();
    }

    public int getDarknessTier3Saturation() {
        return getConfig("config", VampirismConfiguration.class).getDarknessTier3Saturation();
    }

    public int getDarknessTier2Saturation() {
        return getConfig("config", VampirismConfiguration.class).getDarknessTier2Saturation();
    }

    public int getDarknessTier1Saturation() {
        return getConfig("config", VampirismConfiguration.class).getDarknessTier1Saturation();
    }

    public int getDarknessTier3LightLower() {
        return getConfig("config", VampirismConfiguration.class).getDarknessTier3LightLower();
    }

    public int getDarknessTier3LightUpper() {
        return getConfig("config", VampirismConfiguration.class).getDarknessTier3LightUpper();
    }

    public int getDarknessTier2LightLower() {
        return getConfig("config", VampirismConfiguration.class).getDarknessTier2LightLower();
    }

    public int getDarknessTier2LightUpper() {
        return getConfig("config", VampirismConfiguration.class).getDarknessTier2LightUpper();
    }

    public int getDarknessTier1LightLower() {
        return getConfig("config", VampirismConfiguration.class).getDarknessTier1LightLower();
    }

    public int getDarknessTier1LightUpper() {
        return getConfig("config", VampirismConfiguration.class).getDarknessTier1LightUpper();
    }
}
