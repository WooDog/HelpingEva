package io.github.woodog.helpingeva;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class HelpingEva extends JavaPlugin {
	private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
	private final HashMap<String, String> helpTopics = new HashMap<>();
	private boolean globalDebug = false;

	@Override
	public void onDisable() {
		// NOTE: All registered events are automatically unregistered when a
		// plugin is disabled

		// EXAMPLE: Custom code, here we just output some info so we can check
		// all is well
		getLogger().info("Goodbye world! From HelpingEva.");
	}

	@Override
	public void onEnable() {
		// Save a copy of the default config.yml if one is not there
		this.saveDefaultConfig();

		// Register our events

		// Initialize helpTopics
		this.initHelpTopics();

		// Register our commands
		getCommand("eva").setExecutor(new EvaCommand(this));
		getCommand("eva.debug").setExecutor(new EvaDebugCommand(this));
		getCommand("eva.reload").setExecutor(new EvaReloadCommand(this));

		PluginDescriptionFile pdfFile = this.getDescription();
		getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}

	public void initHelpTopics() {
		getLogger().info("Initializing helpTopics ...");
		helpTopics.clear();
		final Map<String, Object> values = this.getConfig().getConfigurationSection("help_topics").getValues(false);
		for (Map.Entry<String, Object> entry : values.entrySet()) {
			helpTopics.put(entry.getKey().toLowerCase(), entry.getValue().toString());
		}
		getLogger().info("Initializing helpTopics done.");
	}

	public boolean isDebugging(final Player player) {
		if (this.isGlobalDebugging()) {
			return true;
		}
		if (debugees.containsKey(player)) {
			return debugees.get(player);
		} else {
			return false;
		}
	}

	public boolean isGlobalDebugging() {
		return globalDebug;
	}

	public void setDebugging(final Player player, final boolean value) {
		debugees.put(player, value);
	}

	public void setGlobalDebugging(final boolean value) {
		globalDebug = value;
	}

	public boolean hasHelpTopic(final String topic) {
		return this.helpTopics.containsKey(topic);
	}

	public String getHelpTopic(String topic) {
		// TODO only single line topics until now :-(
		return colorString(this.helpTopics.get(topic));
	}

	public String getHelpTopicsList() {
		String delimiter = ", ";
		return StringUtils.join(helpTopics.keySet(), delimiter);
	}

	public void debugPrint(final CommandSender sender, String message) {
		if (!(sender instanceof Player)) {
			if (this.globalDebug) {
				this.getLogger().info("Console: " + message);
			}
		} else {
			Player player = (Player) sender;
			if (this.isDebugging(player) || this.globalDebug) {
				this.getLogger().info(player.getName() + " " + message);
			}
		}
	}

	public String colorString(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	

	public boolean sendHelp(CommandSender sender) {
		List<String> text = this.getConfig().getStringList("help");
		for (String line : text) {
			sender.sendMessage(this.colorString(line));
		}
		return true;
	}

	public void sendTopic(CommandSender sender, Player receiver, String topic) {
		receiver.sendMessage(
				this.colorString("&AI am &3Eva&A. " + sender.getName() + " asked me to tell you about:"));
		if (this.hasHelpTopic(topic)) {
			receiver.sendMessage(this.colorString("&A" + topic));
			receiver.sendMessage(this.getHelpTopic(topic));
			sender.sendMessage("Eva: I told " + receiver.getName() + " about " + topic);
		}
	}

}
