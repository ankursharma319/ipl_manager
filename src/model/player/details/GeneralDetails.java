package model.player.details;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import model.player.BatsmanType;
import model.player.Country;
import model.player.Handedness;
import model.player.Player;
import model.player.PlayerType;
import model.player.bowling.BowlerType;

@Entity
public class GeneralDetails
{
	private final IntegerProperty id;
	private final ObjectProperty<Player> player;
	private final ObjectProperty<Handedness> battingHandedness;
	private final ObjectProperty<Handedness> bowlingHandedness;
	private final ObjectProperty<BatsmanType> batsmanType;
	private final ObjectProperty<BowlerType> bowlerType;
	private final ObjectProperty<PlayerType> playerType;
	private final ObjectProperty<Country> country;
	private final IntegerProperty age;
	
	private final IntegerProperty battingAbility;
	private final IntegerProperty battingForm;
	private final IntegerProperty battingNaturalAgression;
	private final IntegerProperty battingAgressionLevel;
	private final IntegerProperty battingSettlement;
	private final IntegerProperty battingFastBowlingPreference;
	private final IntegerProperty battingSlowBallingPreference;
	
	private final IntegerProperty bowlingAbility;
	private final IntegerProperty bowlingForm;
	private final IntegerProperty bowlingAgressionLevel;
	private final IntegerProperty coolHead;
	
