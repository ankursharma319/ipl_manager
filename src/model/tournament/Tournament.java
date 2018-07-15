package model.tournament;

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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import model.match.Match;
import model.player.Player;
import model.team.Team;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Tournament
{
	private final IntegerProperty id;
	private final IntegerProperty season;
	private final ListProperty<Team> teams;
	private final ListProperty<Match> matches;
	private final ListProperty<Player> unsoldPlayers;
	private final ListProperty<Player> retiredPlayers;
	private final ObjectProperty<Team> userTeam;
	
	@Id
	@GeneratedValue	
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="currentTournament")
	@Fetch(FetchMode.SUBSELECT)
	public List<Team> getTeams() {
		return teams.get();
	}
	public void setTeams(List<Team> teams) {
		this.teams.set(FXCollections.observableList(teams));
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, 
			mappedBy="tournament")
	@Fetch(FetchMode.SUBSELECT)
	public List<Match> getMatches()	{
		return this.matches.get();
	}
	public void setMatches(List<Match> matches) {
		this.matches.set(FXCollections.observableArrayList(matches));
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="UnsoldPlayer_TournamentId", referencedColumnName="id", nullable=true)
	@Fetch(FetchMode.SUBSELECT)
	public List<Player> getUnsoldPlayers() {
		return this.unsoldPlayers.get();
	}
	public void setUnsoldPlayers(List<Player> unsoldPlayers) {
		this.unsoldPlayers.set(FXCollections.observableArrayList(unsoldPlayers));
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="RetiredPlayer_TournamentId", referencedColumnName="id", nullable=true)
	@Fetch(FetchMode.SUBSELECT)
	public List<Player> getRetiredPlayers() {
		return this.retiredPlayers.get();
	}
	public void setRetiredPlayers(List<Player> retiredPlayers) {
		this.retiredPlayers.set(FXCollections.observableArrayList(retiredPlayers));
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="UserTeam_Id")
	public Team getUserTeam() {
		return this.userTeam.get();
	}
	public void setUserTeam(Team team) {
		this.userTeam.set(team);
	}
	
	public int getSeason() {
		return season.get();
	}
	public void setSeason(int season) {
		this.season.set(season);
	}
	public Tournament()
	{
		id= new SimpleIntegerProperty();
		season = new SimpleIntegerProperty();
		teams = new SimpleListProperty<Team>();
		matches = new SimpleListProperty<Match>();
		unsoldPlayers = new SimpleListProperty<Player>();
		retiredPlayers = new SimpleListProperty<Player>();
		userTeam = new SimpleObjectProperty<Team>();
	}

	public IntegerProperty idProperty() {
		return id;
	}
	public IntegerProperty seasonProperty() {
		return season;
	}
	public ListProperty<Team> teamsProperty() {
		return this.teams;
	}
	public ListProperty<Match> matchesProperty() {
		return matches;
	}
	public ListProperty<Player> unsoldPlayersProperty() {
		return unsoldPlayers;
	}
	public ListProperty<Player> retiredPlayersProperty() {
		return retiredPlayers;
	}
	public ObjectProperty<Team> userTeamProperty() {
		return userTeam;
	}
}