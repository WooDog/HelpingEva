package io.github.woodog.helpingeva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 * Implementation of ConfigurationSerializable interface for help topics.
 * 
 * @author WooDog
 */

public class EvaHelpTopic implements ConfigurationSerializable {

	private String configFileVersion = "";
	private String type;
	private String topicLine = "";
	private List<String> topicLineList = new ArrayList<String>();

	// default to Version 1.0.1
	public EvaHelpTopic(String topicLine) {
		this(topicLine, "1.0.1");
	}

	public EvaHelpTopic(String topicLine, String configFileVersion) {
		this.type = "string";
		this.topicLine = topicLine;
		this.configFileVersion = configFileVersion;
	}

	public EvaHelpTopic(List<String> topicLineList) {
		this(topicLineList, "1.0.1");
	}

	public EvaHelpTopic(List<String> topicLineList, String configFileVersion) {
		this.type = "stringList";
		this.topicLineList = topicLineList;
		this.configFileVersion = configFileVersion;
	}

	@Override
	public Map<String, Object> serialize() {
		return this.serialize(this.configFileVersion);
	}

	public Map<String, Object> serialize(String configFileVersion) {
		Map<String, Object> serializedEvaHelpTopic = new HashMap<String, Object>();
		serializedEvaHelpTopic.put("type", this.type);
		if (this.type.equals("string")) {
			serializedEvaHelpTopic.put("data", (String) this.topicLine);
		}
		if (this.type.equals("StringList")) {
			serializedEvaHelpTopic.put("data", (List<String>) this.topicLineList);
		}
		return serializedEvaHelpTopic;
	}

	public static EvaHelpTopic deserialize(Map<String, Object> serializedEvaHelpTopic) {
		return deserialize(serializedEvaHelpTopic, "1.0.1");
	}

	// TODO: What to do with configFileVersion ???
	public static EvaHelpTopic deserialize(Map<String, Object> serializedEvaHelpTopic, String configFileVersion) {
		String topicType = (String) serializedEvaHelpTopic.get("type");
		if (topicType.equals("string")) {
			String topicLine = (String) serializedEvaHelpTopic.get("data");
			return new EvaHelpTopic(topicLine);
		} else {// if (topicType.equals("stringList")) {
			@SuppressWarnings("unchecked")
			List<String> topicLineList = (ArrayList<String>) serializedEvaHelpTopic.get("data");
			return new EvaHelpTopic(topicLineList);
		}
	}

	public String getTopicLine() {
		return this.topicLine;
	}

	public List<String> getTopicLineList() {
		return this.topicLineList;
	}

	public String getTopicType() {
		return this.type;
	}
}
