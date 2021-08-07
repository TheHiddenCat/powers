package me.hidden.powers.powers.bowmaster;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class BowMasterConfiguration extends PowerConfiguration {
    public BowMasterConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String initialData() {
        var object = new JSONObject();
        object.put("arrow_velocity", 1.2d);
        object.put("arrow_damage", 1.1d);
        object.put("arrow_no_consume", 0.25d);
        return object.toJSONString();
    }

    public double getArrowVelocity() {
        return (double) getData().get("arrow_velocity");
    }

    public double getArrowDamage() {
        return (double) getData().get("arrow_damage");
    }

    public double getArrowNoConsume() {
        return (double) getData().get("arrow_no_consume");
    }
}
