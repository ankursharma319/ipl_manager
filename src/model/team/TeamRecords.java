package model.team;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import model.player.Player;

@Entity
public class TeamRecords
{
	private final IntegerProperty id;
	private final ObjectProperty<Team> team;
	
	private final IntegerProperty highestTeamScoreScored;
	private final IntegerProperty lowestTeamScoreScored;
	private final IntegerProperty highestTeamScoreConceded;
	private final IntegerProperty lowestTeamScoreConceded;
	
	private final ObjectProperty<Team> highestTeamScoreScoredAgainst;
	private final ObjectProperty<Team> lowestTeamScoreScoredAgainst;
	private final ObjectProperty<Team> highestTeamScoreConcededAgainst;
	private final ObjectProperty<Team> lowestTeamScoreConcededAgainst;
	private final IntegerProperty highestIndividualScore;
	private final StringProperty bestBowlingFigure;
	private final IntegerProperty mostRunsScoredForTeam;
	private final ObjectProperty<Player> mostRunsScoredForTeamPlayer;
	private final IntegerProperty mostWicketsTakenForTeam;
	private final ObjectProperty<Player> mostWicketsTakenForTeamPlayer;
	private final IntegerProperty mostMomsForTeam;
	private final ObjectProperty<Player> mostMomsForTeamPlayer;
	
	private final IntegerProperty mostRunsScoredForTeamInSeason;
	private final ObjectProperty<Player> mostRunsScoredForTeamInSeasonPlayer;
	private final IntegerProperty mostWicketsTakenForTeamInSeason;
	private final ObjectProperty<Player> mostWicketsTakenForTeamInSeasonPlayer;
	
