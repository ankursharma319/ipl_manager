package engine.core;

public enum BallResult 
{
	Dot(0, "."), Single(1, "1"), Two(2, "2"), Four(4, "4"), Six(6, "6"), Chance(0, "!"), Out(0, "W"), Wide(1, "wd"), NoBall(1, "nb");
	
	private int runs;
	private String symbol;
	
	BallResult(int runs, String symbol) {
		this.runs = runs;
		this.symbol = symbol;
	}
	
	public int getRuns() {
		return runs;
	}
	public String getSymbol() {
		return symbol;
	}
}