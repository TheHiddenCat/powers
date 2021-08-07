package me.hidden.powers.config;

import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.UUID;

public class PlayerConfiguration extends JsonConfiguration {
    public PlayerConfiguration(File folder, String fileName) {
        super(folder, fileName);
    }

    @SuppressWarnings("unchecked")
    public void addPlayer(UUID uuid) {
        var object = new JSONObject();
        var array = new JSONArray();

        object.put("powers", array);
        object.put("uuid", uuid.toString());

        var players = getPlayers();
        players.add(object);
    }

    @SuppressWarnings("unchecked")
    public void addPower(UUID playerUuid, String power) {
        var player = getPlayer(playerUuid);
        var powers = (JSONArray) player.get("powers");
        powers.add(power);
    }

    public void removePower(UUID playerUuid, String power) {
        var player = getPlayer(playerUuid);
        var powers = (JSONArray) player.get("powers");
        powers.remove(power);
    }

    public JSONArray getPlayers() {
        return (JSONArray) getData().get("data");
    }

    public JSONObject getPlayer(UUID uuid) {
        var players = getPlayers();
        for (var object : players) {
            var player = (JSONObject) object;
            var playerUuid = UUID.fromString((String) player.get("uuid"));
            if (playerUuid.equals(uuid)) {
                return player;
            }
        }
        return null;
    }
}
