package engine.util;

import java.util.List;

import util.MiscUtilities;
import model.match.Match;
import model.match.MatchState;
import model.player.Handedness;
import model.player.Player;
import model.player.bowling.BowlerType;
import model.player.details.MatchDetails;
import model.team.Team;

public class Misc
{
	public static Match findActiveMatch(List<Match> matches)
	{
		for(int i=0;i<matches.size();i++)
		{
			MatchState m = matches.get(i).getMatchState();
			
			if(m==MatchState.Fixture||m==MatchState.InningsBreak||m==MatchState.InningsOneInProgress||m==MatchState.InningsTwoInProgress)
			{
				return matches.get(i);
			}
		}
		return null;
	}
	
	public static int findUserTeamIndex(Match m, Team t)
	{
		if(m.getTeams().get(1)==t) return 1;
		else {return 0;}
	}
	
	public static int getBatsmanBowlerPreference(Player batsman, Player bowler)
	{
		int i =100;
		if(bowler.getGeneralDetails().getBowlerType().getCategory().equals("F"))
		{
			i = batsman.getGeneralDetails().getBattingFastBowlingPreference();
		}
		else if(bowler.getGeneralDetails().getBowlerType().getCategory().equals("S"))
		{
			i = batsman.getGeneralDetails().getBattingSlowBallingPreference();
		}
		return i;
	}
	
	public static int getBatsmanBowlerHandednessCombination(Player batsman, Player bowler) {
		
		Handedness bat = batsman.getGeneralDetails().getBattingHandedness();
		Handedness ball = bowler.getGeneralDetails().getBowlingHandedness();
		
		if(bat==ball) {
			return 10;
		}
		else if(bat!=ball) {
			return -10;
		}
		return 0;
	}
	
	public static double getBowlerAbilityInfluencedByConditions(Player bowler, Match match)
	{
		BowlerType t = bowler.getGeneralDetails().getBowlerType();
		int ability = bowler.getGeneralDetails().getBowlingAbility();
		
		if(t==BowlerType.LF||t==BowlerType.RF)
		{
			double pace=match.getCurrentPace(), swing=match.getCurrentSwing();
			double abp, absw;
			abp= ability*pace/100;
			absw=ability*swing/100;
			return 0.7*abp+0.3*absw;
		}
		else if(t==BowlerType.LFM||t==BowlerType.RFM)
		{
			double pace=match.getCurrentPace(), swing=match.getCurrentSwing();
			double abp, absw;
			abp= ability*pace/100;
			absw=ability*swing/100;
			return 0.3*abp+0.7*absw;
		}
		else if(t==BowlerType.RM||t==BowlerType.LM)
		{
			double slow=match.getCurrentSlowness(), swing=match.getCurrentSwing();
			double abs, absw;
			abs= ability*slow/100;
			absw=ability*swing/100;
			return 0.7*abs+0.3*absw;
		}
		else if(t==BowlerType.LS||t==BowlerType.OS||t==BowlerType.SLA)
		{
			double spin=match.getCurrentSpin();
			return ability*spin/100;
		}
		
		return ability;
	}
	
	public static String getOversFromBalls(int balls)
	{
		StringBuilder overs = new StringBuilder();
		overs.append((int)(balls/6));
		overs.append(".");
		overs.append(balls%6);
		return overs.toString();
	}
	
	public static int calculateBowlingPoints(MatchDetails md) {
		int bowlingPoints;
		bowlingPoints = md.getBowlingDots()*2 + md.getBowlingWickets()*25 + ((int)(md.getBowlingBalls()*1.5) - md.getBowlingRuns())*2;
		
		if(md.getBowlingWickets()>1) {
			bowlingPoints += 10*(md.getBowlingWickets()-1);
		}
		if(md.getBowlingRuns()>md.getBowlingBalls()*1.5) {
			bowlingPoints -= ((int)(md.getBowlingBalls()*1.5) - md.getBowlingRuns())*2;
			bowlingPoints += (int)(md.getBowlingBalls()*1.5)-md.getBowlingRuns();
		}
		if(md.isMom()) {
			bowlingPoints += 20;
		}
		return bowlingPoints;
	}
	
	public static int calculateBattingPoints(MatchDetails md)
	{
		int battingPoints;
		battingPoints = md.getBattingRuns() + (int)(1.5*(md.getBattingRuns() - md.getBattingBalls())) + md.getBattingFours() + md.getBattingSixes()*2;
		
		if(md.isMom()) {
			battingPoints += 20;
		}
		
		if(md.getBattingRuns()>25) {
			battingPoints += 10;
		}
		if(md.getBattingRuns()>50) {
			battingPoints += 15;
		}
		if(md.getBattingRuns()>75) {
			battingPoints += 10;
		}
		if(md.getBattingRuns()>100) {
			battingPoints += 15;
		}
		
		return battingPoints;
	}
	
	public static String generateBattingStatus(String bowler, String fielder, String wk) {
		if(MiscUtilities.generateRandomInt()>75)
		{
			return "b "+bowler; 
		}
		else if(MiscUtilities.generateRandomInt()>40)
		{
			if(fielder.equals(bowler))
			{
				return "c & b "+bowler;
			}
			return "c " + fielder + " b "+bowler; 
		}
		else if(MiscUtilities.generateRandomInt()>20)
		{
			return "lbw b "+bowler;
		}
		else if(MiscUtilities.generateRandomInt()>5)
		{
			return "c " + wk + " b " + bowler;
		}
		else
		{
			return "run out " + fielder;
		}
	}
	
	public static String generateBowlingFigure(String overs, int dots, int runs, int wickets)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(overs + "-");
		sb.append(dots+ "-"+runs+"-"+wickets);
		return sb.toString();
	}
	
	public static String getFOWText(int runs, int wickets, String overs, Player batsman)
	{
		return new StringBuilder(wickets + "-" + runs + "  " + batsman.getLastName() + " in: " + overs + " overs").toString();
	}

	public static double calculateRequiredRunRate(int target, int currentScore, int ballsPlayed)
	{
		return MiscUtilities.round(((double)target-currentScore)*6/(120-ballsPlayed), 2);
	}
}
