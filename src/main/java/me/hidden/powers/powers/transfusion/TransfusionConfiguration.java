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
        object.put("saturation", 0.2f);
        object.put("damage", 1.0d);
        return object.toJSONString();
    }

    public float getSaturation() {
        return ((Double) getData().get("saturation")).floatValue();
    }
    public double getDamage() { return (double) getData().get("damage"); }
}
