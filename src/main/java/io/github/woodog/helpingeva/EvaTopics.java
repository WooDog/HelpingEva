package io.github.woodog.helpingeva;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class EvaTopics {
	private final HelpingEva plugin;
	private final HashMap<String, String> helpTopics = new HashMap<>();

	public EvaTopics(HelpingEva plugin) {
		this.plugin = plugin;
		this.initTopics();
	}

	public void initTopics() {
		// TODO Auto-generated method stub
		plugin.getLogger().info("Initializing helpTopics ...");
		helpTopics.clear();
		final Map<String, Object> values = plugin.getConfig().getConfigurationSection("help_topics").getValues(false);
		for (Map.Entry<String, Object> entry : values.entrySet()) {
			helpTopics.put(entry.getKey().toLowerCase(), entry.getValue().toString());
		}
		plugin.getLogger().info("Initializing helpTopics done.");
	}

	public boolean hasHelpTopic(String topic) {
		return this.helpTopics.containsKey(topic);
	}
	
	public String getHelpTopic(String topic) {
		// TODO only single line topics until now :-(
		return plugin.colorString(this.helpTopics.get(topic));
	}
	
	public String getHelpTopicsList() {
		String delimiter = ", ";
		return StringUtils.join(this.helpTopics.keySet(), delimiter);
	}

}