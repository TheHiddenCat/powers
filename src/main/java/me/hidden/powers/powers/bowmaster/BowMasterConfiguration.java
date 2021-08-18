package me.hidden.powers.powers.bowmaster;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public final class BowMasterConfiguration extends PowerConfiguration {
    public BowMasterConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String initialData() {
        var object = new JSONObject();
        object.put("arrow_velocity", 1.25d);
        object.put("arrow_damage", 1.1d);
        object.put("arrow_no_consume", 0.25d);
        object.put("arrows_body_amount", 3);
        object.put("arrows_body_explode_damage", 6.0d);
        object.put("arrow_explode_weakness_amplifier", 0);
        object.put("arrow_explode_weakness_duration", 200);
        return object.toJSONString();
    }

    public double getArrowVelocity() {
        return (double) getData().get("arrow_velocity");
    }

    public double getArrowDamage() {
        return (double) getData().get("arrow_damage");
    }

    public double getArrowNoConsume() {
        return (double) getData().get("arrow_no_consume");
    }

    public double getArrowsBodyExplodeDamage() {
        return (double) getData().get("arrows_body_explode_damage");
    }

    public int getArrowsBodyAmount() {
        return ((Number) getData().get("arrows_body_amount")).intValue();
    }

    public int getArrowExplodeWeaknessAmplifier() {
        return ((Number) getData().get("arrow_explode_weakness_amplifier")).intValue();
    }

    public int getArrowExplodeWeaknessDuration() {
        return ((Number) getData().get("arrow_explode_weakness_duration")).intValue();
    }
}
