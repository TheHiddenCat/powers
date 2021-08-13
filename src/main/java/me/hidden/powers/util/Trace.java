package me.hidden.powers.util;

import org.bukkit.Bukkit;

public final class Trace {
    public static void trace(String message) {
        Bukkit.getLogger().info(message);
    }
}
