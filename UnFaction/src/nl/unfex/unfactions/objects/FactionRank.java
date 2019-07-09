package nl.unfex.unfactions.objects;

public enum FactionRank {
	
	NONE(""), MEMBER("member"), COLEADER("co-leader"), LEADER("leader");
	
	private String name;
	
	FactionRank(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
