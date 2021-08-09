package me.hidden.powers.powers.acidblood;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class AcidBloodConfiguration extends PowerConfiguration {
    public AcidBloodConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String initialData() {
        var object = new JSONObject();
        object.put("poison_modifier", 0);
        object.put("poison_duration", 100);
        return object.toJSONString();
    }

    public int getPoisonModifier() {
        return ((Number) getData().get("poison_modifier")).intValue();
    }

    public int getPoisonDuration() {
        return ((Number) getData().get("poison_duration")).intValue();
    }
}
