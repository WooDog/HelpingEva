package io.github.woodog.helpingeva;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Handler for the /eva command.
 * 
 * @author WooDog
 */

public class EvaCommand implements CommandExecutor {
	private final HelpingEva plugin;

	public EvaCommand(HelpingEva plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
		if (split.length == 0) {
			return noCommandParam(sender);
		} else {
			return multipleCommandParam(sender, command, label, split);
		}
	}

	public boolean noCommandParam(CommandSender sender) {
		Player player = (Player) sender;
		Location location = player.getLocation();
		player.sendMessage("You are currently at " + location.getX() + "," + location.getY() + "," + location.getZ()
				+ " with " + location.getYaw() + " yaw and " + location.getPitch() + " pitch");
		return true;
	}

	private boolean multipleCommandParam(CommandSender sender, Command command, String label, String[] split) {
		String cmd = split[0].toLowerCase();
		Player receiver = null;
		if (cmd.equals("help")) {
			return plugin.sendHelp(sender);
		} else if (cmd.equals("list")) {
			sender.sendMessage(plugin.colorString("&AI know the following topics:"));
			sender.sendMessage(plugin.colorString("&A" + plugin.evaTopics.getHelpTopicsList()));
			return true;
		} else if (cmd.equals("tell")) {
			// TODO: Add permissions check here for sub commands
			// Usage: /eva tell WooDog bucket

			// User given?
			if (split.length >= 2) {
				String userString = split[1];
				if (userString.equals("me")) {
					receiver = (Player) sender;
				} else {
					receiver = (Bukkit.getServer().getPlayer(split[1]));
				}
				if (receiver == null) {
					sender.sendMessage(split[1] + " is not online!");
					return true;
				}
			}

			// Topic given?
			if (split.length >= 3) {
				for (int i = 2; i < split.length; i++) {
					String topic = split[i].toLowerCase();
					if (plugin.evaTopics.hasHelpTopic(topic)) {
						plugin.evaTopics.sendTopic(sender, receiver, topic);
					} else {
						sender.sendMessage("Help topic " + topic + " not found!");
					}
				}
			}
			return true;
		}

		plugin.debugPrint(sender, "Command is: " + command);
		plugin.debugPrint(sender, "Label is: " + label);
		plugin.debugPrint(sender, "cmd is: " + cmd);

		return false;
	}
}
