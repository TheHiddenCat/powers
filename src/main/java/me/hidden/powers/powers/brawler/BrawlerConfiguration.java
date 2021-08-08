package me.hidden.powers.powers.brawler;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class BrawlerConfiguration extends PowerConfiguration {

    public BrawlerConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String initialData() {
        var object = new JSONObject();
        object.put("damage_modifier", 3d);
        return object.toJSONString();
    }

    public double getDamageModifier() {
        return (double) getData().get("damage_modifier");
    }
}
