package me.hidden.powers.powers.thaumaturge;

public enum ThaumaturgeSpellType {
    FIRE,
    BLIZZARD,
    THUNDER;

    public static ThaumaturgeSpellType cycle(ThaumaturgeSpellType current) {
        var options = values();
        var index = current.ordinal() + 1;
        if (index >= options.length) {
            return FIRE;
        }
        else {
            return options[index];
        }
    }
}
