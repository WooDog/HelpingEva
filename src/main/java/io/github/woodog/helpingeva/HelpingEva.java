package io.github.woodog.helpingeva;

import org.bukkit.plugin.java.JavaPlugin;

public final class HelpingEva extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}

}
