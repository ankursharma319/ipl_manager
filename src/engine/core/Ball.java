////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
//////////////////////////// Ball Class ////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////

package engine.core;

public class Ball 
{
	public Ball()
	{
		
	}
	
	public double getDotWeight() {
		return dotWeight;
	}
	public void setDotWeight(double dotWeight) {
		this.dotWeight = dotWeight;
	}

	public double getSingleWeight() {
		return singleWeight;
	}

	public void setSingleWeight(double singleWeight) {
		this.singleWeight = singleWeight;
	}

	public double getTwoWeight() {
		return twoWeight;
	}

	public void setTwoWeight(double twoWeight) {
		this.twoWeight = twoWeight;
	}

	public double getFourWeight() {
		return fourWeight;
	}

	public void setFourWeight(double fourWeight) {
		this.fourWeight = fourWeight;
	}

	public double getSixWeight() {
		return sixWeight;
	}

	public void setSixWeight(double sixWeight) {
		this.sixWeight = sixWeight;
	}

	public double getChanceWeight() {
		return chanceWeight;
	}

	public void setChanceWeight(double chanceWeight) {
		this.chanceWeight = chanceWeight;
	}

	private double dotWeight;
	private double singleWeight;
	private double twoWeight;
	private double fourWeight;
	private double sixWeight;
	private double chanceWeight;
}
