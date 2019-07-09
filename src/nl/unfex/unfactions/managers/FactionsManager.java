package nl.unfex.unfactions.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import nl.unfex.unfactions.objects.Faction;

public class FactionsManager {
	
	private static FactionsManager factionsManager;
	
	private List<Faction> factions = new ArrayList<Faction>();
	
	private final SettingsManager settingsManager = SettingsManager.getSettingsManager();
	private final FileConfiguration data = settingsManager.getData();
	
	private FactionsManager() { }
	
	public void setup() {
		if(!data.isConfigurationSection("factions")) return;
		
		for(String factionName : data.getConfigurationSection("factions").getKeys(false)) {
			String dataPrefix = "factions." + factionName + ".";
			
			String description = data.getString(dataPrefix + "description");
			
			List<UUID> memberUUIDs = new ArrayList<>(); 
			List<String> challenges = new ArrayList<>(); 
			
			for(String member : data.getConfigurationSection(dataPrefix + "members").getKeys(false)) {
				memberUUIDs.add(UUID.fromString(member));
			}
			
			for(String challenge : data.getConfigurationSection(dataPrefix + "challenges").getKeys(false)) {
				challenges.add(challenge);
			}
			
			
			World homeWorld = Bukkit.getWorld(data.getString(dataPrefix + "home.world"));
			int homeX = data.getInt(dataPrefix + "home.x");
			int homeY = data.getInt(dataPrefix + "home.y");
			int homeZ = data.getInt(dataPrefix + "home.z");
			
			Faction faction = new Faction(factionName, description, memberUUIDs, challenges, homeWorld, homeX, homeY, homeZ);
			
			factions.add(faction);
			
		}
	}
	
	public void saveFactions() {
		for(Faction faction : factions) {
			faction.save();
		}
	}
	
	public Faction getFaction(Player player) {
		return getFaction(player.getUniqueId());
	}
	
	public Faction getFaction(UUID uuid) {
		for(Faction faction : factions) {
			if(faction.getPlayerUUIDs().contains(uuid)) {
				return faction;
			}
		}
		return null;
	}
	
	public Faction getFaction(String factionName) {
		for(Faction faction : factions) {
			if(faction.getName().equalsIgnoreCase(factionName)) {
				return faction;
			}
		}
		return null;
	}
	
	public boolean factionExists(String factionName) {
		return getFaction(factionName) != null;
	}
	
	public static FactionsManager getFactionsManager() {
		if(factionsManager == null) {
			factionsManager = new FactionsManager();
		}
		return factionsManager;
	}

}
