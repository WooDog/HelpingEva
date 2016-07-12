# Configfile Versions and Examples

## Version 1.0.0 - depreciated

First config file. Help topics in one line as a string.

Example:

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
	  
## Version 1.0.1

Addition of multiline topics

Example:

    # default config.yml for HelpingEva
	config_file_version: 1.0.1
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
	  bucket: 
	    type: string 
	    data: "&AA bucket is a bucket is a bucket."
	  mods: 
	    type: string
	    data: "&AMostly useful ..."
	  source: 
	    type: string
	    data: "&6See: https://github.com/WooDog/HelpingEva"  
