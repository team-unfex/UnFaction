package nl.unfex.unfactions.objects;

import java.util.UUID;

public class FactionPlayer {
	
	private UUID uuid;
	private FactionRank rank;
	
	public FactionPlayer(UUID uuid, FactionRank rank) {
		this.uuid = uuid;
		this.rank = rank;
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	public FactionRank getRank() {
		return rank;
	}
	
	public void setRank(FactionRank rank) {
		this.rank = rank;
	}

}
