package util.database;

import java.util.List;

import javafx.collections.FXCollections;
import model.match.Match;
import model.match.MatchResult;
import model.match.MatchState;
import model.match.Stadium;
import model.player.BatsmanType;
import model.player.Country;
import model.player.Handedness;
import model.player.Player;
import model.player.PlayerType;
import model.player.bowling.BowlerType;
import model.player.details.CareerDetails;
import model.player.details.GeneralDetails;
import model.player.details.PlayerCurrentTeamCareerDetails;
import model.player.details.TournamentDetails;
import model.team.Team;
import model.team.TeamMatchDetails;
import model.team.TeamRecords;
import model.team.TeamTournamentDetails;
import model.tournament.Tournament;
import util.MiscUtilities;

public class TemporaryHelper
{
	public static Tournament createRandomData()
	{				
		
		//To be completed - TeamRecords
		
		Tournament to = new Tournament();
		to.setSeason(8);
		to.setTeams(FXCollections.observableArrayList());
		to.setMatches(FXCollections.observableArrayList());
		to.setRetiredPlayers(FXCollections.observableArrayList());
		to.setUnsoldPlayers(FXCollections.observableArrayList());

		List<Stadium> stl = FXCollections.observableArrayList();
		
		//Create Teams and stadiums
		for(int i=0;i<8;i++)
		{
			Team t = new Team();
			t.setFullName(MiscUtilities.generateRandomString());
			t.setShortName(MiscUtilities.generateRandomString());
			t.setCurrentSquad(FXCollections.observableArrayList());
			t.setCurrentPlayingEleven(FXCollections.observableArrayList());
			t.setMatches(FXCollections.observableArrayList());
			t.setCurrentTournament(to);

			t.setTeamTournamentDetails(new TeamTournamentDetails());
			t.getTeamTournamentDetails().setTeam(t);
			t.getTeamTournamentDetails().setTournament(to);
			t.getTeamTournamentDetails().setMatchesLost(0);
			t.getTeamTournamentDetails().setMatchesPlayed(0);
			t.getTeamTournamentDetails().setMatchesTied(0);
			t.getTeamTournamentDetails().setMatchesWon(0);
			t.getTeamTournamentDetails().setNetRunRate(0);
			t.getTeamTournamentDetails().setPoints(0);
			t.getTeamTournamentDetails().setRank(1);
			
			List<MatchResult> mr = FXCollections.observableArrayList();
			for(int j = 0; j<3; j++)
			{
				if(MiscUtilities.generateRandomInt()>50)
				{
					mr.add(MatchResult.Won);
				}
				else
				{
					mr.add(MatchResult.Lost);
				}
			}
			
			t.getTeamTournamentDetails().setMatchResults(mr);
			
			TeamRecords tr = new TeamRecords();
			tr.setTeam(t);
			t.setTeamRecords(tr);
			
			Stadium st = new Stadium();
			st.setName(MiscUtilities.generateRandomString());
			st.setMatches(FXCollections.observableArrayList());
			st.setHomeTeam(t);
			st.setBasePace(MiscUtilities.generateRandomInt());
			st.setBaseSlowness(MiscUtilities.generateRandomInt());
			st.setBaseSpin(MiscUtilities.generateRandomInt());
			st.setBaseSwing(MiscUtilities.generateRandomInt());
			t.setHomeStadium(st);
			
			to.getTeams().add(t);
			
			stl.add(st);
		}
		
		//create 56 matches, set two teams to each match, and set match to both teams, also set stadium
		for(int i=0; i<56;i++)
		{
			Match m = new Match();
			m.setMatchState(MatchState.Fixture);
			m.setTournament(to);
			m.setTeams(FXCollections.observableArrayList());
			m.setTeamsMatchDetails(FXCollections.observableArrayList());
			
			m.getTeams().add(to.getTeams().get(MiscUtilities.generateRandomInt(0, 7)));
			m.getTeams().add(to.getTeams().get(MiscUtilities.generateRandomInt(0, 7)));
			while(m.getTeams().get(0)==m.getTeams().get(1))
			{
				m.getTeams().remove(1);
				m.getTeams().add(to.getTeams().get(MiscUtilities.generateRandomInt(0, 7)));
			}
			
			for(int j=0; j<2;j++)
			{
				m.getTeams().get(j).getMatches().add(m);
				m.getTeamsMatchDetails().add(new TeamMatchDetails());
				m.getTeamsMatchDetails().get(j).setMatch(m);
				m.getTeamsMatchDetails().get(j).setTeam(m.getTeams().get(j));
				m.getTeamsMatchDetails().get(j).setBatting(false);
				m.getTeamsMatchDetails().get(j).setBowling(false);
			}
			
			m.setStadium(m.getTeams().get(0).getHomeStadium());
			m.getStadium().getMatches().add(m);
			
			m.setVictoryMarginWickets(0);
			m.setVictoryMarginRuns(0);
			m.setVictoryMarginBallsRemaining(0);
			m.setCurrentPace((int) (m.getStadium().getBasePace()*MiscUtilities.generateRandomDouble(0.75, 1.25)));
			m.setCurrentSlowness((int) (m.getStadium().getBaseSlowness()*MiscUtilities.generateRandomDouble(0.75, 1.25)));
			m.setCurrentSpin((int) (m.getStadium().getBaseSpin()*MiscUtilities.generateRandomDouble(0.75, 1.25)));
			m.setCurrentSwing((int) (m.getStadium().getBaseSwing()*MiscUtilities.generateRandomDouble(0.75, 1.25)));

			to.getMatches().add(m);
		}
		
		//Create 20 players for each team and set playing elevens and whole squads
		for (int i = 0; i < to.getTeams().size(); i++)
		{
			for (int j = 0; j < 20; j++)
			{
				Player p = new Player();
				CareerDetails cd = createRandomCareerDetails();
				TournamentDetails td = createRandomTournamentDetails();
				GeneralDetails gd = createRandomGeneralDetails();
				PlayerCurrentTeamCareerDetails pctcd = createRandomPlayerCurrentTeamCareerDetails();
				p.setFirstName(MiscUtilities.generateRandomString());
				p.setLastName(MiscUtilities.generateRandomString());
				
				cd.setPlayer(p);
				p.setCareerDetails(cd);
				
				td.setPlayer(p);
				td.setMatchDetailsList(FXCollections.observableArrayList());
				td.setTournament(to);
				p.setTournamentDetails(td);
				
				gd.setPlayer(p);
				p.setGeneralDetails(gd);
				
				pctcd.setPlayer(p);
				p.setCurrentTeamCareerDetails(pctcd);
				
				to.getTeams().get(i).getCurrentSquad().add(p);
				p.setCurrentTeam(to.getTeams().get(i));
			}
			for(int k=0; k<11;k++)
			{
				to.getTeams().get(i).getCurrentPlayingEleven().add(
						to.getTeams().get(i).getCurrentSquad().get(k));
			}
		}
		
		
		//create unsold players
		for(int i =0; i<50; i++)
		{
			Player p = new Player();
			CareerDetails cd = createRandomCareerDetails();
			GeneralDetails gd = createRandomGeneralDetails();
			p.setFirstName(MiscUtilities.generateRandomString());
			p.setLastName(MiscUtilities.generateRandomString());
			
			cd.setPlayer(p);
			p.setCareerDetails(cd);
						
			gd.setPlayer(p);
			p.setGeneralDetails(gd);
			
			p.setCurrentTeamCareerDetails(null);
			p.setCurrentTeam(null);
			p.setTournamentDetails(null);
			
			to.getUnsoldPlayers().add(p);
		}
		//create retired players
		
		for(int i =0; i<60; i++)
		{
			Player p = new Player();
			CareerDetails cd = createRandomCareerDetails();
			GeneralDetails gd = createRandomGeneralDetails();
			p.setFirstName(MiscUtilities.generateRandomString());
			p.setLastName(MiscUtilities.generateRandomString());
			
			cd.setPlayer(p);
			p.setCareerDetails(cd);
						
			gd.setPlayer(p);
			p.setGeneralDetails(gd);
			
			p.setCurrentTeamCareerDetails(null);
			p.setCurrentTeam(null);
			p.setTournamentDetails(null);
			
			to.getRetiredPlayers().add(p);
		}
		
		//Set the properties of teamrecords
		for(int i =0; i<to.getTeams().size();i++)
		{
			to.getTeams().get(i).getTeamRecords().setBestBowlingFigure("24-0-18-2");
			to.getTeams().get(i).getTeamRecords().setHighestIndividualScore(MiscUtilities.generateRandomInt(80, 150));
			to.getTeams().get(i).getTeamRecords().setHighestTeamScoreConceded(MiscUtilities.generateRandomInt(200, 250));
			to.getTeams().get(i).getTeamRecords().setHighestTeamScoreConcededAgainst(to.getTeams().get(MiscUtilities.generateRandomInt(0, 7)));
			to.getTeams().get(i).getTeamRecords().setHighestTeamScoreScored(MiscUtilities.generateRandomInt(200, 250));
			to.getTeams().get(i).getTeamRecords().setHighestTeamScoreScoredAgainst(to.getTeams().get(MiscUtilities.generateRandomInt(0, 7)));
			to.getTeams().get(i).getTeamRecords().setLowestTeamScoreConceded(MiscUtilities.generateRandomInt(50, 100));
			to.getTeams().get(i).getTeamRecords().setLowestTeamScoreConcededAgainst(to.getTeams().get(MiscUtilities.generateRandomInt(0, 7)));
			to.getTeams().get(i).getTeamRecords().setLowestTeamScoreScored(MiscUtilities.generateRandomInt(50, 100));
			to.getTeams().get(i).getTeamRecords().setLowestTeamScoreScoredAgainst(to.getTeams().get(MiscUtilities.generateRandomInt(0, 7)));
			to.getTeams().get(i).getTeamRecords().setMostMomsForTeam(MiscUtilities.generateRandomInt(4, 8));
			to.getTeams().get(i).getTeamRecords().setMostMomsForTeamPlayer(to.getUnsoldPlayers().get(MiscUtilities.generateRandomInt(0, 40)));
			to.getTeams().get(i).getTeamRecords().setMostRunsScoredForTeam(MiscUtilities.generateRandomInt(1000, 2000));
			to.getTeams().get(i).getTeamRecords().setMostMomsForTeamPlayer(to.getRetiredPlayers().get(MiscUtilities.generateRandomInt(0, 49)));
			to.getTeams().get(i).getTeamRecords().setTotalMatchesLost(MiscUtilities.generateRandomInt());
			to.getTeams().get(i).getTeamRecords().setTotalMatchesTied(MiscUtilities.generateRandomInt(0, 3));
			to.getTeams().get(i).getTeamRecords().setTotalMatchesWon(MiscUtilities.generateRandomInt());
			to.getTeams().get(i).getTeamRecords().setTotalMatchesPlayed(MiscUtilities.generateRandomInt(100, 200));
		}
		
		//Set the names of the teams
		to.getTeams().get(0).setFullName("Delhi Daredevils");
		to.getTeams().get(1).setFullName("Chennai Super Kings");
		to.getTeams().get(2).setFullName("Kolkata Knight Riders");
		to.getTeams().get(3).setFullName("Kings XI Punjab");
		to.getTeams().get(4).setFullName("Royal Challengers Bangalore");
		to.getTeams().get(5).setFullName("Rajasthan Royals");
		to.getTeams().get(6).setFullName("Mumbai Indians");
		to.getTeams().get(7).setFullName("Sunrisers Hyderabad");
		
		to.getTeams().get(0).setShortName("DD");
		to.getTeams().get(1).setShortName("CSK");
		to.getTeams().get(2).setShortName("KKR");
		to.getTeams().get(3).setShortName("KXIP");
		to.getTeams().get(4).setShortName("RCB");
		to.getTeams().get(5).setShortName("RR");
		to.getTeams().get(6).setShortName("MI");
		to.getTeams().get(7).setShortName("SRH");
		
		return to;
	}
	
