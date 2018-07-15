package engine.core;

import util.MiscUtilities;

public class WeightedRandomGenerator 
{
	private boolean isLogged = false, shouldLog=false;
	
	public WeightedRandomGenerator()
	{
		
	}
	
	// Compute the total weight of all items together
	double totalWeight = 0.0d;
	
	//Pass the Ball object to this function and this will return the ENUM value BallResult
	//ball object should contain the values out of 100 of each factor like batsman agression, bowler skill etc.
	public BallResult getRandomBallResult(Ball ball)
	{		
		if(Math.random()*100>97)
			return BallResult.Wide;
		if(Math.random()*100>96)
			return BallResult.NoBall;
		
		totalWeight = ball.getDotWeight() + ball.getSingleWeight()
				+ ball.getTwoWeight() + ball.getFourWeight() + ball.getSixWeight()
				+ ball.getChanceWeight();
		
		if (!isLogged && shouldLog) {
			MiscUtilities.log("Dot % weight: " + 100 * ball.getDotWeight()
					/ totalWeight);
			MiscUtilities.log("One % weight: " + 100 * ball.getSingleWeight()
					/ totalWeight);
			MiscUtilities.log("Two % weight: " + 100 * ball.getTwoWeight()
					/ totalWeight);
			MiscUtilities.log("Four % weight: " + 100 * ball.getFourWeight()
					/ totalWeight);
			MiscUtilities.log("Six % weight: " + 100 * ball.getSixWeight()
					/ totalWeight);
			MiscUtilities.log("Chance % weight: " + 100 * ball.getChanceWeight()
					/ totalWeight);
			isLogged = true;
		}
		// Now choose a random item
		int randomIndex = -1;
		double random = Math.random() * totalWeight;
		for (int i = 1; i < 7; ++i)
		{
		    if(i==1)
		    	random -= ball.getDotWeight();
		    if(i==2)
		    	random -= ball.getSingleWeight();
		    if(i==3)
		    	random -= ball.getTwoWeight();
		    if(i==4)
		    	random -= ball.getFourWeight();
		    if(i==5)
		    	random -= ball.getSixWeight();
		    if(i==6)
		    	random -= ball.getChanceWeight();

		    if (random <= 0.0d)
		    {
		        randomIndex = i;
		        break;
		    }
		}
	    if(randomIndex==1)
	    	return BallResult.Dot;
	    if(randomIndex==2)
	    	return BallResult.Single;
	    if(randomIndex==3)
	    	return BallResult.Two;
	    if(randomIndex==4)
	    	return BallResult.Four;
	    if(randomIndex==5)
	    	return BallResult.Six;
	    if(randomIndex==6)
	    {
	    	if(Math.random()>0.5)
	    	{
	    		return BallResult.Out;
	    	}
	    	return BallResult.Chance;
	    }
	    
	    return null;
	}
}
