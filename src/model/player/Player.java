package model.player;

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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import util.MiscUtilities;
import model.player.details.CareerDetails;
import model.player.details.GeneralDetails;
import model.player.details.PlayerCurrentTeamCareerDetails;
import model.player.details.TournamentDetails;
import model.team.Team;

@Entity
public class Player
{
	private final IntegerProperty id;
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty fullName;
	private final StringProperty styledName;
	
	private final ObjectProperty<CareerDetails> careerDetails;
	private final ObjectProperty<PlayerCurrentTeamCareerDetails> currentTeamCareerDetails;
	private final ObjectProperty<TournamentDetails> tournamentDetails;
	private final ObjectProperty<GeneralDetails> generalDetails;
	private final ObjectProperty<Team> currentTeam;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="PlayerIDGenerator")
	@TableGenerator(name="PlayerIDGenerator", allocationSize=1, pkColumnName="PrimaryKey", pkColumnValue="PlayerID")
	public int getId()
	{
		return id.get();
	}
	public void setId(int id)
	{
		this.id.set(id);
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name="CareerDetails_Id", insertable=true, updatable=true, nullable=true)
	public CareerDetails getCareerDetails()
	{
		return this.careerDetails.get();
	}
	public void setCareerDetails(CareerDetails details)
	{
		this.careerDetails.set(details);
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="CurrentTeamCareerDetails_Id", insertable=true, updatable=true, nullable=true)
	public PlayerCurrentTeamCareerDetails getCurrentTeamCareerDetails()
	{
		return this.currentTeamCareerDetails.get();
	}
	public void setCurrentTeamCareerDetails(PlayerCurrentTeamCareerDetails details)
	{
		this.currentTeamCareerDetails.set(details);
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="TournamanetDetails_Id", insertable=true, updatable=true, nullable=true)
	public TournamentDetails getTournamentDetails()
	{
		return this.tournamentDetails.get();
	}
	public void setTournamentDetails(TournamentDetails details)
	{
		this.tournamentDetails.set(details);
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="GeneralDetails_Id", insertable=true, updatable=true, nullable=true)
	public GeneralDetails getGeneralDetails()
	{
		return this.generalDetails.get();
	}
	public void setGeneralDetails(GeneralDetails details)
	{
		this.generalDetails.set(details);
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Team_Id")
	public Team getCurrentTeam() {
		return currentTeam.get();
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam.set(currentTeam);
	}
	
	public String getFirstName() {
		return firstName.get();
	}	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
		try
		{ 
			this.fullName.set(getFirstName() + " " +getLastName());
			this.styledName.set(getFirstName().charAt(0) + " " + this.getLastName());
		}catch(Exception e){MiscUtilities.log("Could not set styled name and firstname in Player.class");}
	}
		
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
		try
		{ 
			this.fullName.set(this.getFirstName() + " " +getLastName());
			this.styledName.set(this.getFirstName().charAt(0) + " " + this.getLastName());
		}catch(Exception e){MiscUtilities.log("Could not set styled name and firstname in Player.class");}
	}
	
	@Transient
	public String getFullName() {
		return fullName.get();
	}
	
	@Transient
	public String getStyledName() {
		return styledName.get();
	}
	
	public Player()
	{
		id=new SimpleIntegerProperty();
		firstName = new SimpleStringProperty();
		lastName = new SimpleStringProperty();
		fullName = new SimpleStringProperty();
		styledName = new SimpleStringProperty();
		careerDetails = new SimpleObjectProperty<CareerDetails>();
		tournamentDetails = new SimpleObjectProperty<TournamentDetails>();
		generalDetails = new SimpleObjectProperty<GeneralDetails>();
		currentTeam = new SimpleObjectProperty<Team>();
		currentTeamCareerDetails = new SimpleObjectProperty<PlayerCurrentTeamCareerDetails>();
	}
	
	public IntegerProperty idProperty()
	{
		return id;
	}
	public StringProperty firstNameProperty()
	{
		return firstName;
	}
	public StringProperty lastNameProperty()
	{
		return lastName;
	}
	public ObjectProperty<CareerDetails> careerDetailsProperty()
	{
		return careerDetails;
	}
	public ObjectProperty<TournamentDetails> tournamentDetailsProperty()
	{
		return tournamentDetails;
	}
	public ObjectProperty<GeneralDetails> generalDetailsProperty() {
		return generalDetails;
	}
	public ObjectProperty<Team> currentTeamProperty() {
		return currentTeam;
	}
	public ObjectProperty<PlayerCurrentTeamCareerDetails> currentTeamCareerDetailsProperty() {
		return currentTeamCareerDetails;
	}
	public StringProperty fullNameProperty() {
		return fullName;
	}
	public StringProperty styledNameProperty() {
		return styledName;
	}

}
