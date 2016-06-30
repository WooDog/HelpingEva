package io.github.woodog.helpingeva;

import java.util.HashMap;
import org.bukkit.entity.Player;
//import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.configuration.file.FileConfiguration;


public final class HelpingEva extends JavaPlugin {
    private final EvaPlayerListener playerListener = new EvaPlayerListener(this);
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    private boolean globalDebug = false;

	@Override
	public void onDisable() {
        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        getLogger().info("Goodbye world! From HelpingEva.");
	}

	@Override
	public void onEnable() {
		// Save a copy of the default config.yml if one is not there
        this.saveDefaultConfig();
        getLogger().info(this.getConfig().getString("message"));
        
        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);

        // Register our commands
        getCommand("eva").setExecutor(new EvaCommand(this));
        getCommand("eva.debug").setExecutor(new EvaDebugCommand(this));
        getCommand("eva.reload").setExecutor(new EvaReloadCommand(this));

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
//        PluginDescriptionFile pdfFile = this.getDescription();
//        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
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
    
    public void debugPrint(final Player player, String message) {
    	String name = "";
    	
    	if ((player instanceof Player)) {
    		name = player.getName();
        } else {
        	name = "Console";
        }
		if (this.isDebugging(player) || this.globalDebug) {
			this.getLogger().info(name + " " + message);
		}
    }
}
