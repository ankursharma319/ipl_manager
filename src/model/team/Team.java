package model.team;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
import javax.persistence.TableGenerator;

import model.match.Match;
import model.match.Stadium;
import model.player.Player;
import model.tournament.Tournament;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Team
{
	private final IntegerProperty id;
	private final ListProperty<Player> currentSquad;
	private final StringProperty fullName;
	private final StringProperty shortName;
	private final ListProperty<Player> currentPlayingEleven;
	private final ObjectProperty<TeamRecords> teamRecords;
	private final ObjectProperty<Tournament> currentTournament;
	private final ListProperty<Match> matches;
	private final ObjectProperty<TeamTournamentDetails> teamTournamentDetails;
	private final ObjectProperty<Stadium> homeStadium;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TeamIDGenerator")
	@TableGenerator(name="TeamIDGenerator", allocationSize=1, pkColumnName="PrimaryKey", pkColumnValue="TeamID")
	public int getId()
	{
		return id.get();
	}
	public void setId(int id)
	{
		this.id.set(id);
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="currentTeam", 
			fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	public List<Player> getCurrentSquad()
	{
		return currentSquad.get();
	}
	public void setCurrentSquad(List<Player> playerList)
	{
		this.currentSquad.set(FXCollections.observableArrayList(playerList));
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="TeamCurrentPlayingEleven_Player_OneToMany_Table", joinColumns=@JoinColumn(name="TeamID"),
		inverseJoinColumns=@JoinColumn(name="PlayerID"))
	@Fetch(FetchMode.SUBSELECT)
	//@JoinColumn(name="PlayingEleven_ID", referencedColumnName="id", nullable=true, insertable=true, updatable=true)
	public List<Player> getCurrentPlayingEleven()
	{
		return currentPlayingEleven.get();
	}
	public void setCurrentPlayingEleven(List<Player> playerList)
	{
		this.currentPlayingEleven.set(FXCollections.observableArrayList(playerList));
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="TeamRecords_Id")
	public TeamRecords getTeamRecords(){
		return this.teamRecords.get();
	}
	public void setTeamRecords(TeamRecords recordsObject){
		this.teamRecords.set(recordsObject);
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="CurrentTournament_Id", insertable=true, nullable=true, updatable=true)
	public Tournament getCurrentTournament() {
		return currentTournament.get();
	}
	public void setCurrentTournament(Tournament currentTournament) {
		this.currentTournament.set(currentTournament);
	}
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, 
			mappedBy="teams")
	@Fetch(FetchMode.SUBSELECT)
	public List<Match> getMatches()	{
		return this.matches.get();
	}
	public void setMatches(List<Match> matches) {
		this.matches.set(FXCollections.observableArrayList(matches));
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="TeamTournamentDetails_Id")
	public TeamTournamentDetails getTeamTournamentDetails() {
		return this.teamTournamentDetails.get();
	}
	public void setTeamTournamentDetails(TeamTournamentDetails teamTournamentDetails) {
		this.teamTournamentDetails.set(teamTournamentDetails);
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="Stadium_Id")
	public Stadium getHomeStadium() {
		return this.homeStadium.get();
	}
	public void setHomeStadium(Stadium homeStadium) {
		this.homeStadium.set(homeStadium);
	}
	
	public String getFullName() {
		return fullName.get();
	}	
	public void setFullName(String fullName) {
		this.fullName.set(fullName);
	}
	
	public String getShortName() {
		return shortName.get();
	}	
	public void setShortName(String initials) {
		this.shortName.set(initials);
	}

	public Team()
	{
		id = new SimpleIntegerProperty();
		currentSquad= new SimpleListProperty<Player>();
		currentPlayingEleven = new SimpleListProperty<Player>();
		fullName= new SimpleStringProperty();
		shortName= new SimpleStringProperty();
		teamRecords= new SimpleObjectProperty<TeamRecords>();
		currentTournament = new SimpleObjectProperty<Tournament>();
		matches= new SimpleListProperty<Match>();
		teamTournamentDetails= new SimpleObjectProperty<TeamTournamentDetails>();
		homeStadium = new SimpleObjectProperty<Stadium>();
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	public ListProperty<Player> currentSquadProperty() {
		return currentSquad;
	}
	public ListProperty<Player> currentPlayingElevenProperty() {
		return currentPlayingEleven;
	}
	public StringProperty fullNameProperty() {
		return fullName;
	}
	public StringProperty shortNameProperty() {
		return shortName;
	}
	public ObjectProperty<TeamRecords> teamRecordsProperty() {
		return teamRecords;
	}
	public ObjectProperty<Tournament> currentTournamentProperty() {
		return currentTournament;
	}
	public ListProperty<Match> matchesProperty() {
		return matches;
	}
	public ObjectProperty<TeamTournamentDetails> teamTournamentDetailsProperty() {
		return teamTournamentDetails;
	}
	public ObjectProperty<Stadium> homeStadiumProperty() {
		return homeStadium;
	}
}
