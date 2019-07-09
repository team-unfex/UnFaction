package nl.unfex.unfactions.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.unfex.unfactions.Main;
import nl.unfex.unfactions.utils.ChatUtils;

public class CMDExecutor implements CommandExecutor {
	
	private Main instance;
	
	private List<PlayerCommand> playerCommands = new ArrayList<>();
	
	private List<ConsoleCommand> consoleCommands = new ArrayList<>();
	
	public CMDExecutor() {
		instance = Main.getInstance();
		// Player commands.
		
		// Console commands.
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&c"+ instance.getDescription().getFullName() + " &fby " + instance.getDescription().getAuthors().toString()));
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&7/unfaction help for all the commands."));
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
			return true;
		}
		
		if(!isPlayerCommand(args[0])) {
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&c"+ instance.getDescription().getFullName() + " &fby " + instance.getDescription().getAuthors().toString()));
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&7/unfaction help for all the commands."));
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
			return true;
		}
		
		if(!isConsoleCommand(args[0])) {
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&c"+ instance.getDescription().getFullName() + " &fby " + instance.getDescription().getAuthors().toString()));
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&7/unfaction help for all the commands."));
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&7=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
			return true;
		}
		
		for(PlayerCommand command : playerCommands) {
			if(command.getName() == args[0]) {
				if(!(sender instanceof Player)) {
					sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&4You have to be a player to execute this command!"));
					return true;
				}
				Player player = (Player) sender;
				
				if(!player.hasPermission(command.getPermission())) {
					player.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&4You do not have the permission: " + command.getPermission() + "."));
					return true;
				}
				
				command.run(player, args);
			}
		}
		
		for(ConsoleCommand command : consoleCommands) {
			if(command.getName() == args[0]) {
				
				if(!sender.hasPermission(command.getPermission())) {
					sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&4You do not have the permission: " + command.getPermission() + "."));
					return true;
				}
				
				command.run(sender, args);
			}
		}
		
		return true;
	}

	private boolean isPlayerCommand(String cmd) {
		for(PlayerCommand command : playerCommands) {
			if(command.getName().equalsIgnoreCase(cmd)) {
				return true;
			}
		}
		return false;
	}

	private boolean isConsoleCommand(String cmd) {
		for(ConsoleCommand command : consoleCommands) {
			if(command.getName().equalsIgnoreCase(cmd)) {
				return true;
			}
		}
		return false;
	}

	public List<ConsoleCommand> getConsoleCommands() {
		return consoleCommands;
	}

	public List<PlayerCommand> getPlayerCommands() {
		return playerCommands;
	}
	
	
	
}
