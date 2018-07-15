package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import model.match.Match;
import model.match.MatchResult;
import model.team.Team;
import model.team.TeamTournamentDetails;
import util.MiscUtilities;
import engine.ai.MatchSimEngine;
import engine.util.Misc;


public class GamePortalController implements IController, Initializable {

	private String screenName;
	private MasterController masterController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

	@Override
	public void setMasterController(MasterController masterController)
	{
		this.masterController = masterController;
	}

	@Override
	public String getScreenName()
	{
		return this.screenName;
	}
	@Override
	public void setScreenName(String name)
	{
		this.screenName = name;
	}

	@FXML
    private TableColumn<TeamTournamentDetails, Number> rankColumn, playedColumn, tiedColumn, 
    	wonColumn, nrrColumn, pointsColumn, lostColumn;    
	@FXML
    private TableColumn<TeamTournamentDetails, TeamTournamentDetails> last5Column;
    @FXML
    private TableColumn<TeamTournamentDetails, String> teamColumn;
    @FXML
    private TableView<TeamTournamentDetails> pointsTableTableView;

    @FXML
    private Label leftStatusLabel, randomInfoLabel, rightStatusLabel;

    @FXML
    private ImageView homeTeamLogoImageView, matchInfoImageView, iplLogoImageView, awayTeamLogoImageView;

    @FXML
    private ListView<Match> matchListListView;

    @FXML
    private Button continueButton;

    @FXML
    private void handleContinueClick(ActionEvent event)
    {
    	Button b = (Button)event.getSource();
    	if(b.getText().equals("Play Match"))
    	{	
    		PlayingElevenSelectionController pesc = (PlayingElevenSelectionController)masterController.getController("PlayingElevenSelection");
    		pesc.setTeam(masterController.getTournament().getUserTeam());
    		this.masterController.changeScreenTo("PlayingElevenSelection");
    	}
    	else if(b.getText().equals("Sim Match"))
    	{
    		MatchSimEngine.simMatch(Misc.findActiveMatch(masterController.getTournament().getMatches()));
    	}
    }
    
    public void setupScreen()
    {
    	ObservableList<Match> matches = FXCollections.observableArrayList(masterController.getTournament().getMatches());
    	matchListListView.setItems(matches);
    	
    	matchListListView.setCellFactory(new Callback<ListView<Match>, ListCell<Match>>() {
			
    		@Override
			public ListCell<Match> call(ListView<Match> list) {
				
    			return new ListCell<Match>(){
					
    				private Label l;
    				private HBox hbox;
    				private ImageView iv;
    				private Image cskImage;
    				
    				{
    					hbox = new HBox();
    					hbox.setSpacing(10);
						l = new Label();
						cskImage = new Image("file:resources/images/teams/" + "CSK" + ".png");
						iv = new ImageView();
						iv.setFitHeight(50);
						iv.setFitWidth(50);
    				}
    				
    				@Override
					protected void updateItem(Match item, boolean empty) {
						super.updateItem(item, empty);
						
						if(item==null||empty) {
							setText(null);
						}
						else
						{
							try
							{
								//MiscUtilities.log("updating item");
								hbox.getChildren().clear();
								l.setText(item.getTeams().get(0).getShortName()+ " vs. " + item.getTeams().get(1).getShortName());
								
								if(matchListListView.getSelectionModel().getSelectedItem()==item)
								{
									iv.setImage(cskImage);
									hbox.getChildren().add(iv);
								}
								
								hbox.getChildren().add(l);
								//setText("8:00pm - " + item.getStadium().getName());
								setGraphic(hbox);
							}
							catch (Exception e)
							{
								MiscUtilities.log("Error encountered while updating items of the Matchlistlistview at GamePortalController.java: " + e.toString());
							}
						}
					}
				};
			}
		});
    	
    	matchListListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Match>() {

			@Override
			public void changed(ObservableValue<? extends Match> observable,
					Match oldValue, Match newValue) {
				
				matches.set(matchListListView.getSelectionModel().getSelectedIndex(), newValue);
			}
		});
    	
    	ObservableList<TeamTournamentDetails> ttdl = FXCollections.observableArrayList();
    	for(Team t: masterController.getTournament().getTeams())
    	{
    		ttdl.add(t.getTeamTournamentDetails());
    	}
    	
    	rankColumn.setCellValueFactory(cellData -> cellData.getValue().rankProperty());
    	teamColumn.setCellValueFactory(cellData -> cellData.getValue().getTeam().shortNameProperty());
    	playedColumn.setCellValueFactory(cellData -> cellData.getValue().matchesPlayedProperty());
    	wonColumn.setCellValueFactory(cellData -> cellData.getValue().matchesWonProperty());
    	lostColumn.setCellValueFactory(cellData -> cellData.getValue().matchesLostProperty());
    	tiedColumn.setCellValueFactory(cellData -> cellData.getValue().matchesTiedProperty());
    	nrrColumn.setCellValueFactory(cellData -> cellData.getValue().netRunRateProperty());
    	pointsColumn.setCellValueFactory(cellData -> cellData.getValue().pointsProperty());
    	last5Column.setCellValueFactory(cellData -> new SimpleObjectProperty<TeamTournamentDetails>(cellData.getValue()));
    	
    	last5Column.setCellFactory(new Callback<TableColumn<TeamTournamentDetails,TeamTournamentDetails>, TableCell<TeamTournamentDetails,TeamTournamentDetails>>() {
			@Override
			public TableCell<TeamTournamentDetails, TeamTournamentDetails> call(
					TableColumn<TeamTournamentDetails, TeamTournamentDetails> param) {
				
				return new TableCell<TeamTournamentDetails, TeamTournamentDetails>(){
					
					private HBox hbox;
					{
						hbox = new HBox(5);
					}
					
					@Override
					protected void updateItem(TeamTournamentDetails item, boolean empty)
					{
						if(item==null&&!empty)
						{
							for(int i =0;i<5;i++)
							{
								Paint p = Paint.valueOf("Gray");
								Rectangle r = new Rectangle(10, 10, p);
								hbox.getChildren().add(r);
							}
						}
						else if(item!=null)
						{
							for(int i=0; i<5;i++)
							{
								Paint p;
								try
								{
									if(item.getMatchResults().get(item.getMatchResults().size()-1-i) == MatchResult.Won) p=Paint.valueOf("Green");
									else if(item.getMatchResults().get(item.getMatchResults().size()-1-i) == MatchResult.Lost) p=Paint.valueOf("Red");
									else { p =Paint.valueOf("Gray"); }
								}
								catch (Exception e)
								{
									p =Paint.valueOf("Gray");
								}
								hbox.getChildren().add(new Rectangle(10, 10, p));
							}
						}
						setGraphic(hbox);
					};
				};
			}
		});
    	
    	pointsTableTableView.setItems(ttdl);
    	
    	masterController.getTournament().setUserTeam(matches.get(0).getTeams().get(0));
    	
    	if(Misc.findActiveMatch(masterController.getTournament().getMatches()).getTeams().contains(masterController.getTournament().getUserTeam()))
    	{
    		continueButton.setText("Play Match");
    	}
    	else
    	{
    		continueButton.setText("Sim Match");
    	}
    }
    
    @FXML
    private void handleSaveAndBackClick(ActionEvent e)
    {
    	masterController.changeScreenTo("StartMenu");
    }
}