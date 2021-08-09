package me.hidden.powers.powers.vampirism;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public class VampirismConfiguration extends PowerConfiguration {

    public VampirismConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String initialData() {
        var object = new JSONObject();
        object.put("darkness_3_light_lower", 0);
        object.put("darkness_3_light_upper", 3);

        object.put("darkness_2_light_lower", 4);
        object.put("darkness_2_light_upper", 7);

        object.put("darkness_1_light_lower", 8);
        object.put("darkness_1_light_upper", 10);
        return object.toJSONString();
    }

    public int getDarknessTier3LightLower() {
        return ((Number) getData().get("darkness_3_light_lower")).intValue();
    }

    public int getDarknessTier3LightUpper() {
        return ((Number) getData().get("darkness_3_light_upper")).intValue();
    }

    public int getDarknessTier2LightLower() {
        return ((Number) getData().get("darkness_2_light_lower")).intValue();
    }

    public int getDarknessTier2LightUpper() {
        return ((Number) getData().get("darkness_2_light_upper")).intValue();
    }

    public int getDarknessTier1LightLower() {
        return ((Number) getData().get("darkness_1_light_lower")).intValue();
    }

    public int getDarknessTier1LightUpper() {
        return ((Number) getData().get("darkness_1_light_upper")).intValue();
    }
}
