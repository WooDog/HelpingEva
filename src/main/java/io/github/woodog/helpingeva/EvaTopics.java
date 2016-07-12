package io.github.woodog.helpingeva;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class EvaTopics {
	private final HelpingEva plugin;
	//private final HashMap<String, String> helpTopics = new HashMap<>();
	private Map<String, Object> helpTopics;
	private String configFileVersion;

	public EvaTopics(HelpingEva plugin) {
		this.plugin = plugin;
		this.initTopics();
	}

	public void initTopics() {
		plugin.getLogger().info("Initializing helpTopics ...");

		helpTopics.clear();
		configFileVersion = plugin.getConfig().getString("config_file_version");
		final Map<String, Object> valuesToSerialize = plugin.getConfig().getConfigurationSection("help_topics").getValues(false);
		
		for (Map.Entry<String, Object> entry : valuesToSerialize.entrySet()) {
//			helpTopics = EvaHelpTopic.deserialize(
//					plugin.getConfig().getConfigurationSection("help_topics").getValues(false));

			helpTopics.put(entry.getKey().toLowerCase(), 
					EvaHelpTopic.deserialize(
							plugin.getConfig().getConfigurationSection(entry.getKey().toLowerCase()).getValues(false), 
							configFileVersion));
		}
		plugin.getLogger().info("Initializing helpTopics done.");
		plugin.getLogger().info("Initializing helpTopics config_file_version: " 
				+ configFileVersion 
				+ " done.");
	}

	public boolean hasHelpTopic(String topic) {
		return this.helpTopics.containsKey(topic);
	}
	
	public String getHelpTopic(String topic) {
		// TODO only single line topics until now :-(
		return plugin.colorString(((EvaHelpTopic) this.helpTopics.get(topic)).getTopicLine());
	}
	
	public String getHelpTopicsList() {
		String delimiter = ", ";
		return StringUtils.join(this.helpTopics.keySet(), delimiter);
	}
	
}