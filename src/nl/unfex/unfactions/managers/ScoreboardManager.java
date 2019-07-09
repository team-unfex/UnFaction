package nl.unfex.unfactions.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import nl.unfex.unfactions.Main;

public class ScoreboardManager {
	
	private Main main;
	
	private static ScoreboardManager scoreboardManager;
	
	private SettingsManager settingsManager = SettingsManager.getSettingsManager();
	
	public void ScoreboardManager() {}
	
	@SuppressWarnings("deprecation")
	public void setup() {
		org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		
		if (settingsManager.getConfig().getString("Scoreboard.MainBoard.Enabled") == "Enabled") {
			setupMainBoard(board);
		}
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(board);
		}
		
		updateHealthFoodBoard();
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
	
	public void updateHealthFoodBoard() {
		new BukkitRunnable() {
			public void run() {
				int wichBoard = 0;
				for (Player player : Bukkit.getOnlinePlayers()) {
					org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
					Scoreboard board = manager.getNewScoreboard();
					
					if (wichBoard == 0) {
						if (settingsManager.getConfig().getString("Scoreboard.HealthBoard.Enabled") == "Enabled") {
							setupHealthBoard(board);
						}
						wichBoard =+ 1;
					}
					
					if (wichBoard == 1) {
						if (settingsManager.getConfig().getString("Scoreboard.FoodBoard.Enabled") == "Enabled") {
							setupFoodBoard(board);
						}
						wichBoard =- 1;
					}
				}
			}
		}.runTaskTimer(main.getInstance(), 0L, 100L);
	}
	
	public static ScoreboardManager getScoreboardManager() {
		if(scoreboardManager == null) {
			scoreboardManager = new ScoreboardManager();
		}
		return scoreboardManager;
	}
}
