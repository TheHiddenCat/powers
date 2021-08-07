package me.hidden.powers.config;

import java.io.File;

public abstract class PowerConfiguration extends JsonConfiguration{
    public PowerConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    public abstract String initialData();
}
