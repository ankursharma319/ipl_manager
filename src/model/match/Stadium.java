package model.match;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import model.team.Team;

@Entity
public class Stadium
{
	private final IntegerProperty id;
	private final StringProperty name;
	private final ListProperty<Match> matches;
	private final ObjectProperty<Team> homeTeam;
	
	private final IntegerProperty baseSwing;
	private final IntegerProperty basePace;
	private final IntegerProperty baseSlowness;
	private final IntegerProperty baseSpin;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="StadiumIDGenerator")
	@TableGenerator(name="StadiumIDGenerator", allocationSize=1, pkColumnName="PrimaryKey", pkColumnValue="StadiumID")
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	
	@OneToMany(mappedBy="stadium", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public List<Match> getMatches()	{
		return this.matches.get();
	}
	public void setMatches(List<Match> matches) {
		this.matches.set(FXCollections.observableArrayList(matches));
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="homeStadium")
	public Team getHomeTeam() {
		return this.homeTeam.get();
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam.set(homeTeam);
	}
	
	public String getName() {
		return name.get();
	}	
	public void setName(String name) {
		this.name.set(name);
	}
	public int getBaseSwing() {
		return baseSwing.get();
	}
	public void setBaseSwing(int swing) {
		this.baseSwing.set(swing);
	}
	public int getBasePace() {
		return basePace.get();
	}
	public void setBasePace(int pace) {
		this.basePace.set(pace);
	}
	public int getBaseSlowness() {
		return baseSlowness.get();
	}
	public void setBaseSlowness(int slowness) {
		this.baseSlowness.set(slowness);
	}
	public int getBaseSpin() {
		return baseSpin.get();
	}
	public void setBaseSpin(int spin) {
		this.baseSpin.set(spin);
	}
	
	public Stadium()
	{
		id=new SimpleIntegerProperty();
		name= new SimpleStringProperty();
		matches = new SimpleListProperty<Match>();
		baseSwing = new SimpleIntegerProperty();
		baseSlowness = new SimpleIntegerProperty();
		basePace = new SimpleIntegerProperty();
		baseSpin = new SimpleIntegerProperty();
		homeTeam = new SimpleObjectProperty<Team>();
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	public StringProperty nameProperty() {
		return name;
	}
	public ListProperty<Match> matchesProperty() {
		return matches;
	}
	public IntegerProperty baseSwingProperty() {
		return baseSwing;
	}
	public IntegerProperty basePaceProperty() {
		return basePace;
	}
	public IntegerProperty baseSlownessProperty() {
		return baseSlowness;
	}
	public IntegerProperty baseSpinProperty() {
		return baseSpin;
	}
	public ObjectProperty<Team> homeTeamProperty() {
		return homeTeam;
	}
}