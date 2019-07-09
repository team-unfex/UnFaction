package nl.unfex.unfactions.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import nl.unfex.unfactions.managers.SettingsManager;

public class ChatUtils {
	
	public static String PREFIX = ChatColor.translateAlternateColorCodes('&', SettingsManager.getSettingsManager().getLang().getString("en-us.prefix") + "&f ");
	
	public static void broadcast(String message) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(PREFIX + ChatColor.translateAlternateColorCodes('&', message));
		}
	}
	
	public static String color(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

}
