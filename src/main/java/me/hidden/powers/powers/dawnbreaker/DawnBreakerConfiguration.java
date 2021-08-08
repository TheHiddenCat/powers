package me.hidden.powers.powers.dawnbreaker;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public class DawnBreakerConfiguration extends PowerConfiguration {
    public DawnBreakerConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String initialData() {
        var object = new JSONObject();
        object.put("ray_length", 6d);
        object.put("ray_damage", 6d);
        return object.toJSONString();
    }


    public double getRayLength() {
        return (double) getData().get("ray_length");
    }

    public double getRayDamage() {
        return (double) getData().get("ray_damage");
    }
}
