package me.hidden.powers.powers.sugarrush;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class SugarRushConfiguration extends PowerConfiguration {

    public SugarRushConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    public String initialData() {
        var object = new JSONObject();
        object.put("speed_amplifier", 2);
        object.put("speed_duration", 160);

        return object.toJSONString();
    }

    public int getSpeedAmplifier() {
        return ((Number) getData().get("speed_amplifier")).intValue();
    }
    public int getSpeedDuration() { return ((Number) getData().get("speed_duration")).intValue(); }
}
