package model.player.details;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import util.MiscUtilities;
import engine.util.Misc;
import model.player.Player;
import model.tournament.Tournament;

@Entity
public class TournamentDetails
{
	private final IntegerProperty id;
	private final ObjectProperty<Player> player;
	private final ObjectProperty<Tournament> tournament;
	private final ListProperty<MatchDetails> matchDetailsList;
	private final IntegerProperty overallPoints;
	private final IntegerProperty momAwards;
	private final IntegerProperty matchesPlayed;
	
	private final IntegerProperty battingRuns;
	private final IntegerProperty battingBalls;
	private final DoubleProperty battingAverage;
	private final DoubleProperty battingStrikeRate;
	private final IntegerProperty battingOuts;
	private final IntegerProperty battingNotOuts;
	private final IntegerProperty battingDots;
	private final IntegerProperty battingFours;
	private final IntegerProperty battingSixes;
	private final IntegerProperty battingHighestScore;
	private final IntegerProperty battingThirties;
	private final IntegerProperty battingFifties;
	private final IntegerProperty battingCenturies;
	private final IntegerProperty battingPoints;

	private final IntegerProperty bowlingRuns;
	private final IntegerProperty bowlingWickets;
	private final IntegerProperty bowlingBalls;
	private final StringProperty bowlingOvers;
	private final DoubleProperty bowlingAverage;
	private final DoubleProperty bowlingEconomy;
	private final IntegerProperty bowlingMaidens;
	private final DoubleProperty bowlingStrikeRate;
	private final IntegerProperty bowlingDots;
	private final IntegerProperty bowlingFours;
	private final IntegerProperty bowlingSixes;
	private final IntegerProperty bowlingPoints;
	private final IntegerProperty bowlingFourWicketHauls;
	private final IntegerProperty bowlingFiveWicketHauls;
	private final StringProperty bestBowlingFigure;
	
	@Id
	@GeneratedValue
	//@GenericGenerator(name="foreignGenerator", strategy = "foreign", parameters={@Parameter(value="player", name="property")})
	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	@OneToOne(cascade=CascadeType.ALL, mappedBy="tournamentDetails")
	public Player getPlayer()
	{
		return player.get();
	}
	public void setPlayer(Player player)
	{
		this.player.set(player);
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="Tournament_Id")
	public Tournament getTournament()
	{
		return tournament.get();
	}
	public void setTournament(Tournament tournament)
	{
		this.tournament.set(tournament);
	}
	
	@OneToMany(cascade=CascadeType.ALL, targetEntity=MatchDetails.class, mappedBy="playerTournamentDetails")
	public List<MatchDetails> getMatchDetailsList() {
		return matchDetailsList.get();
	}

	public void setMatchDetailsList(List<MatchDetails> matchDetailsList) {
		ObservableList<MatchDetails> md = FXCollections.observableArrayList();
		md.addAll(matchDetailsList);
		this.matchDetailsList.set(md);
	}
	
	public int getBattingRuns() {
		return battingRuns.get();
	}
	public void setBattingRuns(int battingRuns) {
		this.battingRuns.set(battingRuns);
		this.battingStrikeRate.set(MiscUtilities.formatDouble((double)this.getBattingRuns()*100/this.getBattingBalls()));
		this.battingAverage.set(MiscUtilities.formatDouble((double)this.getBattingRuns()/this.getBattingOuts()));
	}

	public int getBattingBalls() {
		return battingBalls.get();
	}
	public void setBattingBalls(int battingBalls) {
		this.battingBalls.set(battingBalls);
	}

	@Transient
	public double getBattingAverage() {
		return battingAverage.get();
	}

