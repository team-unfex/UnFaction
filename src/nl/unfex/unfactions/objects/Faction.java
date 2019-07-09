package nl.unfex.unfactions.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import nl.unfex.unfactions.managers.SettingsManager;

public class Faction {
	
	private final SettingsManager settingsManager = SettingsManager.getSettingsManager();
	private final FileConfiguration data = settingsManager.getData();
	
	private String name;
	private String description;
	
	private List<UUID> playerUUIDs = new ArrayList<>();
	private List<String> challengesCompleted = new ArrayList<>();
	
	private World homeWorld;
	private int homeX;
	private int homeY;
	private int homeZ;
	
	private Location home;
	
	public Faction(String name, String description, List<UUID> playerUUIDs, List<String> challengesCompleted, World homeWorld, int homeX, int homeY, int homeZ) {
		this.name = name;
		this.description = description;
		this.playerUUIDs = playerUUIDs;
		this.challengesCompleted = challengesCompleted;
		
		this.homeWorld = homeWorld;
		this.homeX = homeX;
		this.homeY = homeY;
		this.homeZ = homeZ;
		
		this.home = new Location(homeWorld, homeX, homeY, homeZ);
	}
	
	public void save() {
		data.set("factions." + name + ".description", description);
		data.set("factions." + name + ".members", playerUUIDs);
		data.set("factions." + name + ".challenges", challengesCompleted);
		if(home != null) {
			data.set("factions." + name + ".home.world", homeWorld.getName());
			data.set("factions." + name + ".home.x", homeX);
			data.set("factions." + name + ".home.y", homeY);
			data.set("factions." + name + ".home.z", homeZ);
		}
		settingsManager.saveData();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UUID> getPlayerUUIDs() {
		return playerUUIDs;
	}

	public List<String> getChallengesCompleted() {
		return challengesCompleted;
	}

	public World getHomeWorld() {
		return homeWorld;
	}

	public void setHomeWorld(World homeWorld) {
		this.homeWorld = homeWorld;
	}

	public int getHomeX() {
		return homeX;
	}

	public void setHomeX(int homeX) {
		this.homeX = homeX;
	}

	public int getHomeY() {
		return homeY;
	}

	public void setHomeY(int homeY) {
		this.homeY = homeY;
	}

	public int getHomeZ() {
		return homeZ;
	}

	public void setHomeZ(int homeZ) {
		this.homeZ = homeZ;
	}

	public Location getHome() {
		return home;
	}

	public void setHome(Location home) {
		this.home = home;
	}
	
	

}