	private static CareerDetails createRandomCareerDetails()
	{
		CareerDetails cd= new CareerDetails();

		cd.setBattingBalls(MiscUtilities.generateRandomInt(50, 3000));
		cd.setBattingCenturies(0);
		cd.setBattingFifties(MiscUtilities.generateRandomInt(0, 18));
		cd.setBattingFours(MiscUtilities.generateRandomInt(2, 250));
		cd.setBattingHighestScore(MiscUtilities.generateRandomInt(30, 110));
		cd.setBattingNotOuts(MiscUtilities.generateRandomInt(0, 10));
		cd.setBattingOuts(MiscUtilities.generateRandomInt(5, 50));
		cd.setBattingRuns(MiscUtilities.generateRandomInt(100, 3500));
		cd.setBattingSixes(MiscUtilities.generateRandomInt(1, 100));
		cd.setBattingThirties(MiscUtilities.generateRandomInt(1, 30));
		cd.setBestBowlingFigure("4.0-0-19-1");
		cd.setBowlingBalls(MiscUtilities.generateRandomInt(50, 2000));
		cd.setBowlingFiveWicketHauls(0);
		cd.setBowlingFours(MiscUtilities.generateRandomInt(5, 150));
		cd.setBowlingFourWicketHauls(MiscUtilities.generateRandomInt(0, 4));
		cd.setBowlingMaidens(MiscUtilities.generateRandomInt(0, 3));
		cd.setBowlingRuns(MiscUtilities.generateRandomInt(50, 2000));
		cd.setBowlingSixes(MiscUtilities.generateRandomInt(5, 45));
		cd.setBowlingWickets(MiscUtilities.generateRandomInt(1, 100));
		cd.setMatchesPlayed(MiscUtilities.generateRandomInt());
		cd.setMomAwards(MiscUtilities.generateRandomInt(0, 6));
		
		return cd;
	}
	private static TournamentDetails createRandomTournamentDetails()
	{
		TournamentDetails td = new TournamentDetails();
		return td;
	}
	
