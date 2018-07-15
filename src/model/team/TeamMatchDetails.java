package model.team;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import model.match.Match;
import model.match.MatchResult;
import model.player.Player;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import engine.util.Misc;

@Entity
public class TeamMatchDetails
{
	private final IntegerProperty id;
	private final ObjectProperty<Match> match;
	private final ListProperty<Player> playingEleven;
	private final ObjectProperty<Team> team;
	private final ObjectProperty<MatchResult> matchResult;
	
	private final BooleanProperty batting;
	private final BooleanProperty bowling;
	private final IntegerProperty totalScored;
	private final IntegerProperty wicketsLost;
	private final IntegerProperty ballsPlayed;
	private final IntegerProperty overNumber;
	private final StringProperty oversPlayed;
	private final IntegerProperty extrasGained;
	private final IntegerProperty totalConceded;
	private final IntegerProperty wicketsGained;
	private final IntegerProperty ballsBowled;
	private final StringProperty oversBowled;
	private final IntegerProperty extrasConceded;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="TeamMatchDetailsPlayingEleven_Player_OneToMany_Table", joinColumns=@JoinColumn(name="TeamMatchDetails_Id"),
		inverseJoinColumns=@JoinColumn(name="Player_Id"))
	@Fetch(FetchMode.SUBSELECT)
	public List<Player> getPlayingEleven() {
		return playingEleven.get();
	}
	public void setPlayingEleven(List<Player> playerList) {
		this.playingEleven.set(FXCollections.observableArrayList(playerList));
	}
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="Match_Id")
	public Match getMatch() {
		return match.get();
	}
	public void setMatch(Match match) {
		this.match.set(match);
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="Team_Id")
	public Team getTeam() {
		return team.get();
	}
	public void setTeam(Team team) {
		this.team.set(team);
	}
	
	@Enumerated(EnumType.STRING)
	public MatchResult getMatchResult() {
		return matchResult.get();
	}
	public void setMatchResult(MatchResult matchResult) {
		this.matchResult.set(matchResult);
	}
	
	public boolean isBatting() {
		return batting.get();
	}
	public void setBatting(boolean batting) {
		this.batting.set(batting);
	}
	public boolean isBowling() {
		return bowling.get();
	}
	public void setBowling(boolean bowling) {
		this.bowling.set(bowling);
	}
	public int getTotalScored() {
		return totalScored.get();
	}
	public void setTotalScored(int totalScored) {
		this.totalScored.set(totalScored);
	}
	public int getWicketsLost() {
		return wicketsLost.get();
	}
	public void setWicketsLost(int wicketsLost) {
		this.wicketsLost.set(wicketsLost);
	}
	public int getBallsPlayed() {
		return ballsPlayed.get();
	}
	public void setBallsPlayed(int ballsPlayed) {
		this.ballsPlayed.set(ballsPlayed);
		this.oversPlayed.set(Misc.getOversFromBalls(this.getBallsPlayed()));
	}
	@Transient
	public String getOversPlayed() {
		return oversPlayed.get();
	}
	public int getExtrasGained() {
		return extrasGained.get();
	}
	public void setExtrasGained(int extrasGained) {
		this.extrasGained.set(extrasGained);
	}
	public int getTotalConceded() {
		return totalConceded.get();
	}
	public void setTotalConceded(int totalConceded) {
		this.totalConceded.set(totalConceded);
	}
	public int getWicketsGained() {
		return wicketsGained.get();
	}
	public void setWicketsGained(int wicketsGained) {
		this.wicketsGained.set(wicketsGained);
	}
	public int getBallsBowled() {
		return ballsBowled.get();
	}
	public void setBallsBowled(int ballsBowled) {
		this.ballsBowled.set(ballsBowled);
		this.oversBowled.set(Misc.getOversFromBalls(this.getBallsBowled()));
	}
	public int getOverNumber() {
		return overNumber.get();
	}
	public void setOverNumber(int overNumber) {
		this.overNumber.set(overNumber);
	}
	@Transient
	public String getOversBowled() {
		return oversBowled.get();
	}
	public int getExtrasConceded() {
		return extrasConceded.get();
	}
	public void setExtrasConceded(int extrasConceded) {
		this.extrasConceded.set(extrasConceded);
	}
	
	public TeamMatchDetails()
	{
		id= new SimpleIntegerProperty();
		playingEleven = new SimpleListProperty<Player>();
		match= new SimpleObjectProperty<Match>();
		team= new SimpleObjectProperty<Team>();
		matchResult = new SimpleObjectProperty<MatchResult>();
		batting = new SimpleBooleanProperty();
		bowling = new SimpleBooleanProperty();
		totalScored = new SimpleIntegerProperty();
		wicketsLost = new SimpleIntegerProperty();
		ballsPlayed = new SimpleIntegerProperty();
		oversPlayed = new SimpleStringProperty();
		extrasGained = new SimpleIntegerProperty();
		totalConceded = new SimpleIntegerProperty();
		wicketsGained = new SimpleIntegerProperty();
		ballsBowled = new SimpleIntegerProperty();
		oversBowled = new SimpleStringProperty();
		overNumber = new SimpleIntegerProperty();
		extrasConceded = new SimpleIntegerProperty();
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	public ListProperty<Player> playingElevenProperty() {
		return playingEleven;
	}
	public ObjectProperty<Match> matchProperty() {
		return match;
	}
	public ObjectProperty<Team> teamProperty() {
		return team;
	}
	public ObjectProperty<MatchResult> matchResultProperty() {
		return matchResult;
	}
	public BooleanProperty battingProperty() {
		return batting;
	}
	public BooleanProperty bowlingProperty() {
		return bowling;
	}
	public IntegerProperty totalScoredProperty() {
		return totalScored;
	}
	public IntegerProperty wicketsLostProperty() {
		return wicketsLost;
	}
	public IntegerProperty ballsPlayedProperty() {
		return ballsPlayed;
	}
	public StringProperty oversPlayedProperty() {
		return oversPlayed;
	}
	public IntegerProperty extrasGainedProperty() {
		return extrasGained;
	}
	public IntegerProperty totalConcededProperty() {
		return totalConceded;
	}
	public IntegerProperty wicketsGainedProperty() {
		return wicketsGained;
	}
	public IntegerProperty ballsBowledProperty() {
		return ballsBowled;
	}
	public StringProperty oversBowledProperty() {
		return oversBowled;
	}
	public IntegerProperty extrasConcededProperty() {
		return extrasConceded;
	}
	public IntegerProperty overNumberProperty() {
		return overNumber;
	}
}