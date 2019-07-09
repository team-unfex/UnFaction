package nl.unfex.unfactions.commands;

import org.bukkit.entity.Player;

public abstract class PlayerCommand {
	
	private String name;
	private String usage;
	private String description;
	private String permission;

	public PlayerCommand(String name, String usage, String description, String permission) {
		this.name = name;
		this.usage = usage;
		this.description = description;
		this.permission = permission;
	}
	
	abstract void run(Player p, String[] args);
	
	public String getName() {
		return name;
	}
	
	public String getUsage() {
		return usage;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getPermission() {
		return permission;
	}
	
}
