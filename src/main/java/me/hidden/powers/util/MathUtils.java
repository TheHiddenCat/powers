package me.hidden.powers.util;

public final class MathUtils {
    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    public static double clampLower(double val, double min) {
        return Math.max(min, Math.min(Double.MAX_VALUE, val));
    }
}
