package nl.unfex.unfactions.commands;

import org.bukkit.entity.Player;

import nl.unfex.unfactions.utils.ChatUtils;

public class CreateCommand extends PlayerCommand {

	public CreateCommand(String name, String usage, String description, String permission) {
		super(name, usage, description, permission);
	}

	@Override
	void run(Player p, String[] args) {
		if(args.length != 2) {
			p.sendMessage(ChatUtils.PREFIX + ChatUtils.color("&cThat is not how you use the command. The usage: " + this.getUsage()));
			return;
		}
		
	}

}