	private static GeneralDetails createRandomGeneralDetails()
	{
		GeneralDetails gd = new GeneralDetails();
		gd.setAge(MiscUtilities.generateRandomInt(18, 37));
		gd.setBattingAbility(MiscUtilities.generateRandomInt());
		gd.setBattingAgressionLevel(0);
		gd.setBattingFastBowlingPreference(MiscUtilities.generateRandomInt(75, 125));
		gd.setBattingForm(MiscUtilities.generateRandomInt());
		gd.setBattingHandedness(Handedness.Right);
		gd.setBatsmanType(BatsmanType.RHB);
		if(Math.random()>0.65)gd.setBattingHandedness(Handedness.Left);
		gd.setBattingNaturalAgression(MiscUtilities.generateRandomInt());
		gd.setBattingSettlement(0);
		gd.setBattingSlowBallingPreference(MiscUtilities.generateRandomInt(75, 125));
		gd.setBowlingAbility(MiscUtilities.generateRandomInt());
		gd.setBowlingAgressionLevel(0);
		gd.setBowlingForm(MiscUtilities.generateRandomInt());
		gd.setBowlingHandedness(Handedness.Right);
		if(Math.random()>0.65)gd.setBowlingHandedness(Handedness.Left);
		gd.setCoolHead(MiscUtilities.generateRandomInt());
		gd.setBowlerType(BowlerType.RFM);
		gd.setCountry(Country.India);
		gd.setPlayerType(PlayerType.Allrounder);
		
		return gd;
	}
	private static PlayerCurrentTeamCareerDetails createRandomPlayerCurrentTeamCareerDetails()
	{
		PlayerCurrentTeamCareerDetails pctcd = new PlayerCurrentTeamCareerDetails();
		pctcd.setBattingCenturies(0);
		pctcd.setBattingFifties(MiscUtilities.generateRandomInt(0, 18));
		pctcd.setBattingHighestScore(MiscUtilities.generateRandomInt(30, 110));
		pctcd.setBattingRuns(MiscUtilities.generateRandomInt(100, 3500));
		pctcd.setBattingThirties(MiscUtilities.generateRandomInt(1, 30));
		pctcd.setBestBowlingFigure("4.0-0-21-1");
		pctcd.setBowlingFiveWicketHauls(0);
		pctcd.setBowlingFourWicketHauls(MiscUtilities.generateRandomInt(0, 4));
		pctcd.setBowlingWickets(MiscUtilities.generateRandomInt());
		pctcd.setMatchesPlayed(MiscUtilities.generateRandomInt());
		pctcd.setMomAwards(MiscUtilities.generateRandomInt(0, 6));
		return pctcd;
	}
}