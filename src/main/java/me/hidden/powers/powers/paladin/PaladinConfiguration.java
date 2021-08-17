package me.hidden.powers.powers.paladin;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class PaladinConfiguration extends PowerConfiguration {

    public PaladinConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    public String initialData() {
        var object = new JSONObject();
        object.put("health_boost_amplifier", 2);
        return object.toJSONString();
    }
}
