package nl.unfex.unfactions.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import nl.unfex.unfactions.objects.Faction;
import nl.unfex.unfactions.objects.FactionPlayer;
import nl.unfex.unfactions.objects.FactionRank;

public class FactionsManager {
	
	private static FactionsManager factionsManager;
	
	private List<Faction> factions = new ArrayList<Faction>();
	
	private final SettingsManager settingsManager = SettingsManager.getSettingsManager();
	private final FileConfiguration data = settingsManager.getData();
	
	private FactionsManager() { }
	
	public void createFaction(String name, Player player) {
		List<FactionPlayer> members = new ArrayList<>();
		members.add(new FactionPlayer(player.getUniqueId(), FactionRank.LEADER));
		Faction faction = new Faction(name, null, members, new ArrayList<String>(), null, 0, 0, 0);
		factions.add(faction);
		
		for(FactionPlayer member : members) {
			data.set("factions." + name + ".members." + member.getUUID() + ".rank", member.getRank());
		}
	}
	
	public void setup() {
		if(!data.isConfigurationSection("factions")) return;
		
		for(String factionName : data.getConfigurationSection("factions").getKeys(false)) {
			String dataPrefix = "factions." + factionName + ".";
			
			String description = data.getString(dataPrefix + "description");
			
			List<FactionPlayer> members = new ArrayList<>(); 
			List<String> challenges = new ArrayList<>(); 
			
			for(String member : data.getConfigurationSection(dataPrefix + "members").getKeys(false)) {
				members.add(new FactionPlayer(UUID.fromString(member), getRankByString(data.getString(dataPrefix + "members." + member + ".rank"))));
			}
			
			for(String challenge : data.getConfigurationSection(dataPrefix + "challenges").getKeys(false)) {
				challenges.add(challenge);
			}
			
			if(data.contains(dataPrefix + "home.world")) {
				World homeWorld = Bukkit.getWorld(data.getString(dataPrefix + "home.world"));
				int homeX = data.getInt(dataPrefix + "home.x");
				int homeY = data.getInt(dataPrefix + "home.y");
				int homeZ = data.getInt(dataPrefix + "home.z");
			}
			
			Faction faction = new Faction(factionName, description, members, challenges, homeWorld, homeX, homeY, homeZ);
			
			factions.add(faction);
			
		}
	}
	
	private FactionRank getRankByString(String rank) {
		if(rank.equalsIgnoreCase(FactionRank.COLEADER.getName())) return FactionRank.COLEADER;
		if(rank.equalsIgnoreCase(FactionRank.LEADER.getName())) return FactionRank.LEADER;
		if(rank.equalsIgnoreCase(FactionRank.MEMBER.getName())) return FactionRank.MEMBER;
		return FactionRank.NONE;
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
			for(FactionPlayer player : faction.getMembers()) {
				if(player.getUUID().equals(uuid)) {
					return faction;
				}
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
