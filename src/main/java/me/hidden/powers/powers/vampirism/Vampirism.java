package me.hidden.powers.powers.vampirism;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

public class Vampirism extends Power {

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
        return "Being a vampire makes you vulnerable to the sun, but strong while surrounded by darkness. Grants Strength, Saturation and Night Vision based on light level. Become afflicted with Hunger and Weakness while in contact with sunlight.";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.SPECIAL;
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
