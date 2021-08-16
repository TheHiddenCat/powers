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
        object.put("fire_flux_cost", 2d);
        object.put("blizzard_flux_cost", 2d);
        object.put("thunder_flux_cost", 2d);
        object.put("max_flux", 10d);
        return object.toJSONString();
    }

    public double getFireFluxCost() {
        return (double) getData().get("fire_flux_cost");
    }

    public double getBlizzardFluxCost() {
        return (double) getData().get("blizzard_flux_cost");
    }

    public double getThunderFluxCost() {
        return (double) getData().get("thunder_flux_cost");
    }
}
