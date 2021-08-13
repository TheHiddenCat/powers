package me.hidden.powers.powers.transfusion;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class TransfusionConfiguration extends PowerConfiguration {

    public TransfusionConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    public String initialData() {
        var object = new JSONObject();
        object.put("health", 0.5d);
        object.put("damage", 3.0d);
        object.put("max_enemies_hit", 12);
        object.put("distance", 10.0d);
        return object.toJSONString();
    }

    public double getHealth() { return (double) getData().get("health"); }
    public double getDamage() { return (double) getData().get("damage"); }
    public double getDistance() { return (double) getData().get("distance"); }
    public int getMaxEnemiesHit() {
        return ((Number) getData().get("max_enemies_hit")).intValue();
    }
}