	private final IntegerProperty totalMatchesPlayed;
	private final IntegerProperty totalMatchesWon;
	private final IntegerProperty totalMatchesLost;
	private final IntegerProperty totalMatchesTied;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="teamRecords")
	public Team getTeam() {
		return team.get();
	}
	public void setTeam(Team team) {
		this.team.set(team);
	}
	
	public int getHighestTeamScoreScored() {
		return highestTeamScoreScored.get();
	}
	public void setHighestTeamScoreScored(int highestScoreScored) {
		this.highestTeamScoreScored .set(highestScoreScored);
	}
	public int getLowestTeamScoreScored() {
		return lowestTeamScoreScored.get();
	}
	public void setLowestTeamScoreScored(int lowestScoreScored) {
		this.lowestTeamScoreScored.set(lowestScoreScored);
	}
	public int getHighestTeamScoreConceded() {
		return highestTeamScoreConceded.get();
	}
	public void setHighestTeamScoreConceded(int highestScoreConceded) {
		this.highestTeamScoreConceded.set(highestScoreConceded);
	}
	public int getLowestTeamScoreConceded() {
		return lowestTeamScoreConceded.get();
	}
	public void setLowestTeamScoreConceded(int lowestScoreConceded) {
		this.lowestTeamScoreConceded.set(lowestScoreConceded);
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="HighestTeamScoreScoredAgainst_TeamId")
	public Team getHighestTeamScoreScoredAgainst() {
		return highestTeamScoreScoredAgainst.get();
	}
	public void setHighestTeamScoreScoredAgainst(Team highestTeamScoreScoredAgainst) {
		this.highestTeamScoreScoredAgainst.set(highestTeamScoreScoredAgainst);
	}
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="LowestTeamScoreScoredAgainst_TeamId")
	public Team getLowestTeamScoreScoredAgainst() {
		return lowestTeamScoreScoredAgainst.get();
	}
	public void setLowestTeamScoreScoredAgainst(Team lowestTeamScoreScoredAgainst) {
		this.lowestTeamScoreScoredAgainst.set(lowestTeamScoreScoredAgainst);
	}
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="HighestTeamScoreConcededAgainst_TeamId")
	public Team getHighestTeamScoreConcededAgainst() {
		return highestTeamScoreConcededAgainst.get();
	}
	public void setHighestTeamScoreConcededAgainst(
			Team highestTeamScoreConcededAgainst) {
		this.highestTeamScoreConcededAgainst.set(highestTeamScoreConcededAgainst);
	}
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="LowestTeamScoreConcededAgainst_TeamId")
	public Team getLowestTeamScoreConcededAgainst() {
		return lowestTeamScoreConcededAgainst.get();
	}
	public void setLowestTeamScoreConcededAgainst(
			Team lowestTeamScoreConcededAgainst) {
		this.lowestTeamScoreConcededAgainst.set(lowestTeamScoreConcededAgainst);
	}
	public int getHighestIndividualScore() {
		return highestIndividualScore.get();
	}
	public void setHighestIndividualScore(int highestIndividualScore) {
		this.highestIndividualScore.set(highestIndividualScore);
	}
	
	public String getBestBowlingFigure() {
		return bestBowlingFigure.get();
	}
	public void setBestBowlingFigure(String bestBowlingFigure) {
		this.bestBowlingFigure.set(bestBowlingFigure);
	}
	public int getMostRunsScoredForTeam() {
		return mostRunsScoredForTeam.get();
	}
	public void setMostRunsScoredForTeam(int mostRunsScoredForTeam) {
		this.mostRunsScoredForTeam.set(mostRunsScoredForTeam);
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="MostRunsScoredForTeam_PlayerId")
	public Player getMostRunsScoredForTeamPlayer() {
		return mostRunsScoredForTeamPlayer.get();
	}
	public void setMostRunsScoredForTeamPlayer(Player mostRunsScoredForTeamPlayer) {
		this.mostRunsScoredForTeamPlayer.set(mostRunsScoredForTeamPlayer);
	}
	public int getMostWicketsTakenForTeam() {
		return mostWicketsTakenForTeam.get();
	}
	public void setMostWicketsTakenForTeam(int mostWicketsTakenForTeam) {
		this.mostWicketsTakenForTeam.set(mostWicketsTakenForTeam);
	}
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="MostWicketsTakenForTeam_PlayerId")
	public Player getMostWicketsTakenForTeamPlayer() {
		return mostWicketsTakenForTeamPlayer.get();
	}
	public void setMostWicketsTakenForTeamPlayer(
			Player mostWicketsTakenForTeamPlayer) {
		this.mostWicketsTakenForTeamPlayer.set(mostWicketsTakenForTeamPlayer);
	}
	public int getMostMomsForTeam() {
		return mostMomsForTeam.get();
	}
	public void setMostMomsForTeam(int mostMomsForTeam) {
		this.mostMomsForTeam.set(mostMomsForTeam);
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="Player_Id")
	public Player getMostMomsForTeamPlayer() {
		return mostMomsForTeamPlayer.get();
	}
	public void setMostMomsForTeamPlayer(Player mostMomsForTeam) {
		this.mostMomsForTeamPlayer.set(mostMomsForTeam);
	}
	public int getMostRunsScoredForTeamInSeason() {
		return mostRunsScoredForTeamInSeason.get();
	}
	public void setMostRunsScoredForTeamInSeason(int mostRunsScoredForTeamInSeason) {
		this.mostRunsScoredForTeamInSeason.set(mostRunsScoredForTeamInSeason);
	}
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="MostRunsScoredForTeamInSeason_PlayerId")
	public Player getMostRunsScoredForTeamInSeasonPlayer() {
		return mostRunsScoredForTeamInSeasonPlayer.get();
	}
	public void setMostRunsScoredForTeamInSeasonPlayer(
			Player mostRunsScoredForTeamInSeasonPlayer) {
		this.mostRunsScoredForTeamInSeasonPlayer.set(mostRunsScoredForTeamInSeasonPlayer);
	}
	public int getMostWicketsTakenForTeamInSeason() {
		return mostWicketsTakenForTeamInSeason.get();
	}
	public void setMostWicketsTakenForTeamInSeason(
			int mostWicketsTakenForTeamInSeason) {
		this.mostWicketsTakenForTeamInSeason.set(mostWicketsTakenForTeamInSeason);
	}
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="MostWicketsTakenForTeamInSeason_PlayerId")
	public Player getMostWicketsTakenForTeamInSeasonPlayer() {
		return mostWicketsTakenForTeamInSeasonPlayer.get();
	}
	public void setMostWicketsTakenForTeamInSeasonPlayer(
			Player mostWicketsTakenForTeamInSeasonPlayer) {
		this.mostWicketsTakenForTeamInSeasonPlayer.set(mostWicketsTakenForTeamInSeasonPlayer);
	}
	public int getTotalMatchesPlayed() {
		return totalMatchesPlayed.get();
	}
	public void setTotalMatchesPlayed(int totalMatchesPlayed) {
		this.totalMatchesPlayed.set(totalMatchesPlayed);
	}
	public int getTotalMatchesWon() {
		return totalMatchesWon.get();
	}
	public void setTotalMatchesWon(int totalMatchesWon) {
		this.totalMatchesWon.set(totalMatchesWon);
	}
	public int getTotalMatchesLost() {
		return totalMatchesLost.get();
	}
	public void setTotalMatchesLost(int totalMatchesLost) {
		this.totalMatchesLost.set(totalMatchesLost);
	}
	public int getTotalMatchesTied() {
		return totalMatchesTied.get();
	}
	public void setTotalMatchesTied(int totalMatchesTied) {
		this.totalMatchesTied.set(totalMatchesTied);
	}

	public TeamRecords()
	{
		id= new SimpleIntegerProperty();
		team = new SimpleObjectProperty<Team>();
		highestTeamScoreScored= new SimpleIntegerProperty();
		lowestTeamScoreScored= new SimpleIntegerProperty();
		highestTeamScoreConceded= new SimpleIntegerProperty();
		lowestTeamScoreConceded= new SimpleIntegerProperty();
		highestTeamScoreScoredAgainst = new SimpleObjectProperty<Team>();
		lowestTeamScoreScoredAgainst = new SimpleObjectProperty<Team>();
		highestTeamScoreConcededAgainst = new SimpleObjectProperty<Team>();
		lowestTeamScoreConcededAgainst = new SimpleObjectProperty<Team>();
		highestIndividualScore= new SimpleIntegerProperty();
		bestBowlingFigure = new SimpleStringProperty();
		mostRunsScoredForTeam= new SimpleIntegerProperty();
		mostRunsScoredForTeamPlayer = new SimpleObjectProperty<Player>();
		mostWicketsTakenForTeam= new SimpleIntegerProperty();
		mostWicketsTakenForTeamPlayer = new SimpleObjectProperty<Player>();
		mostMomsForTeam = new SimpleIntegerProperty();
		mostMomsForTeamPlayer = new SimpleObjectProperty<Player>();
		mostRunsScoredForTeamInSeason= new SimpleIntegerProperty();
		mostRunsScoredForTeamInSeasonPlayer = new SimpleObjectProperty<Player>();
		mostWicketsTakenForTeamInSeason= new SimpleIntegerProperty();
		mostWicketsTakenForTeamInSeasonPlayer = new SimpleObjectProperty<Player>();		
		totalMatchesPlayed= new SimpleIntegerProperty();
		totalMatchesWon= new SimpleIntegerProperty();
		totalMatchesLost= new SimpleIntegerProperty();
		totalMatchesTied= new SimpleIntegerProperty();
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	public ObjectProperty<Team> teamProperty() {
		return team;
	}
	public IntegerProperty highestTeamScoreScoredProperty() {
		return highestTeamScoreScored;
	}
	public IntegerProperty lowestTeamScoreScoredProperty() {
		return lowestTeamScoreScored;
	}
	public IntegerProperty highestTeamScoreConcededProperty() {
		return highestTeamScoreConceded;
	}
	public IntegerProperty lowestTeamScoreConcededProperty() {
		return lowestTeamScoreConceded;
	}
	public ObjectProperty<Team> highestTeamScoreScoredAgainstProperty() {
		return highestTeamScoreScoredAgainst;
	}
	public ObjectProperty<Team> lowestTeamScoreScoredAgainstProperty() {
		return lowestTeamScoreScoredAgainst;
	}
	public ObjectProperty<Team> highestTeamScoreConcededAgainstProperty() {
		return highestTeamScoreConcededAgainst;
	}
	public ObjectProperty<Team> lowestTeamScoreConcededAgainstProperty() {
		return lowestTeamScoreConcededAgainst;
	}
	public IntegerProperty highestIndividualScoreProperty() {
		return highestIndividualScore;
	}
	public StringProperty bestBowlingFigureProperty() {
		return bestBowlingFigure;
	}
	public IntegerProperty mostRunsScoredForTeamProperty() {
		return mostRunsScoredForTeam;
	}
	public ObjectProperty<Player> mostRunsScoredForTeamPlayerProperty() {
		return mostRunsScoredForTeamPlayer;
	}
	public IntegerProperty mostWicketsTakenForTeamProperty() {
		return mostWicketsTakenForTeam;
	}
	public ObjectProperty<Player> mostWicketsTakenForTeamPlayerProperty() {
		return mostWicketsTakenForTeamPlayer;
	}
	public IntegerProperty mostMomsForTeamProperty() {
		return mostMomsForTeam;
	}
	public IntegerProperty mostRunsScoredForTeamInSeasonProperty() {
		return mostRunsScoredForTeamInSeason;
	}
	public ObjectProperty<Player> mostRunsScoredForTeamInSeasonPlayerProperty() {
		return mostRunsScoredForTeamInSeasonPlayer;
	}
	public IntegerProperty mostWicketsTakenForTeamInSeasonProperty() {
		return mostWicketsTakenForTeamInSeason;
	}
	public ObjectProperty<Player> mostWicketsTakenForTeamInSeasonPlayerProperty() {
		return mostWicketsTakenForTeamInSeasonPlayer;
	}
	public IntegerProperty totalMatchesPlayedProperty() {
		return totalMatchesPlayed;
	}
	public IntegerProperty totalMatchesWonProperty() {
		return totalMatchesWon;
	}
	public IntegerProperty totalMatchesLostProperty() {
		return totalMatchesLost;
	}
	public IntegerProperty totalMatchesTiedProperty() {
		return totalMatchesTied;
	}
	public ObjectProperty<Player> mostMomsForTeamPlayerProperty() {
		return mostMomsForTeamPlayer;
	}

}
