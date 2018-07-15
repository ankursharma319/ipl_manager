package model.player.details;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import model.player.Player;

@Entity
public class PlayerCurrentTeamCareerDetails
{
	private final IntegerProperty id;
	private final IntegerProperty battingRuns;
	private final ObjectProperty<Player> player;
	
	private final IntegerProperty momAwards;
	private final IntegerProperty matchesPlayed;

	private final IntegerProperty battingHighestScore;
	private final IntegerProperty battingThirties;
	private final IntegerProperty battingFifties;
	private final IntegerProperty battingCenturies;

	private final IntegerProperty bowlingWickets;
	private final IntegerProperty bowlingFourWicketHauls;
	private final IntegerProperty bowlingFiveWicketHauls;
	private final StringProperty bestBowlingFigure;

	@Id
	@GeneratedValue
	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	@OneToOne(cascade=CascadeType.ALL, mappedBy="currentTeamCareerDetails")
	public Player getPlayer()
	{
		return player.get();
	}
	public void setPlayer(Player player)
	{
		this.player.set(player);
	}
	
	public int getBattingRuns() {
		return battingRuns.get();
	}

	public void setBattingRuns(int battingRuns) {
		this.battingRuns.set(battingRuns);
	}
	
	public int getMomAwards() {
		return this.momAwards.get();
	}

	public void setMomAwards(int momAwards) {
		this.momAwards.set(momAwards);
	}

	public int getMatchesPlayed() {
		return matchesPlayed.get();
	}

	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed.set(matchesPlayed);
	}

	public int getBattingHighestScore() {
		return battingHighestScore.get();
	}

	public void setBattingHighestScore(int battingHighestScore) {
		this.battingHighestScore.set(battingHighestScore);
	}

	public int getBattingThirties() {
		return battingThirties.get();
	}

	public void setBattingThirties(int battingThirties) {
		this.battingThirties.set(battingThirties);
	}

	public int getBattingFifties() {
		return battingFifties.get();
	}

	public void setBattingFifties(int battingFifties) {
		this.battingFifties.set(battingFifties);
	}

	public int getBattingCenturies() {
		return battingCenturies.get();
	}

	public void setBattingCenturies(int battingCenturies) {
		this.battingCenturies.set(battingCenturies);
	}

	public int getBowlingWickets() {
		return bowlingWickets.get();
	}

	public void setBowlingWickets(int bowlingWickets) {
		this.bowlingWickets.set(bowlingWickets);
	}
	
	public String getBestBowlingFigure() {
		return bestBowlingFigure.get();
	}

	public void setBestBowlingFigure(String bestBowlingFigure) {
		this.bestBowlingFigure.set(bestBowlingFigure);
	}
	
	public int getBowlingFourWicketHauls() {
		return bowlingFourWicketHauls.get();
	}

	public void setBowlingFourWicketHauls(int bowlingFourWicketHauls) {
		this.bowlingFourWicketHauls.set(bowlingFourWicketHauls);
	}

	public int getBowlingFiveWicketHauls() {
		return bowlingFiveWicketHauls.get();
	}

	public void setBowlingFiveWicketHauls(int bowlingFiveWicketHauls) {
		this.bowlingFiveWicketHauls.set(bowlingFiveWicketHauls);
	}
	
	public PlayerCurrentTeamCareerDetails()
	{		
		id = new SimpleIntegerProperty();
		player = new SimpleObjectProperty<Player>();
		momAwards = new SimpleIntegerProperty();
		matchesPlayed = new SimpleIntegerProperty();
		
		battingRuns = new SimpleIntegerProperty();
		battingHighestScore = new SimpleIntegerProperty();
		battingThirties = new SimpleIntegerProperty();
		battingFifties = new SimpleIntegerProperty();
		battingCenturies = new SimpleIntegerProperty();

		bowlingWickets = new SimpleIntegerProperty();
		bestBowlingFigure = new SimpleStringProperty();
		bowlingFourWicketHauls = new SimpleIntegerProperty();
		bowlingFiveWicketHauls = new SimpleIntegerProperty();
		
	}

	public IntegerProperty idProperty() {
		return id;
	}
	public ObjectProperty<Player> playerProperty() {
		return player;
	}
	public IntegerProperty momAwardsProperty() {
		return momAwards;
	}
	public IntegerProperty matchesPlayedProperty() {
		return matchesPlayed;
	}
	public IntegerProperty battingRunsProperty() {
		return battingRuns;
	}
	public IntegerProperty battingHighestScoreProperty() {
		return battingHighestScore;
	}
	public IntegerProperty battingThirtiesProperty() {
		return battingThirties;
	}
	public IntegerProperty battingFiftiesProperty() {
		return battingFifties;
	}
	public IntegerProperty battingCenturiesProperty() {
		return battingCenturies;
	}
	public IntegerProperty bowlingWicketsProperty() {
		return bowlingWickets;
	}
	public StringProperty bestBowlingFigureProperty() {
		return bestBowlingFigure;
	}
	public IntegerProperty bowlingFourWicketHaulsProperty() {
		return bowlingFourWicketHauls;
	}
	public IntegerProperty bowlingFiveWicketHaulsProperty() {
		return bowlingFiveWicketHauls;
	}
}
