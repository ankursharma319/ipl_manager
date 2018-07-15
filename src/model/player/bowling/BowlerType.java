package model.player.bowling;

public enum BowlerType
{
	RF("F", "Right Arm Fast"), LF("F", "Left Arm Fast"), RFM("F", "Right Arm Medium Fast"),
	LFM("F", "Left Arm Medium Fast"), RM("S", "Right Arm Medium"), LM("S", "Left Arm Medium"),
	OS("S", "Off Spinner"), LS("S", "Leg Spinner"), SLA("S", "Slow Left-armer");
	
	private String category, fullName;
	
	BowlerType(String category, String fullName) {
		this.category = category;
		this.fullName = fullName;
	}	
	public String getCategory()	{
		return this.category;
	}	
	public String getFullName() {
		return this.fullName;
	}
}