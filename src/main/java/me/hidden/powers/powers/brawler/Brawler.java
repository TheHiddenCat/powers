package me.hidden.powers.powers.brawler;

import me.hidden.powers.powers.Power;

import org.bukkit.ChatColor;

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
    public String getFancyName() {
        return ChatColor.RED + "Brawler";
    }

    @Override
    public String getDescription() {
        return "Some description";
    }

    public double getDamageModifier() { return getConfig("config", BrawlerConfiguration.class).getDamageModifier(); }
}
