package me.hidden.powers.powers.bowmaster;

import me.hidden.powers.config.PowerConfiguration;

import java.io.File;

public class BowMasterConfiguration extends PowerConfiguration {
    public BowMasterConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @Override
    public String initialData() {
        return "[]";
    }
}
