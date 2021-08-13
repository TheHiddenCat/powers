package me.hidden.powers.powers.transfusion;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class TransfusionConfiguration extends PowerConfiguration {

    public TransfusionConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    public String initialData() {
        var object = new JSONObject();
        object.put("health_per_tick", 0.1d);
        object.put("damage_per_tick", 1.0d);
        return object.toJSONString();
    }

    public double getHealthPerTick() { return (double) getData().get("health_per_tick"); }
    public double getDamagePerTick() { return (double) getData().get("damage_per_tick"); }
}
