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
        object.put("light_level_1", 10);
        object.put("light_level_2", 7);
        object.put("light_level_3", 3);
        object.put("nightvision_light_level", 8);

        object.put("strength_amplifier", 0);
        object.put("resistance_amplifier", 0);
        object.put("saturation_amplifier", 0);
        object.put("weakness_amplifier", 0);

        return object.toJSONString();
    }

    public int getNightVisionLight() {
        return ((Number) getData().get("nightvision_light_level")).intValue();
    }
    public int getLightLevel1() { return ((Number) getData().get("light_level_1")).intValue(); }
    public int getLightLevel2() {
        return ((Number) getData().get("light_level_2")).intValue();
    }
    public int getLightLevel3() { return ((Number) getData().get("light_level_3")).intValue(); }
    public int getStrengthAmplifier() { return ((Number) getData().get("strength_amplifier")).intValue(); }
    public int getResistanceAmplifier() { return ((Number) getData().get("resistance_amplifier")).intValue(); }
    public int getSaturationAmplifier() { return ((Number) getData().get("saturation_amplifier")).intValue(); }
    public int getWeaknessAmplifier() { return ((Number) getData().get("weakness_amplifier")).intValue(); }
}
