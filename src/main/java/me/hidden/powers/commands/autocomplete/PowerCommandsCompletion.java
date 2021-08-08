package me.hidden.powers.commands.autocomplete;

import me.hidden.powers.managers.PowerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PowerCommandsCompletion implements TabCompleter {

    private final List<String> baseCommands = List.of("add", "remove", "list", "show", "info");
    private final List<String> powerCommands;

    public PowerCommandsCompletion(PowerManager powerManager) {
        this.powerCommands = new ArrayList<>();
        for (var power : powerManager.getPowers()) {
            powerCommands.add(power.getName());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        var completions = new ArrayList<String>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], baseCommands, completions);
            Collections.sort(completions);
        }
        else if (args.length == 2) {
            StringUtil.copyPartialMatches(args[1], powerCommands, completions);
            Collections.sort(completions);
            return completions;
        }
        return completions;
    }
}
