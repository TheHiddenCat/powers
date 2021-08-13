package me.hidden.powers.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public final class MathUtils {
    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    public static double clampLower(double val, double min) {
        return Math.max(min, Math.min(Double.MAX_VALUE, val));
    }

    public static Vector getDirection(Location a, Location b) {
        var dx = a.getX() - b.getX();
        var dy = a.getY() - b.getY();
        var dz = a.getZ() - b.getZ();
        var yaw = Math.atan2(dz, dx);
        var pitch = Math.atan2(Math.sqrt(dz * dz + dx * dx), dy) + Math.PI;
        var x = Math.sin(pitch) * Math.cos(yaw);
        var y = Math.sin(pitch) * Math.sin(yaw);
        var z = Math.cos(pitch);

        return new Vector(x, z, y).normalize();
    }
}
