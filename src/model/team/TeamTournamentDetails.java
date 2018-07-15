package model.team;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import model.match.MatchResult;
import model.tournament.Tournament;

@Entity
public class TeamTournamentDetails
{
	private final IntegerProperty id;
	private final ObjectProperty<Tournament> tournament;
	private final ObjectProperty<Team> team;
	
	private final IntegerProperty matchesPlayed;
	private final IntegerProperty matchesWon;
	private final IntegerProperty matchesLost;
	private final IntegerProperty matchesTied;
	private final IntegerProperty points;
	private final IntegerProperty rank;
	private final DoubleProperty netRunRate;
	private final ListProperty<MatchResult> matchResults;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="Tournament_Id")
	public Tournament getTournament() {
		return tournament.get();
	}
	public void setTournament(Tournament tournament) {
		this.tournament.set(tournament);
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="teamTournamentDetails")
	public Team getTeam() {
		return team.get();
	}
	public void setTeam(Team team) {
		this.team.set(team);
	}
	
	public List<MatchResult> getMatchResults() {
		return this.matchResults.get();
	}
	public void setMatchResults(List<MatchResult> matchResults) {
		this.matchResults.set(FXCollections.observableArrayList(matchResults));
	}
	
	public int getMatchesPlayed() {
		return matchesPlayed.get();
	}
	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed.set(matchesPlayed);
	}
	public int getMatchesWon() {
		return matchesWon.get();
	}
	public void setMatchesWon(int matchesWon) {
		this.matchesWon.set(matchesWon);
	}
	public int getMatchesLost() {
		return matchesLost.get();
	}
	public void setMatchesLost(int matchesLost) {
		this.matchesLost.set(matchesLost);
	}
	public int getMatchesTied() {
		return matchesTied.get();
	}
	public void setMatchesTied(int matchesTied) {
		this.matchesTied.set(matchesTied);
	}
	public int getPoints() {
		return points.get();
	}
	public void setPoints(int points) {
		this.points.set(points);
	}
	public int getRank() {
		return rank.get();
	}
	public void setRank(int rank) {
		this.rank.set(rank);
	}
	public double getNetRunRate() {
		return netRunRate.get();
	}
	public void setNetRunRate(double netRunRate) {
		this.netRunRate.set(netRunRate);
	}
	
	public TeamTournamentDetails()
	{
		id= new SimpleIntegerProperty();
		team = new SimpleObjectProperty<Team>();
		matchesPlayed = new SimpleIntegerProperty();
		matchesWon = new SimpleIntegerProperty();
		matchesLost = new SimpleIntegerProperty();
		matchesTied = new SimpleIntegerProperty();
		points = new SimpleIntegerProperty();
		rank = new SimpleIntegerProperty();
		netRunRate = new SimpleDoubleProperty();
		tournament = new SimpleObjectProperty<Tournament>();
		matchResults = new SimpleListProperty<MatchResult>();
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	public ObjectProperty<Tournament> tournamentProperty() {
		return tournament;
	}
	public ObjectProperty<Team> teamProperty() {
		return team;
	}
	public IntegerProperty matchesPlayedProperty() {
		return matchesPlayed;
	}
	public IntegerProperty matchesWonProperty() {
		return matchesWon;
	}
	public IntegerProperty matchesLostProperty() {
		return matchesLost;
	}
	public IntegerProperty matchesTiedProperty() {
		return matchesTied;
	}
	public IntegerProperty pointsProperty() {
		return points;
	}
	public DoubleProperty netRunRateProperty() {
		return netRunRate;
	}
	public IntegerProperty rankProperty() {
		return rank;
	}
	public ListProperty<MatchResult> matchResultsProperty() {
		return matchResults;
	}
}
