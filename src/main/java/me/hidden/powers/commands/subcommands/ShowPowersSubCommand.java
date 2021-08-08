package me.hidden.powers.commands.subcommands;

import me.hidden.powers.commands.SubCommand;
import me.hidden.powers.config.PlayerConfiguration;
import me.hidden.powers.managers.PowerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;

public final class ShowPowersSubCommand implements SubCommand {

    private final PowerManager powerManager;
    private final PlayerConfiguration playerConfiguration;

    public ShowPowersSubCommand(PowerManager powerManager, PlayerConfiguration playerConfiguration) {
        this.powerManager = powerManager;
        this.playerConfiguration = playerConfiguration;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "Display your powers";
    }

    @Override
    public String getUsage() {
        return "/powers show";
    }

    @Override
    public boolean execute(Player sender, String[] args) {
        if (args.length == 1) {
            var message = new StringBuilder();
            var uuid = sender.getUniqueId();
            var data = playerConfiguration.getPlayer(uuid);
            if (data != null) {
                var powers = (JSONArray) data.get("powers");
                for (var i = 0; i < powers.size(); i++) {
                    var entry = powers.get(i);
                    var powerName = (String) entry;
                    var power = powerManager.getPower(powerName);
                    if (power != null) {
                        message.append(power.getFancyName());
                        if (i+1 < powers.size()) {
                            message.append(", ");
                        }
                    }
                }
            }
            sender.sendMessage(ChatColor.GREEN + "[Powers]");
            sender.sendMessage(message.toString());
            return false;
        }
        return true;
    }
}
