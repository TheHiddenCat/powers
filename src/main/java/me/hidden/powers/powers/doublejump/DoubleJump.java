package me.hidden.powers.powers.doublejump;

import me.hidden.powers.powers.Power;
import me.hidden.powers.powers.PowerType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class DoubleJump extends Power {

    private final Map<UUID, Boolean> touchedGround;

    public DoubleJump() {
        super();
        addEvent(DoubleJumpListener.class);
        touchedGround = new HashMap<>();
    }

    @Override
    public String getName() {
        return "DoubleJump";
    }

    @Override
    public String getDescription() {
        return "While holding a feather in either hand allows you to do a extra jump mid air after your first jump.";
    }

    @Override
    public PowerType getPowerType() {
        return PowerType.UTILITY;
    }

    public void playerTouchedGround(UUID player, boolean grounded) {
        touchedGround.put(player, grounded);
    }

    public boolean playerTouchedGround(UUID player) {
        if (touchedGround.containsKey(player)) {
            return touchedGround.get(player);
        }
        return false;
    }
}
