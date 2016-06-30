package io.github.woodog.helpingeva;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handler for the /evareload command.
 * @author WooDog
 */
public class EvaReloadCommand implements CommandExecutor {
	private final HelpingEva plugin;

	public EvaReloadCommand(HelpingEva plugin) {
        this.plugin = plugin;
    }

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		plugin.reloadConfig();
		plugin.getLogger().info("Eva reloaded config file from disk.");
		return true;
	}
}
