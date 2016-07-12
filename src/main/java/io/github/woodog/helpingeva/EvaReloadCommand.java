package io.github.woodog.helpingeva;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Handler for the /eva.reload command.
 * @author WooDog
 */
public class EvaReloadCommand implements CommandExecutor {
	private final HelpingEva plugin;

	public EvaReloadCommand(HelpingEva plugin) {
        this.plugin = plugin;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if (sender.hasPermission("eva.debug")) {
			plugin.reloadConfig();
			plugin.evaTopics.initTopics();
			plugin.getLogger().info("Eva reloaded config file from disk, triggered by " + sender.getName());
			if (sender instanceof Player) {
				sender.sendMessage("Eva reloaded config file from disk.");
			}
			return true;
		} else {
			return false;
		}
	}
}
