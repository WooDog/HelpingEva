# HelpingEva

HelpingEva implements a bot, which sends helpful information about 
pre-defined help topics to players. Works as player and from the console.

A Minecraft Bukkit Plugin written by WooDog.

HelpingEva is free software distributed under the terms of the MIT license.
Sources can be found on [Github](https://github.com/WooDog/HelpingEva).

## Commands

/eva
Displays the exact coordinates of the player.

/eva help
Displays evas help.

/eva list
Lists all help topics.

/eva tell <player> <topic>
Writes information on the topic in the players game chat.

/eva.debug
When given as a player it toggles debugging info's to the console for this player.
When given from console it toggles debugging info's for all players.

/eva.reload
Reloads configuration from disk. 
Usually located in <server home>/plugins/HelpingEva/config.yml


### config.yml example
    # default config.yml for HelpingEva
	config_file_version: 1.0.0
	help:
	  - "&AI am &3Eva&A. Your friendly bot to automate help."
	  - "&6/eva tell <user> <topic>"
	  - "&Ato message a user the predefined message about a <topic>."
	  - "&6/eva list"
	  - "&ALists all known topics."
	  - "&6/eva.debug"
	  - "&Ato toggle debugging."
	  - "&6/eva.reload"
	  - "&Ato reload the config file from disk."
	  - "&ASee: &6https://github.com/WooDog/HelpingEva&A for more information."
	# Single line topics only until now!
	help_topics:
	  bucket: "&AA bucket is a bucket is a bucket."
	  mods: "&AMostly useful ..."
	  source: "&6See: https://github.com/WooDog/HelpingEva"  

Simple chat colors are supported.

 