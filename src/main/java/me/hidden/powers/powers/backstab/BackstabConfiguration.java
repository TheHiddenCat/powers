package me.hidden.powers.powers.backstab;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class BackstabConfiguration extends PowerConfiguration {
    public BackstabConfiguration(File folder, String fileName) { super(folder, fileName); }

    @Override
    public String initialData() {
        var object = new JSONObject();
        object.put("damage_modifier", 1.5d);
        return object.toJSONString();
    }

    public double getDamageModifier() {
        return (double) getData().get("damage_modifier");
    }
}
