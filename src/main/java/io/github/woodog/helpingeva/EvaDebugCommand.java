package io.github.woodog.helpingeva;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Handler for the /eva.debug command.
 * 
 * @author WooDog
 */
public class EvaDebugCommand implements CommandExecutor {
	private final HelpingEva plugin;

	public EvaDebugCommand(HelpingEva plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (sender.hasPermission("eva.debug")) {
				Player player = (Player) sender;
				plugin.setDebugging(player, !plugin.isDebugging(player));
				sender.sendMessage("Eva toggled debugging to: " + plugin.isDebugging(player));
				return true;
			} else {
				return false;
			}
		} else {
			plugin.setGlobalDebugging(!plugin.isGlobalDebugging());
			sender.sendMessage(
					"Eva assumes you are THE console! Toggling globalDebug to: " + plugin.isGlobalDebugging());
			return true;
		}
	}
}
