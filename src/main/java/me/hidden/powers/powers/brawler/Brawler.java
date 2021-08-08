package me.hidden.powers.powers.brawler;

import me.hidden.powers.powers.Power;

import me.hidden.powers.powers.PowerType;

public final class Brawler extends Power {

    public Brawler() {
        super();
        addConfig("config", BrawlerConfiguration.class);
        addEvent(BrawlerListener.class);
    }

    @Override
    public String getName() {
        return "Brawler";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.OFFENSIVE;
    }

    @Override
    public String getDescription() {
        return "Some description";
    }

    public double getDamageModifier() { return getConfig("config", BrawlerConfiguration.class).getDamageModifier(); }
}
