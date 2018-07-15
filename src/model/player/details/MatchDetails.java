package model.player.details;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import util.MiscUtilities;
import engine.util.Misc;
import model.match.Match;
import model.player.Player;

@Entity
public class MatchDetails
{
	private final IntegerProperty id;
	private final ObjectProperty<Player> player;
	private final ObjectProperty<Match> match;
	private final IntegerProperty overallPoints;
	private final BooleanProperty mom;
	private final ObjectProperty<TournamentDetails> playerTournamentDetails;

	private final IntegerProperty battingRuns;
	private final IntegerProperty battingBalls;
	private final DoubleProperty battingStrikeRate;
	private final BooleanProperty outWicket;
	private final BooleanProperty batted;
	private final IntegerProperty battingDots;
	private final IntegerProperty battingFours;
	private final IntegerProperty battingSixes;
	private final IntegerProperty battingPoints;
	private final StringProperty battingStatus;
	private final BooleanProperty onStrike;

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
	private final StringProperty bowlingFigure;

	@Id
	@GeneratedValue
	//@GenericGenerator(name="foreignGenerator", strategy = "foreign", parameters={@Parameter(value="player", name="property")})
	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	@OneToOne(cascade=CascadeType.ALL, mappedBy="careerDetails")
	public Player getPlayer()
	{
		return player.get();
	}
	public void setPlayer(Player player)
	{
		this.player.set(player);
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="PlayerTournamentDetails_Id")
	public TournamentDetails getPlayerTournamentDetails() {
		return playerTournamentDetails.get();
	}

	public void setPlayerTournamentDetails(TournamentDetails tournamentDetails) {
		this.playerTournamentDetails.set(tournamentDetails);
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="Match_Id")
	public Match getMatch()
	{
		return match.get();
	}
	public void setMatch(Match match)
	{
		this.match.set(match);
	}
	
	public int getBattingRuns() {
		return battingRuns.get();
	}

	public void setBattingRuns(int battingRuns) {
		this.battingRuns.set(battingRuns);
		this.battingStrikeRate.set(MiscUtilities.formatDouble((double)this.getBattingRuns()*100/this.getBattingBalls()));
	}

	public int getBattingBalls() {
		return battingBalls.get();
	}

	public void setBattingBalls(int battingBalls) {
		this.battingBalls.set(battingBalls);
		this.battingStrikeRate.set(MiscUtilities.formatDouble((double)this.getBattingRuns()*100/this.getBattingBalls()));
	}

	@Transient
	public double getBattingStrikeRate() {
		return battingStrikeRate.get();
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

	public int getBowlingRuns() {
		return bowlingRuns.get();
	}

	public void setBowlingRuns(int bowlingRuns) {
		this.bowlingRuns.set(bowlingRuns);
		this.bowlingEconomy.set(MiscUtilities.formatDouble((double)this.getBowlingRuns()*6/this.getBowlingBalls()));
		this.bowlingAverage.set(MiscUtilities.formatDouble((double)this.getBowlingRuns()/this.getBowlingWickets()));
	}

	public int getBowlingWickets() {
		return bowlingWickets.get();
	}

	public void setBowlingWickets(int bowlingWickets) {
		this.bowlingWickets.set(bowlingWickets);
		this.bowlingStrikeRate.set(MiscUtilities.formatDouble((double)this.getBowlingBalls()/this.getBowlingWickets()));
		this.bowlingAverage.set(MiscUtilities.formatDouble((double)this.getBowlingRuns()/this.getBowlingWickets()));
	}

	public int getBowlingBalls() {
		return bowlingBalls.get();
	}

	public void setBowlingBalls(int bowlingBalls) {
		this.bowlingBalls.set(bowlingBalls);
		this.bowlingOvers.set(Misc.getOversFromBalls(bowlingBalls));
		this.bowlingEconomy.set(MiscUtilities.formatDouble((double)this.getBowlingRuns()*6/this.getBowlingBalls()));
		this.bowlingStrikeRate.set(MiscUtilities.formatDouble((double)this.getBowlingBalls()/this.getBowlingWickets()));
		this.bowlingFigure.set(Misc.generateBowlingFigure(this.getBowlingOvers(), 
				this.getBowlingDots(), this.getBowlingRuns(), this.getBowlingWickets()));
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

	public boolean isOutWicket() {
		return outWicket.get();
	}

	public void setOutWicket(boolean isOut) {
		this.outWicket.set(isOut);
	}

	public boolean isBatted() {
		return batted.get();
	}

	public void setBatted(boolean hasBatted) {
		this.batted.set(hasBatted);
	}
	
	public boolean isMom() {
		return mom.get();
	}
	public void setMom(boolean isMom) {
		this.mom.set(isMom);
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
	
	public boolean isOnStrike() {
		return onStrike.get();
	}
	public void setOnStrike(boolean value) {
		this.onStrike.set(value);
	}
	
	public String getBattingStatus() {
		return this.battingStatus.get();
	}
	
	public void setBattingStatus(String s) {
		this.battingStatus.set(s);
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
	
	public String getBowlingFigure() {
		return bowlingFigure.get();
	}

	public void setBowlingFigure(String bowlingFigure) {
		this.bowlingFigure.set(bowlingFigure);
	}

	public MatchDetails()
	{
		id = new SimpleIntegerProperty();
		player = new SimpleObjectProperty<Player>();
		match= new SimpleObjectProperty<Match>();
		overallPoints = new SimpleIntegerProperty();
		mom = new SimpleBooleanProperty();
		playerTournamentDetails = new SimpleObjectProperty<TournamentDetails>();
		
		battingRuns = new SimpleIntegerProperty();
		battingBalls = new SimpleIntegerProperty();
		battingStrikeRate = new SimpleDoubleProperty();
		battingDots = new SimpleIntegerProperty();
		battingFours = new SimpleIntegerProperty();
		battingSixes = new SimpleIntegerProperty();
		battingPoints = new SimpleIntegerProperty();
		outWicket = new SimpleBooleanProperty();
		batted  = new SimpleBooleanProperty();
		battingStatus = new SimpleStringProperty();
		onStrike = new SimpleBooleanProperty();

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
		bowlingFigure = new SimpleStringProperty();
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	public ObjectProperty<Player> playerProperty() {
		return player;
	}
	public ObjectProperty<Match> matchProperty() {
		return match;
	}
	public IntegerProperty overallPointsProperty() {
		return overallPoints;
	}
	public IntegerProperty battingRunsProperty() {
		return battingRuns;
	}
	public IntegerProperty battingBallsProperty() {
		return battingBalls;
	}
	public DoubleProperty battingStrikeRateProperty() {
		return battingStrikeRate;
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
	public BooleanProperty outWicketProperty() {
		return outWicket;
	}
	public BooleanProperty momProperty() {
		return mom;
	}
	public BooleanProperty battedProperty() {
		return batted;
	}
	public StringProperty bowlingFigureProperty() {
		return bowlingFigure;
	}
	public ObjectProperty<TournamentDetails> playerTournamentDetailsProperty() {
		return playerTournamentDetails;
	}

	public StringProperty battingStatusProperty() {
		return battingStatus;
	}

	public BooleanProperty onStrikeProperty() {
		return onStrike;
	}
}