package nl.unfex.unfactions.managers;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SettingsManager {
	
	private Logger logger = Bukkit.getServer().getLogger();
	
	private static SettingsManager settingsManager;
	
	/* Config files BEGIN */
	private File configFile;
	private FileConfiguration config;
	
	private File dataFile;
	private FileConfiguration data;
	
	private File challengesFile;
	private FileConfiguration challenges;
	
	private File langFile;
	private FileConfiguration lang;
	/* Config files END */
	
	private SettingsManager() {
		/* Keep empty ples. */
	} 
	
	public void setup() {
		configFile = new File("plugins/UnFaction/", "config.yml");
		dataFile = new File("plugins/UnFaction/", "data.yml");
		challengesFile = new File("plugins/UnFaction/", "challenges.yml");
		langFile = new File("plugins/UnFaction/", "lang.yml");
		
		/* Makes the file if they don't exist. */
		if(!configFile.exists()) {
			try {
                configFile.createNewFile();
            } catch (IOException e) {
            	e.printStackTrace();
            }
		}
		
		if(!dataFile.exists()) {
			try {
                dataFile.createNewFile();
            } catch (IOException e) {
            	e.printStackTrace();
            }
		}
		
		if(!challengesFile.exists()) {
			try {
                challengesFile.createNewFile();
            } catch (IOException e) {
            	e.printStackTrace();
            }
		}
		
		if(!langFile.exists()) {
			try {
                langFile.createNewFile();
            } catch (IOException e) {
            	e.printStackTrace();
            }
		}
		
		/* Set the FileConfiguration variables. */
		config = YamlConfiguration.loadConfiguration(configFile);
		lang = YamlConfiguration.loadConfiguration(langFile);
		challenges = YamlConfiguration.loadConfiguration(challengesFile);
		lang = YamlConfiguration.loadConfiguration(langFile);
	}
	
	public void saveConfig() {
		try {
            config.save(configFile);
        } catch (IOException e) {
            logger.severe(ChatColor.RED + "Could not save config.yml!");
        }
	}
	
	public void saveData() {
		try {
            data.save(dataFile);
        } catch (IOException e) {
        	logger.severe(ChatColor.RED + "Could not save data.yml!");
        }
	}
	
	public void saveChallenges() {
		try {
            challenges.save(challengesFile);
        } catch (IOException e) {
        	logger.severe(ChatColor.RED + "Could not save challenges.yml!");
        }
	}
	
	public void saveLang() {
		try {
            lang.save(langFile);
        } catch (IOException e) {
        	logger.severe(ChatColor.RED + "Could not save lang.yml!");
        }
	}
	
	public void reload() {
		config = YamlConfiguration.loadConfiguration(configFile);
		data = YamlConfiguration.loadConfiguration(dataFile);
		challenges = YamlConfiguration.loadConfiguration(challengesFile);
		lang = YamlConfiguration.loadConfiguration(langFile);
	}
	
	public FileConfiguration getConfig() {
		return config;
	}

	public FileConfiguration getData() {
		return data;
	}

	public FileConfiguration getChallenges() {
		return challenges;
	}

	public FileConfiguration getLang() {
		return lang;
	}

	public static SettingsManager getSettingsManager() {
		if(settingsManager == null) {
			settingsManager = new SettingsManager();
		}
		return settingsManager;
	}
	

}
