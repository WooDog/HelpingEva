package io.github.woodog.helpingeva;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Handler for the /debug sample command.
 * @author SpaceManiac
 */
public class SampleDebugCommand implements CommandExecutor {
    private final HelpingEva plugin;

    public SampleDebugCommand(HelpingEva plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            plugin.setDebugging(player, !plugin.isDebugging(player));

            return true;
        } else {
        	sender.sendMessage("You must be a player!");
            return false;
        }
    }
}