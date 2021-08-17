package me.hidden.powers.powers.ichor;

import me.hidden.powers.config.PowerConfiguration;
import org.json.simple.JSONObject;

import java.io.File;

public class IchorConfiguration extends PowerConfiguration {

    public IchorConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String initialData() {
        var object = new JSONObject();
        object.put("glowstone_dust_heal", 4d);
        object.put("glowstone_heal", 20d);
        object.put("glowstone_confusion_ticks", 160);
        object.put("glowstone_dust_drop_chance", 3);
        object.put("consume_cooldown_ticks", 20);
        return object.toJSONString();
    }

    public double getGlowStoneDustHeal() {
        return (double) getData().get("glowstone_dust_heal");
    }

    public double getGlowStoneHeal() {
        return (double) getData().get("glowstone_heal");
    }

    public int getConsumeCooldownTicks() {
        return ((Number) getData().get("consume_cooldown_ticks")).intValue();
    }

    public int getGlowStoneConfusionTicks() {
        return ((Number) getData().get("glowstone_confusion_ticks")).intValue();
    }

    public int getGlowStoneDustDropChance() {
        return ((Number) getData().get("glowstone_dust_drop_chance")).intValue();
    }
}
