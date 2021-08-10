package me.hidden.powers.powers.vampirism;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class VampirismConfiguration extends PowerConfiguration {

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
        object.put("darkness_3_strength", 1);
        object.put("darkness_3_saturation", 1);
        object.put("darkness_2_strength", 0);
        object.put("darkness_2_saturation", 0);
        object.put("darkness_1_saturation", 0);
        object.put("nightvision_light", 10);
        object.put("sunlight_hunger", 2);
        object.put("sunlight_weakness", 0);

        return object.toJSONString();
    }

    public int getNightVisionLight() {
        return ((Number) getData().get("nightvision_light")).intValue();
    }

    public int getSunlightHunger() {
        return ((Number) getData().get("sunlight_hunger")).intValue();
    }

    public int getSunlightWeakness() {
        return ((Number) getData().get("sunlight_weakness")).intValue();
    }

    public int getDarknessTier3Strength() {
        return ((Number) getData().get("darkness_3_strength")).intValue();
    }

    public int getDarknessTier2Strength() {
        return ((Number) getData().get("darkness_2_strength")).intValue();
    }

    public int getDarknessTier3Saturation() {
        return ((Number) getData().get("darkness_3_saturation")).intValue();
    }

    public int getDarknessTier2Saturation() {
        return ((Number) getData().get("darkness_2_saturation")).intValue();
    }

    public int getDarknessTier1Saturation() {
        return ((Number) getData().get("darkness_1_saturation")).intValue();
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