	@Id
	@GeneratedValue
	//@GenericGenerator(name="foreignGenerator", strategy = "foreign", parameters={@Parameter(value="player", name="property")})
	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="generalDetails")
	public Player getPlayer()
	{
		return player.get();
	}
	public void setPlayer(Player player)
	{
		this.player.set(player);
	}
	
	@Enumerated(EnumType.STRING)
	public PlayerType getPlayerType()
	{
		return this.playerType.get();
	}
	public void setPlayerType(PlayerType type)
	{
		this.playerType.set(type);
	}
	
	@Enumerated(EnumType.STRING)
	public Handedness getBattingHandedness() {
		return battingHandedness.get();
	}

	public void setBattingHandedness(Handedness battingHandedness) {
		this.battingHandedness.set(battingHandedness);
	}
	
	@Enumerated(EnumType.STRING)
	public Handedness getBowlingHandedness() {
		return bowlingHandedness.get();
	}

	public void setBowlingHandedness(Handedness bowlingHandedness) {
		this.bowlingHandedness.set(bowlingHandedness);
	}
	
	@Enumerated(EnumType.STRING)
	public BatsmanType getBatsmanType() {
		return batsmanType.get();
	}

	public void setBatsmanType(BatsmanType batsmanType) {
		this.batsmanType.set(batsmanType);
	}
	
	@Enumerated(EnumType.STRING)
	public BowlerType getBowlerType() {
		return bowlerType.get();
	}

	public void setBowlerType(BowlerType bowlerType) {
		this.bowlerType .set(bowlerType);
	}

	@Enumerated(EnumType.STRING)
	public Country getCountry() {
		return country.get();
	}

	public void setCountry(Country country) {
		this.country.set(country);
	}

	public int getAge() {
		return age.get();
	}

	public void setAge(int age) {
		this.age.set(age);
	}

	public int getBattingAbility() {
		return battingAbility.get();
	}

	public void setBattingAbility(int battingAbility) {
		this.battingAbility.set(battingAbility);
	}

	public int getBattingForm() {
		return battingForm.get();
	}

	public void setBattingForm(int battingForm) {
		this.battingForm.set(battingForm);
	}

	public int getBattingNaturalAgression() {
		return battingNaturalAgression.get();
	}

	public void setBattingNaturalAgression(int battingNaturalAgression) {
		this.battingNaturalAgression.set(battingNaturalAgression);
	}

	public int getBattingAgressionLevel() {
		return battingAgressionLevel.get();
	}

	public void setBattingAgressionLevel(int battingAgressionLevel) {
		this.battingAgressionLevel.set(battingAgressionLevel);
	}

	public int getBattingSettlement() {
		return battingSettlement.get();
	}

	public void setBattingSettlement(int battingSettlement) {
		this.battingSettlement.set(battingSettlement);
	}

	public int getBattingFastBowlingPreference() {
		return battingFastBowlingPreference.get();
	}

	public void setBattingFastBowlingPreference(int battingFastBowlingPreference) {
		this.battingFastBowlingPreference.set(battingFastBowlingPreference);
	}

	public int getBattingSlowBallingPreference() {
		return battingSlowBallingPreference.get();
	}

	public void setBattingSlowBallingPreference(int battingSlowBallingPreference) {
		this.battingSlowBallingPreference.set(battingSlowBallingPreference);
	}

	public int getBowlingAbility() {
		return bowlingAbility.get();
	}

	public void setBowlingAbility(int bowlingAbility) {
		this.bowlingAbility.set(bowlingAbility);
	}

	public int getBowlingForm() {
		return bowlingForm.get();
	}

	public void setBowlingForm(int bowlingForm) {
		this.bowlingForm.set(bowlingForm);
	}

	public int getBowlingAgressionLevel() {
		return bowlingAgressionLevel.get();
	}

	public void setBowlingAgressionLevel(int bowlingAgressionLevel) {
		this.bowlingAgressionLevel.set(bowlingAgressionLevel);
	}

	public int getCoolHead() {
		return coolHead.get();
	}

	public void setCoolHead(int coolHead) {
		this.coolHead.set(coolHead);
	}
	
	public GeneralDetails()
	{
		id=new SimpleIntegerProperty();
		battingHandedness = new SimpleObjectProperty<Handedness>();
		bowlingHandedness = new SimpleObjectProperty<Handedness>();
		batsmanType = new SimpleObjectProperty<BatsmanType>();
		bowlerType= new SimpleObjectProperty<BowlerType>();
		playerType = new SimpleObjectProperty<PlayerType>();
		country = new SimpleObjectProperty<Country>();
		age = new SimpleIntegerProperty();
		player = new SimpleObjectProperty<Player>();
		
		battingAbility = new SimpleIntegerProperty();
		battingForm= new SimpleIntegerProperty();
		battingNaturalAgression= new SimpleIntegerProperty();
		battingAgressionLevel= new SimpleIntegerProperty();
		battingSettlement= new SimpleIntegerProperty();
		battingFastBowlingPreference= new SimpleIntegerProperty();
		battingSlowBallingPreference= new SimpleIntegerProperty();
		
		bowlingAbility= new SimpleIntegerProperty();
		bowlingForm= new SimpleIntegerProperty();
		bowlingAgressionLevel= new SimpleIntegerProperty();
		coolHead= new SimpleIntegerProperty();
		
	}
	
	public ObjectProperty<Handedness> battingHandednessProperty() {
		return battingHandedness;
	}
	public ObjectProperty<Handedness> bowlingHandednessProperty() {
		return bowlingHandedness;
	}
	public ObjectProperty<BowlerType> bowlerTypeProperty() {
		return bowlerType;
	}
	public ObjectProperty<PlayerType> playerTypeProperty() {
		return playerType;
	}
	public IntegerProperty idProperty() {
		return id;
	}
	public ObjectProperty<Country> countryProperty() {
		return country;
	}
	public IntegerProperty ageProperty() {
		return age;
	}
	public ObjectProperty<Player> playerProperty() {
		return player;
	}
	public IntegerProperty battingAbilityProperty() {
		return battingAbility;
	}
	public IntegerProperty battingFormProperty() {
		return battingForm;
	}
	public IntegerProperty battingNaturalAgressionProperty() {
		return battingNaturalAgression;
	}
	public IntegerProperty battingAgressionLevelProperty() {
		return battingAgressionLevel;
	}
	public IntegerProperty battingSettlementProperty() {
		return battingSettlement;
	}
	public IntegerProperty battingFastBowlingPreferenceProperty() {
		return battingFastBowlingPreference;
	}
	public IntegerProperty battingSlowBallingPreferenceProperty() {
		return battingSlowBallingPreference;
	}
	public IntegerProperty bowlingAbilityProperty() {
		return bowlingAbility;
	}
	public IntegerProperty bowlingFormProperty() {
		return bowlingForm;
	}
	public IntegerProperty bowlingAgressionLevelProperty() {
		return bowlingAgressionLevel;
	}
	public IntegerProperty coolHeadProperty() {
		return coolHead;
	}

	public ObjectProperty<BatsmanType> batsmanTypeProperty() {
		return batsmanType;
	}
}
