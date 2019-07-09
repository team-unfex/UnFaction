package nl.unfex.unfactions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import nl.unfex.unfactions.managers.FactionsManager;
import nl.unfex.unfactions.managers.ScoreboardManager;
import nl.unfex.unfactions.managers.SettingsManager;

public class Main extends JavaPlugin {
	
	private PluginManager pm;
	
	// Self-made managers
	private final SettingsManager settingsManager = SettingsManager.getSettingsManager();
	private final FactionsManager factionsManager = FactionsManager.getFactionsManager();
	private final ScoreboardManager scoreboardManager = ScoreboardManager.getScoreboardManager();
	
	private static Main instance;
	
	
	public void onEnable() {
		pm = Bukkit.getPluginManager();
		
		/* Setting up the config */
		loadConfig();
		
		/* Loading the factions. */
		factionsManager.setup();
		scoreboardManager.setup();
		
		Bukkit.getConsoleSender().sendMessage("===========================");
		Bukkit.getConsoleSender().sendMessage("                           ");
		Bukkit.getConsoleSender().sendMessage("      " + getDescription().getFullName() + " - v" + getDescription().getVersion() + "      ");
		Bukkit.getConsoleSender().sendMessage("         [ENABLED]         ");
		Bukkit.getConsoleSender().sendMessage("                           ");
		Bukkit.getConsoleSender().sendMessage("===========================");
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("===========================");
		Bukkit.getConsoleSender().sendMessage("                           ");
		Bukkit.getConsoleSender().sendMessage("      " + getDescription().getFullName() + " - v" + getDescription().getVersion() + "      ");
		Bukkit.getConsoleSender().sendMessage("         [DISABLED]        ");
		Bukkit.getConsoleSender().sendMessage("                           ");
		Bukkit.getConsoleSender().sendMessage("===========================");
	}
	
	private void loadConfig() {
		settingsManager.setup();
		
		/* This is temporary. */
		settingsManager.getLang().addDefault("en-us.prefix", "&7[&cUn&2Factions&7]");
		settingsManager.getLang().options().copyDefaults(true);
		settingsManager.saveLang();
	}
	
	public static Main getInstance() {
		return instance;
	}

}
