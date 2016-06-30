package io.github.woodog.helpingeva;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Handler for the /eva command.
 * @author WooDog
 */
public class EvaCommand implements CommandExecutor {
	private final HelpingEva plugin;

	public EvaCommand(HelpingEva plugin) {
        this.plugin = plugin;
    }
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
		if (!(sender instanceof Player)) {
            return false;
        }

		Player player = (Player) sender;
        
        if (split.length == 0) {
        	return noCommandParam(player);
        } else {
        	return multipleCommandParam(player, command, label, split);
        }
	}

	public boolean noCommandParam(Player player) {
		Location location = player.getLocation();

		player.sendMessage("You are currently at " + location.getX() +"," + location.getY() + "," + location.getZ() +
                " with " + location.getYaw() + " yaw and " + location.getPitch() + " pitch");

		return true;
	}

	private boolean multipleCommandParam(Player player, Command command, String label, String[] split) {
		String cmd = split[0].toLowerCase();
		
		if (cmd.equals("help")) {
			return sendText(player, "help");
		} else if (cmd.equals("tell")) {
			// TODO: Add permissions check here for sub commands
			// Usage: /eva tell WooDog about tipps
			
			
			if (split.length >= 1) {
				Player receiver = (Bukkit.getServer().getPlayer(split[1]));
		        if (receiver == null) {
		           player.sendMessage(split[1] + " is not online!");
		           return false;
		        } else {
					receiver.sendMessage(plugin.getConfig().getString("message"));
					List<String> rules = plugin.getConfig().getStringList("rules");
		            for (String s : rules){
		                receiver.sendMessage(s);
		            }
		            player.sendMessage("Eva: I told " + split[1] + " about ...");
					return true;
		        }
			} else {
				return false;
			}
		}

		plugin.debugPrint(player, "Command is: " + command);
		plugin.debugPrint(player, "Label is: " + label);
		plugin.debugPrint(player, "cmd is: " + cmd);
		plugin.debugPrint(player, "Split is: " + StringUtils.join(split, " "));
		plugin.debugPrint(player, "Lenght of Split is: " + split.length);

		return false;
	}

	private boolean sendText(Player player, String topic) {
		List<String> text = plugin.getConfig().getStringList(topic);
        for (String line : text){
        	if (line.length() == 0) {
        		player.sendMessage(" ");
        	} else {
        		player.sendMessage("Len: " + line.length());
        		player.sendMessage(line);
        	}
        }
        return true;
	}
}
