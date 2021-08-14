package me.hidden.powers.powers.bloodboil;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public class BloodBoilConfiguration extends PowerConfiguration {
    public BloodBoilConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    public String initialData() {
        var object = new JSONObject();
        object.put("damage", 60.0d);
        return object.toJSONString();
    }

    public double getDamage() {
        return (double) getData().get("damage");
    }

}
