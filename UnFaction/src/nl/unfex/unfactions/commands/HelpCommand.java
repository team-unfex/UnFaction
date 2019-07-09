package nl.unfex.unfactions.commands;

import org.bukkit.command.CommandSender;

import nl.unfex.unfactions.Main;
import nl.unfex.unfactions.utils.ChatUtils;

public class HelpCommand extends ConsoleCommand {

	public HelpCommand(String name, String usage, String description, String permission) {
		super(name, usage, description, permission);
	}

	@Override
	void run(CommandSender sender, String[] args) {
		CMDExecutor cmdExecutor = Main.getInstance().getCmdExecutor();
		sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&7=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-="));
		
		
		for(ConsoleCommand command : cmdExecutor.getConsoleCommands()) {
			sender.sendMessage(ChatUtils.color("&5" + command.getUsage() + " &f- &2 " + command.getDescription()));
		}
		sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&b&lThe following commands can only be executed by players:"));
		for(PlayerCommand command : cmdExecutor.getPlayerCommands()) {
			sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&5" + command.getUsage() + " &f- &2 " + command.getDescription()));
		}
		
		sender.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&7=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-="));
	}



}
