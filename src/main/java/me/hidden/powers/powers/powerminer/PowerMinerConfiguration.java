package me.hidden.powers.powers.powerminer;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class PowerMinerConfiguration extends PowerConfiguration {
    public PowerMinerConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String initialData() {
        var object = new JSONObject();
        object.put("haste_duration", 200);
        object.put("haste_max_modifier", 2);
        return object.toJSONString();
    }

    public int getHasteMaxModifier() {
        return ((Number) getData().get("haste_max_modifier")).intValue();
    }

    public int getHasteDuration() {
        return ((Number) getData().get("haste_duration")).intValue();
    }
}
