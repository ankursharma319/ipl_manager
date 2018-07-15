package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.match.Match;
import model.match.MatchState;
import model.player.Country;
import model.player.Player;
import model.player.PlayerType;
import model.player.details.MatchDetails;
import model.team.Team;
import model.team.TeamMatchDetails;
import util.MiscUtilities;
import engine.ai.AI;
import engine.util.Misc;

public class TossScreenController implements IController, Initializable {

	private MasterController masterController;
	private String screenName;
	private Match match;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	@Override
	public void setMasterController(MasterController masterController) {
		this.masterController = masterController;
	}
	@Override
	public String getScreenName() {
		return this.screenName;
	}
	@Override
	public void setScreenName(String name) {
		this.screenName = name;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	
	public TossScreenController(){
	}
	
	@FXML
    private Label titleLabel, statusLabel, team2Label, team1Label;

    @FXML
    private TableView<Player> playingElevenTableView1, playingElevenTableView2;

	@FXML
    private TableColumn<Player, Country> countryColumn1, countryColumn2;
    @FXML
    private TableColumn<Player, Number> positionColumn1, positionColumn2;
    @FXML
    private TableColumn<Player, PlayerType> typeColumn1, typeColumn2;
    @FXML
    private TableColumn<Player, String> nameColumn1, nameColumn2;
    @FXML
    private Button button1, button2;

    @FXML
    private void handlleButtonClick(ActionEvent event) {
    	Button b = (Button)event.getSource();
    	if(b.getText().equals("Heads")||b.getText().equals("Tails"))
    	{
    		int rand = MiscUtilities.generateRandomInt(0, 1);
    		statusLabel.setText(match.getTeams().get(rand).getFullName() + " has won the toss");
			if(match.getTeams().get(rand)==masterController.getTournament().getUserTeam())
			{
				button1.setText("Bat");
				button2.setText("Bowl");
			}
			else
			{
				button1.setText("Bat");
				button2.setText("Bowl");
				
				String decision = AI.chooseBatOrBowl(match);
				statusLabel.setText(statusLabel.getText() + " and elected to " + decision + " first");
				if(decision.equals("bat"))
				{
					button1.setDisable(true);
				}
				else if(decision.equals("bowl"))
				{
					button2.setDisable(true);
				}
    		}
    	}
    	else if(b.getText().equals("Bat"))
    	{
    		MiscUtilities.log("Bat clicked");
    		for(TeamMatchDetails tmd:match.getTeamsMatchDetails())	{
    			tmd.setBowling(true);
    			tmd.setBatting(false);
    		}
    		match.getTeamsMatchDetails().get(Misc.findUserTeamIndex(match, masterController.getTournament().getUserTeam())).setBatting(true);
    		match.getTeamsMatchDetails().get(Misc.findUserTeamIndex(match, masterController.getTournament().getUserTeam())).setBowling(false);
    		MatchScreenController msc = (MatchScreenController)masterController.getController("MatchScreen");
    		match.setMatchState(MatchState.InningsOneInProgress);
    		msc.setMatch(match);
    		msc.setUserTeamIndex(Misc.findUserTeamIndex(match, masterController.getTournament().getUserTeam()));
    		msc.setUpScreen();
    		masterController.changeScreenTo("MatchScreen");
    	}
    	else if(b.getText().equals("Bowl"))
    	{
    		MiscUtilities.log("Bowl clicked");
    		for(TeamMatchDetails tmd:match.getTeamsMatchDetails())	{
    			tmd.setBowling(false);
    			tmd.setBatting(true);
    		}
    		match.getTeamsMatchDetails().get(Misc.findUserTeamIndex(match, masterController.getTournament().getUserTeam())).setBowling(true);
    		match.getTeamsMatchDetails().get(Misc.findUserTeamIndex(match, masterController.getTournament().getUserTeam())).setBatting(false);
    		MatchScreenController msc = (MatchScreenController)masterController.getController("MatchScreen");
    		msc.setMatch(match);
    		match.setMatchState(MatchState.InningsOneInProgress);
    		msc.setUserTeamIndex(Misc.findUserTeamIndex(match, masterController.getTournament().getUserTeam()));
    		msc.setUpScreen();
    		masterController.changeScreenTo("MatchScreen");
    	}
    }
    
    private void createMatchDetails()
    {
    	match.setTeamsMatchDetails(FXCollections.observableArrayList());
    	for(Team t: match.getTeams())
    	{
    		TeamMatchDetails tmd = new TeamMatchDetails();
    		tmd.setMatch(match);
    		tmd.setTeam(t);
    		tmd.setPlayingEleven(t.getCurrentPlayingEleven());
    		tmd.setBatting(false);
    		tmd.setBowling(false);
    		tmd.setBallsBowled(0);
    		tmd.setBallsPlayed(0);
    		tmd.setExtrasConceded(0);
    		tmd.setExtrasGained(0);
    		tmd.setMatchResult(null);
    		tmd.setOverNumber(0);
    		tmd.setTotalConceded(0);
    		tmd.setTotalScored(0);
    		tmd.setWicketsGained(0);
    		tmd.setWicketsLost(0);
    		
    		for(Player p:tmd.getPlayingEleven())
    		{
    			if(p.getTournamentDetails().getMatchDetailsList()==null||p.getTournamentDetails().getMatchDetailsList().isEmpty())
    			{
    				p.getTournamentDetails().setMatchDetailsList(FXCollections.observableArrayList());
    			}
    			MatchDetails md = new MatchDetails();
    			md.setBatted(false);
    			md.setBattingBalls(0);
    			md.setBattingDots(0);
    			md.setBattingFours(0);
    			md.setBattingFours(0);
    			md.setBattingPoints(0);
    			md.setBattingPoints(0);
    			md.setBattingRuns(0);
    			md.setBattingSixes(0);
    			md.setBowlingBalls(0);
    			md.setBowlingDots(0);
    			md.setBowlingFours(0);
    			md.setBowlingMaidens(0);
    			md.setBowlingPoints(0);
    			md.setBowlingRuns(0);
    			md.setBowlingSixes(0);
    			md.setBowlingWickets(0);
    			md.setMatch(match);
    			md.setMom(false);
    			md.setOutWicket(false);
    			md.setOverallPoints(0);
    			md.setMatch(match);
    			md.setPlayer(p);
    			md.setPlayerTournamentDetails(p.getTournamentDetails());
    			p.getTournamentDetails().getMatchDetailsList().add(md);
    		}
    		
    		match.getTeamsMatchDetails().add(tmd);
    	}
    }
    
    public void setUpScreen()
    {
    	createMatchDetails();
    	
    	button1.setText("Heads");
    	button2.setText("Tails");
    	button1.setDisable(false);
    	button2.setDisable(false);
    	
    	titleLabel.setText(match.getTeams().get(0).getShortName() + " vs. " + match.getTeams().get(1).getShortName() + "\r\n" + "Live from " + match.getStadium().getName());
    	statusLabel.setText("Toss time!");
    	team1Label.setText(match.getTeams().get(0).getFullName());
    	team2Label.setText(match.getTeams().get(1).getFullName());
    	
    	positionColumn1.setCellValueFactory(cellData->new SimpleIntegerProperty(1+match.getTeams().get(0).getCurrentPlayingEleven().indexOf(cellData.getValue())));
    	positionColumn2.setCellValueFactory(cellData->new SimpleIntegerProperty(1+match.getTeams().get(1).getCurrentPlayingEleven().indexOf(cellData.getValue())));
    	
    	nameColumn1.setCellValueFactory(cellData-> cellData.getValue().lastNameProperty());
    	nameColumn2.setCellValueFactory(cellData-> cellData.getValue().lastNameProperty());
    	
    	typeColumn1.setCellValueFactory(cellData->cellData.getValue().getGeneralDetails().playerTypeProperty());
    	typeColumn2.setCellValueFactory(cellData->cellData.getValue().getGeneralDetails().playerTypeProperty());
    	
    	countryColumn1.setCellValueFactory(cellData -> cellData.getValue().getGeneralDetails().countryProperty());
    	countryColumn2.setCellValueFactory(cellData -> cellData.getValue().getGeneralDetails().countryProperty());
    	
    	playingElevenTableView1.setItems(match.getTeams().get(0).currentPlayingElevenProperty());
    	playingElevenTableView2.setItems(match.getTeams().get(1).currentPlayingElevenProperty());
    }
}
