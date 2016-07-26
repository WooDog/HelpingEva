package io.github.woodog.helpingeva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EvaTopics {
	private final HelpingEva plugin;
	private Map<String, Object> helpTopics = new HashMap<>();
	private String configFileVersion;

	public EvaTopics(HelpingEva plugin) {
		this.plugin = plugin;
		this.initTopics();
	}

	public void initTopics() {
		plugin.getLogger().info("Initializing helpTopics ...");

		helpTopics.clear();
		configFileVersion = plugin.getConfig().getString("config_file_version");
		plugin.getLogger().info("config_file_version: " + configFileVersion);
		final Map<String, Object> valuesToSerialize = plugin.getConfig().getConfigurationSection("help_topics")
				.getValues(false);

		for (Map.Entry<String, Object> entry : valuesToSerialize.entrySet()) {
			String topicKey = "help_topics." + entry.getKey();
			plugin.getLogger().info("Key: " + topicKey);

			plugin.getLogger().info("Key: " + plugin.getConfig().getConfigurationSection(topicKey).getValues(false));

			helpTopics.put(entry.getKey().toLowerCase(), EvaHelpTopic.deserialize(
					plugin.getConfig().getConfigurationSection(topicKey).getValues(false), configFileVersion));
		}
		plugin.getLogger().info("Initializing helpTopics config_file_version: " + configFileVersion + " done.");
	}

	public boolean hasHelpTopic(String topic) {
		return this.helpTopics.containsKey(topic);
	}

	public List<String> getHelpTopicText(String topic) {

		List<String> helpTopicText = new ArrayList<String>();
		String topicType = ((EvaHelpTopic) this.helpTopics.get(topic)).getTopicType();

		if (topicType.equals("string")) {
			helpTopicText.add(plugin.colorString(((EvaHelpTopic) this.helpTopics.get(topic)).getTopicLine()));
		}
		if (topicType.equals("stringList")) {
			for (String line : ((EvaHelpTopic) this.helpTopics.get(topic)).getTopicLineList()) {
				helpTopicText.add(plugin.colorString(line));
			}
		}
		return helpTopicText;
	}

	public String getHelpTopicsList() {
		String delimiter = ", ";
		return StringUtils.join(this.helpTopics.keySet(), delimiter);
	}

	public void sendTopic(CommandSender sender, Player receiver, String topic) {
		receiver.sendMessage(
				plugin.colorString("&AI am &3Eva&A. " + sender.getName() + " asked me to tell you about:"));
		if (this.hasHelpTopic(topic)) {
			receiver.sendMessage(plugin.colorString("&A" + topic));
			for (String line : this.getHelpTopicText(topic)) {
				receiver.sendMessage(line);
			}
			sender.sendMessage("Eva: I told " + receiver.getName() + " about " + topic);
		}
	}
}