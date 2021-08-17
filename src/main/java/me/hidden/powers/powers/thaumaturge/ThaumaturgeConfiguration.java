package me.hidden.powers.powers.thaumaturge;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class ThaumaturgeConfiguration extends PowerConfiguration {

    public ThaumaturgeConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String initialData() {
        var object = new JSONObject();
        object.put("fire_flux_cost", 1.5d);

        object.put("blizzard_flux_cost", 2d);
        object.put("blizzard_damage", 2d);
        object.put("blizzard_aoe", 4d);
        object.put("blizzard_slow_duration", 120);
        object.put("blizzard_slow_amplifier", 1);

        object.put("thunder_flux_cost", 3.0d);
        object.put("max_flux", 10d);
        return object.toJSONString();
    }

    public double getFireFluxCost() {
        return (double) getData().get("fire_flux_cost");
    }

    public double getBlizzardFluxCost() {
        return (double) getData().get("blizzard_flux_cost");
    }
    public double getBlizzardAoe() {
        return (double) getData().get("blizzard_aoe");
    }
    public double getBlizzardDamage() {
        return (double) getData().get("blizzard_damage");
    }
    public int getBlizzardSlowDuration() {
        return ((Number) getData().get("blizzard_slow_duration")).intValue();
    }
    public int getBlizzardSlowAmplifier() {
        return ((Number) getData().get("blizzard_slow_amplifier")).intValue();
    }


    public double getThunderFluxCost() {
        return (double) getData().get("thunder_flux_cost");
    }

    public double getMaxFlux() {
        return (double) getData().get("max_flux");
    }
}