	@Transient
	public double getBattingStrikeRate() {
		return battingStrikeRate.get();
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

	public int getBattingOuts() {
		return battingOuts.get();
	}
	public void setBattingOuts(int battingOuts) {
		this.battingOuts.set(battingOuts);
		this.battingAverage.set(MiscUtilities.formatDouble((double)this.getBattingRuns()/this.getBattingOuts()));
	}
	
	public int getBattingNotOuts() {
		return battingNotOuts.get();
	}
	public void setBattingNotOuts(int battingNotOuts) {
		this.battingNotOuts.set(battingNotOuts);
	}

	public int getBattingFours() {
		return battingFours.get();
	}
	public void setBattingFours(int battingFours) {
		this.battingFours.set(battingFours);
	}

	public int getBattingSixes() {
		return battingSixes.get();
	}
	public void setBattingSixes(int battingSixes) {
		this.battingSixes.set(battingSixes);
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

	public int getBowlingRuns() {
		return bowlingRuns.get();
	}
	public void setBowlingRuns(int bowlingRuns) {
		this.bowlingRuns.set(bowlingRuns);
		this.bowlingAverage.set(MiscUtilities.formatDouble((double)this.getBowlingRuns()/this.getBowlingWickets()));
		this.bowlingEconomy.set(MiscUtilities.formatDouble((double)this.getBowlingRuns()*6/this.getBowlingBalls()));
	}

	public int getBowlingWickets() {
		return bowlingWickets.get();
	}
	public void setBowlingWickets(int bowlingWickets) {
		this.bowlingWickets.set(bowlingWickets);
		this.bowlingAverage.set(MiscUtilities.formatDouble((double)this.getBowlingRuns()/this.getBowlingWickets()));
		this.bowlingStrikeRate.set(MiscUtilities.formatDouble((double)this.getBowlingBalls()/this.getBowlingWickets()));
	}

	public int getBowlingBalls() {
		return bowlingBalls.get();
	}
	public void setBowlingBalls(int bowlingBalls) {
		this.bowlingBalls.set(bowlingBalls);
		this.bowlingOvers.set(Misc.getOversFromBalls(bowlingBalls));
		this.bowlingEconomy.set(MiscUtilities.formatDouble((double)this.getBowlingRuns()*6/this.getBowlingBalls()));
		this.bowlingStrikeRate.set(MiscUtilities.formatDouble((double)this.getBowlingBalls()/this.getBowlingWickets()));
	}

	@Transient
	public String getBowlingOvers() {
		return bowlingOvers.get();
	}

	@Transient
	public double getBowlingAverage() {
		return bowlingAverage.get();
	}
	
	@Transient
	public double getBowlingEconomy() {
		return bowlingEconomy.get();
	}
	
	public int getBowlingMaidens() {
		return bowlingMaidens.get();
	}
	public void setBowlingMaidens(int bowlingMaidens) {
		this.bowlingMaidens.set(bowlingMaidens);
	}

	@Transient
	public double getBowlingStrikeRate() {
		return bowlingStrikeRate.get();
	}

	public int getBowlingFours() {
		return bowlingFours.get();
	}
	public void setBowlingFours(int bowlingFours) {
		this.bowlingFours.set(bowlingFours);
	}

	public int getBowlingSixes() {
		return bowlingSixes.get();
	}
	public void setBowlingSixes(int bowlingSixes) {
		this.bowlingSixes.set(bowlingSixes);
	}

	public int getOverallPoints() {
		return overallPoints.get();
	}
	public void setOverallPoints(int overallPoints) {
		this.overallPoints.set(overallPoints);
	}

	public int getBattingDots() {
		return battingDots.get();
	}
	public void setBattingDots(int battingDots) {
		this.battingDots.set(battingDots);
	}

	public int getBattingPoints() {
		return battingPoints.get();
	}
	public void setBattingPoints(int battingPoints) {
		this.battingPoints.set(battingPoints);
	}

	public int getBowlingDots() {
		return bowlingDots.get();
	}
	public void setBowlingDots(int bowlingDots) {
		this.bowlingDots.set(bowlingDots);
	}

	public int getBowlingPoints() {
		return bowlingPoints.get();
	}
	public void setBowlingPoints(int bowlingPoints) {
		this.bowlingPoints.set(bowlingPoints);
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

	public TournamentDetails()
	{
		id = new SimpleIntegerProperty();
		player = new SimpleObjectProperty<Player>();
		tournament = new SimpleObjectProperty<Tournament>();
		matchDetailsList = new SimpleListProperty<MatchDetails>();
		overallPoints = new SimpleIntegerProperty();
		momAwards = new SimpleIntegerProperty();
		matchesPlayed = new SimpleIntegerProperty();
		
		battingRuns = new SimpleIntegerProperty();
		battingBalls = new SimpleIntegerProperty();
		battingAverage = new SimpleDoubleProperty();
		battingStrikeRate = new SimpleDoubleProperty();
		battingOuts = new SimpleIntegerProperty();
		battingNotOuts = new SimpleIntegerProperty();
		battingDots = new SimpleIntegerProperty();
		battingFours = new SimpleIntegerProperty();
		battingSixes = new SimpleIntegerProperty();
		battingHighestScore = new SimpleIntegerProperty();
		battingThirties = new SimpleIntegerProperty();
		battingFifties = new SimpleIntegerProperty();
		battingCenturies = new SimpleIntegerProperty();
		battingPoints = new SimpleIntegerProperty();

		bowlingRuns = new SimpleIntegerProperty();
		bowlingWickets = new SimpleIntegerProperty();
		bowlingBalls = new SimpleIntegerProperty();
		bowlingOvers = new SimpleStringProperty();
		bowlingAverage = new SimpleDoubleProperty();
		bowlingEconomy = new SimpleDoubleProperty();
		bowlingMaidens = new SimpleIntegerProperty();
		bowlingStrikeRate = new SimpleDoubleProperty();
		bowlingDots = new SimpleIntegerProperty();
		bowlingFours = new SimpleIntegerProperty();
		bowlingSixes = new SimpleIntegerProperty();
		bowlingPoints = new SimpleIntegerProperty();
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
	public ListProperty<MatchDetails> matchDetailsListProperty() {
		return matchDetailsList;
	}
	public IntegerProperty overallPointsProperty() {
		return overallPoints;
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
	public IntegerProperty battingBallsProperty() {
		return battingBalls;
	}
	public DoubleProperty battingAverageProperty() {
		return battingAverage;
	}
	public DoubleProperty battingStrikeRateProperty() {
		return battingStrikeRate;
	}
	public IntegerProperty battingOutsProperty() {
		return battingOuts;
	}
	public IntegerProperty battingNotOutsProperty() {
		return battingNotOuts;
	}
	public IntegerProperty battingDotsProperty() {
		return battingDots;
	}
	public IntegerProperty battingFoursProperty() {
		return battingFours;
	}
	public IntegerProperty battingSixesProperty() {
		return battingSixes;
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
	public IntegerProperty battingPointsProperty() {
		return battingPoints;
	}
	public IntegerProperty bowlingRunsProperty() {
		return bowlingRuns;
	}
	public IntegerProperty bowlingWicketsProperty() {
		return bowlingWickets;
	}
	public IntegerProperty bowlingBallsProperty() {
		return bowlingBalls;
	}
	public StringProperty bowlingOversProperty() {
		return bowlingOvers;
	}
	public DoubleProperty bowlingAverageProperty() {
		return bowlingAverage;
	}
	public DoubleProperty bowlingEconomyProperty() {
		return bowlingEconomy;
	}
	public IntegerProperty bowlingMaidensProperty() {
		return bowlingMaidens;
	}
	public DoubleProperty bowlingStrikeRateProperty() {
		return bowlingStrikeRate;
	}
	public IntegerProperty bowlingDotsProperty() {
		return bowlingDots;
	}
	public IntegerProperty bowlingFoursProperty() {
		return bowlingFours;
	}
	public IntegerProperty bowlingSixesProperty() {
		return bowlingSixes;
	}
	public IntegerProperty bowlingPointsProperty() {
		return bowlingPoints;
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
	public ObjectProperty<Tournament> tournamentProperty() {
		return tournament;
	}
}
