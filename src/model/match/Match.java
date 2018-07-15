package model.match;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import model.player.Player;
import model.team.Team;
import model.team.TeamMatchDetails;
import model.tournament.Tournament;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Matches")
public class Match
{
	private final IntegerProperty id;
	private final ObjectProperty<Tournament> tournament;
	private final ListProperty<Team> teams;
	private final ObjectProperty<MatchState> matchState;
	private final ObjectProperty<Stadium> stadium;
	
	private final IntegerProperty pressure;
	private final IntegerProperty momentum;
	private final IntegerProperty currentSwing;
	private final IntegerProperty currentPace;
	private final IntegerProperty currentSlowness;
	private final IntegerProperty currentSpin;
	
	private final ListProperty<TeamMatchDetails> teamsMatchDetails;
 	private final IntegerProperty victoryMarginRuns;
 	private final IntegerProperty victoryMarginWickets;
 	private final IntegerProperty victoryMarginBallsRemaining;
 	private final ObjectProperty<Player> manOfTheMatch;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="MatchIDGenerator")
	@TableGenerator(name="MatchIDGenerator", allocationSize=1, pkColumnName="PrimaryKey", pkColumnValue="MatchID")
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="Tournament_Id", insertable=true, nullable=true, updatable=true)
	public Tournament getTournament() {
		return tournament.get();
	}
	public void setTournament(Tournament tournament) {
		this.tournament.set(tournament);
	}
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="Match_Team_ManyToMany_Table", joinColumns=@JoinColumn(name="Match_Id"),
		inverseJoinColumns=@JoinColumn(name="Team_id"))
	@Fetch(FetchMode.SUBSELECT)
	public List<Team> getTeams() {
		return teams.get();
	}
	public void setTeams(List<Team> teams) {
		this.teams.set(FXCollections.observableArrayList(teams));
	}
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="Stadium_Id")
	public Stadium getStadium()	{
		return this.stadium.get();
	}
	public void setStadium(Stadium stadium) {
		this.stadium.set(stadium);
	}
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="match")
	@Fetch(FetchMode.SUBSELECT)
	public List<TeamMatchDetails> getTeamsMatchDetails() {
		return teamsMatchDetails.get();
	}
	public void setTeamsMatchDetails(List<TeamMatchDetails> teamsMatchDetails) {
		this.teamsMatchDetails.set(FXCollections.observableArrayList(teamsMatchDetails));
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="ManOfTheMatchPlayer_Id")
	public Player getManOfTheMatch() {
		return manOfTheMatch.get();
	}
	public void setManOfTheMatch(Player manOfTheMatch) {
		this.manOfTheMatch.set(manOfTheMatch);
	}
	
	@Enumerated(EnumType.STRING)
	public MatchState getMatchState() {
		return this.matchState.get();
	}
	public void setMatchState(MatchState matchState) {
		this.matchState.set(matchState);
	}
	
	public int getVictoryMarginRuns() {
		return victoryMarginRuns.get();
	}
	public void setVictoryMarginRuns(int victoryMarginRuns) {
		this.victoryMarginRuns.set(victoryMarginRuns);
	}
	public int getVictoryMarginWickets() {
		return victoryMarginWickets.get();
	}
	public void setVictoryMarginWickets(int victoryMarginWickets) {
		this.victoryMarginWickets.set(victoryMarginWickets);
	}
	public int getVictoryMarginBallsRemaining() {
		return victoryMarginBallsRemaining.get();
	}
	public void setVictoryMarginBallsRemaining(int victoryMarginBallsRemaining) {
		this.victoryMarginBallsRemaining.set(victoryMarginBallsRemaining);
	}	
	public int getPressure() {
		return pressure.get();
	}
	public void setPressure(int pressure) {
		this.pressure.set(pressure);
	}
	public int getMomentum() {
		return momentum.get();
	}
	public void setMomentum(int momentum) {
		this.momentum.set(momentum);
	}
	
	public int getCurrentSwing() {
		return currentSwing.get();
	}
	public void setCurrentSwing(int swing) {
		this.currentSwing.set(swing);
	}
	public int getCurrentPace() {
		return currentPace.get();
	}
	public void setCurrentPace(int pace) {
		this.currentPace.set(pace);
	}
	public int getCurrentSlowness() {
		return currentSlowness.get();
	}
	public void setCurrentSlowness(int slowness) {
		this.currentSlowness.set(slowness);
	}
	public int getCurrentSpin() {
		return currentSpin.get();
	}
	public void setCurrentSpin(int spin) {
		this.currentSpin.set(spin);
	}
	
	public Match()
	{
		id=new SimpleIntegerProperty();
		tournament = new SimpleObjectProperty<Tournament>();
		teams= new SimpleListProperty<Team>();
		matchState = new SimpleObjectProperty<MatchState>();
		stadium = new SimpleObjectProperty<Stadium>();
		pressure= new SimpleIntegerProperty();
		momentum = new SimpleIntegerProperty();
		currentSwing = new SimpleIntegerProperty();
		currentSlowness = new SimpleIntegerProperty();
		currentPace = new SimpleIntegerProperty();
		currentSpin = new SimpleIntegerProperty();
		teamsMatchDetails = new SimpleListProperty<TeamMatchDetails>();
		manOfTheMatch = new SimpleObjectProperty<Player>();
		victoryMarginBallsRemaining = new SimpleIntegerProperty();
		victoryMarginRuns = new SimpleIntegerProperty();
		victoryMarginWickets = new SimpleIntegerProperty();
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	public ObjectProperty<Tournament> tournamentProperty() {
		return tournament;
	}
	public ListProperty<Team> teamsProperty() {
		return teams;
	}
	public ObjectProperty<MatchState> matchStateProperty() {
		return matchState;
	}
	public ObjectProperty<Stadium> stadiumProperty() {
		return stadium;
	}
	public IntegerProperty pressureProperty() {
		return pressure;
	}
	public IntegerProperty momentumProperty() {
		return momentum;
	}
	public IntegerProperty currentSwingProperty() {
		return currentSwing;
	}
	public IntegerProperty currentPaceProperty() {
		return currentPace;
	}
	public IntegerProperty currentSlownessProperty() {
		return currentSlowness;
	}
	public IntegerProperty currentSpinProperty() {
		return currentSpin;
	}
	public ObjectProperty<Player> manOfTheMatchProperty() {
		return manOfTheMatch;
	}
	public IntegerProperty victoryMarginBallsRemainingProperty() {
		return victoryMarginBallsRemaining;
	}
	public IntegerProperty victoryMarginWicketsProperty() {
		return victoryMarginWickets;
	}
	public IntegerProperty victoryMarginRunsProperty() {
		return victoryMarginRuns;
	}
	public ListProperty<TeamMatchDetails> teamsMatchDetailsProperty() {
		return teamsMatchDetails;
	}
}
