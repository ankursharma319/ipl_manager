package engine.core;

import javafx.collections.FXCollections;
import model.match.Match;
import model.match.Stadium;
import model.player.BatsmanType;
import model.player.Country;
import model.player.Handedness;
import model.player.Player;
import model.player.PlayerType;
import model.player.bowling.BowlerType;
import model.player.details.GeneralDetails;
import util.MiscUtilities;
import engine.util.Misc;

public class Engine 
{
	private WeightedRandomGenerator ranGen;
	private Ball ball;
	private boolean isLogged, shouldLog;
	
	public Engine()
	{
		ranGen = new WeightedRandomGenerator();
		isLogged = false;
		shouldLog = false;
	}
	
	public void engineTest()
	{			
		Player batsman = createBatsman();
		Player bowler = createBowler();
		Match match = createMatch();
		BallResult r = null;
		int runs=0, wickets=0, balls=0;
		
		for(int i=0;i<1200;i++)
		{
			r = nextBall(batsman, bowler, match);
			runs = runs+r.getRuns();
			
			if(r==BallResult.Out) wickets++;
			
			if(r==BallResult.Wide||r==BallResult.NoBall) {
				balls--;
			}
			balls++;
			
			r = null;
		}
		
		MiscUtilities.log("Score: " + runs + "-" + wickets + " in " + balls + " balls.");
		MiscUtilities.log("Strike Rate: " + (100*(double)runs/balls));
	}
	
	
	private Player createBatsman() 
	{
		Player p = new Player();
		GeneralDetails gd = createBatsmanGeneralDetails();
		p.setFirstName("Manoj");
		p.setLastName("Tiwary");
		
		gd.setPlayer(p);
		p.setGeneralDetails(gd);
		
		return p;
	}

	private Player createBowler() 
	{
		Player p= new Player();
		GeneralDetails gd = createBowlerGeneralDetails();
		p.setFirstName("Praveen");
		p.setLastName("Kumar");
		
		gd.setPlayer(p);
		p.setGeneralDetails(gd);
		return p;
	}
	
	private GeneralDetails createBatsmanGeneralDetails()
	{
		GeneralDetails gd = new GeneralDetails();
		gd.setBattingAbility(66);
		gd.setBattingAgressionLevel(10);
		gd.setBattingFastBowlingPreference(100);
		gd.setBattingForm(65);
		gd.setBattingHandedness(Handedness.Right);
		gd.setBatsmanType(BatsmanType.RHB);
		gd.setBattingNaturalAgression(60);
		gd.setBattingSettlement(10);
		gd.setBattingSlowBallingPreference(100);
		gd.setCoolHead(54);
		gd.setCountry(Country.India);
		gd.setPlayerType(PlayerType.Batsman);
		
		return gd;
	}
	
	private GeneralDetails createBowlerGeneralDetails()
	{
		GeneralDetails gd = new GeneralDetails();
		gd.setBowlingAbility(64);
		gd.setBowlingAgressionLevel(10);
		gd.setBowlingForm(67);
		gd.setBowlingHandedness(Handedness.Right);
		gd.setCoolHead(34);
		gd.setBowlerType(BowlerType.RFM);
		gd.setCountry(Country.India);
		gd.setPlayerType(PlayerType.Allrounder);
		
		return gd;
	}
	
	private Match createMatch() 
	{
		Match m = new Match();
		m.setPressure(50);
		m.setMomentum(67);
		
		Stadium st = new Stadium();
		st.setName("Ferozshah Kotla");
		st.setMatches(FXCollections.observableArrayList());
		st.setBasePace(50);
		st.setBaseSlowness(25);
		st.setBaseSpin(60);
		st.setBaseSwing(50);
		
		m.setStadium(st);
		m.getStadium().getMatches().add(m);
		
		m.setCurrentPace((int) (m.getStadium().getBasePace()*MiscUtilities.generateRandomDouble(0.95, 1.05)));
		m.setCurrentSlowness((int) (m.getStadium().getBaseSlowness()*MiscUtilities.generateRandomDouble(0.95, 1.05)));
		m.setCurrentSpin((int) (m.getStadium().getBaseSpin()*MiscUtilities.generateRandomDouble(0.95, 1.05)));
		m.setCurrentSwing((int) (m.getStadium().getBaseSwing()*MiscUtilities.generateRandomDouble(0.95, 1.05)));

		return m;
	}
	
