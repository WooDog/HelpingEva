package io.github.woodog.helpingeva;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 * Implementation of ConfigurationSerializable interface 
 * for help topics.
 * 
 * @author WooDog
 */

public class EvaHelpTopic implements ConfigurationSerializable {

	private String configFileVersion = "";
	private String type;
	private String topicLine;


	/*
	 * 	 default to Version 1.0.1
	 */
    public EvaHelpTopic(String topicLine) {
        this(topicLine, "1.0.1");
    }
 
    public EvaHelpTopic(String topicLine, String configFileVersion) {
    	this.topicLine = topicLine;
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
        return serializedEvaHelpTopic;
    }

    public static EvaHelpTopic deserialize(Map<String, Object> serializedEvaHelpTopic) {
        String topicLine = (String) serializedEvaHelpTopic.get("data");
    	return new EvaHelpTopic(topicLine);
    }

    // TODO: What to do with configFileVersion ??? 
	public static EvaHelpTopic deserialize(Map<String, Object> serializedEvaHelpTopic, 
			String configFileVersion) {
        String topicLine = (String) serializedEvaHelpTopic.get("data");
    	return new EvaHelpTopic(topicLine);

	}

	public String getTopicLine() {
		return this.topicLine;
	}
}
