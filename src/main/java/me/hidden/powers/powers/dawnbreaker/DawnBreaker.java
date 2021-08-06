package me.hidden.powers.powers.dawnbreaker;

import me.hidden.powers.powers.Power;

public class DawnBreaker extends Power {

    public DawnBreaker() {
        super();
        this.eventListeners.add(DawnBreakerListener.class);
    }

    @Override
    public long getId() {
        return 2;
    }

    @Override
    public String getName() {
        return "Dawnbreaker";
    }

    @Override
    public String getFancyName() {
        return "Dawn Breaker";
    }

    @Override
    public String getDescription() {
        return "Some description";
    }
}