	public BallResult nextBall(Player batsman, Player bowler, Match match)
	{		
		double Positivity=0, Agression=0, BowlerAgression=0, Settlement=0, NaturalAgression=0;
		
		Positivity = batsman.getGeneralDetails().getBattingAbility()*(double)Misc.getBatsmanBowlerPreference(batsman, bowler)/100
				+ batsman.getGeneralDetails().getBattingForm()
				- Misc.getBowlerAbilityInfluencedByConditions(bowler, match)
				- bowler.getGeneralDetails().getBowlingForm() + 200
				+ (batsman.getGeneralDetails().getCoolHead() - bowler.getGeneralDetails().getCoolHead())
				*(double)match.getPressure()/100
				+ (match.getMomentum()-50)*0.5
				+ Misc.getBatsmanBowlerHandednessCombination(batsman, bowler);
		
		Positivity = MiscUtilities.round(Positivity, 2);
		
		Agression = batsman.getGeneralDetails().getBattingAgressionLevel();
		BowlerAgression = bowler.getGeneralDetails().getBowlingAgressionLevel();
		Settlement = batsman.getGeneralDetails().getBattingSettlement();
		NaturalAgression = batsman.getGeneralDetails().getBattingNaturalAgression();
		
		if (!isLogged&&shouldLog)
		{
			MiscUtilities.log("Positivity: " + Positivity);
			MiscUtilities.log("Agression: " + Agression);
			MiscUtilities.log("Bowler Agression: " + BowlerAgression);
			MiscUtilities.log("NaturalAgression: " + NaturalAgression);
			MiscUtilities.log("Settlement: " + Settlement);
		}

		
		ball = new Ball();
		ball.setDotWeight(0.9*(250-Positivity) + 0.5*(100-Settlement) + 0.5*(100-NaturalAgression)+ 2*(100-Agression) + 0.3*(100-BowlerAgression));
		ball.setSingleWeight(0.4*((Positivity) + 0.25*(Settlement) + 0.15*(NaturalAgression)+ 0.6*(100-Agression) + 0.2*(100-BowlerAgression)));
		ball.setTwoWeight(0.05*Positivity + 0.05*Settlement + 0.05*NaturalAgression + 0.1*Agression);
		ball.setFourWeight(0.05*Positivity + 0.03*Settlement + 0.05*NaturalAgression + 0.2*Agression+ 0.03*BowlerAgression);
		ball.setSixWeight(0.02*Positivity + 0.015*Settlement + 0.05*NaturalAgression + 0.1*Agression + 0.02*BowlerAgression);
		ball.setChanceWeight(0.05*(NaturalAgression) + 0.4*Agression + 0.35*(100-Settlement) + 0.15*(200-Positivity) + 0.1*BowlerAgression);
		
		if (!isLogged&&shouldLog)
		{
			MiscUtilities.log("Dot weight: " + ball.getDotWeight());
			MiscUtilities.log("One weight: " + ball.getSingleWeight());
			MiscUtilities.log("Two weight: " + ball.getTwoWeight());
			MiscUtilities.log("Four weight: " + ball.getFourWeight());
			MiscUtilities.log("Six weight: " + ball.getSixWeight());
			MiscUtilities.log("Chance weight: " + ball.getChanceWeight());
			isLogged = true;
		}
		
		BallResult r = ranGen.getRandomBallResult(ball);
		
		ball = null;
		return r;
	}
}
