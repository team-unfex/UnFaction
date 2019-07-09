package nl.unfex.unfactions.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {
	
	private static ScoreboardManager scoreboardManager;
	
	private SettingsManager settingsManager = SettingsManager.getSettingsManager();
	
	private ScoreboardManager() {}
	
	public void setup() {
		org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		
		if (settingsManager.getConfig().getString("Scoreboard.MainBoard.Enabled") == "Enabled") {
			setupMainBoard(board);
		}
		if (settingsManager.getConfig().getString("Scoreboard.HealthBoard.Enabled") == "Enabled") {
			setupHealthBoard(board);
		}
		if (settingsManager.getConfig().getString("Scoreboard.FoodBoard.Enabled") == "Enabled") {
			setupFoodBoard(board);
		}
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(board);
		}
	}
	
	public void setupMainBoard(Scoreboard board) {
		Objective mainObj = board.registerNewObjective("MainUFBoard", "dummy");
	}
	
	public void setupHealthBoard(Scoreboard board) {
		Objective healthObj = board.registerNewObjective("UFHealth", "health");
		
		healthObj.setDisplaySlot(DisplaySlot.BELOW_NAME);
		healthObj.setDisplayName(settingsManager.getConfig().getString("Scoreboard.HealthBoard.DisplayName"));
	}
	
	public void setupFoodBoard(Scoreboard board) {
		Objective foodObj = board.registerNewObjective("UFFood", "hunger");
		
		foodObj.setDisplaySlot(DisplaySlot.BELOW_NAME);
		foodObj.setDisplayName(settingsManager.getConfig().getString("Scoreboard.FoodBoard.DisplayName"));
	}
	
	public static ScoreboardManager getScoreboardManager() {
		if(scoreboardManager == null) {
			scoreboardManager = new ScoreboardManager();
		}
		return scoreboardManager;
	}
}
