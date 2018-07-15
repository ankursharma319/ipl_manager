package controller;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import model.player.Handedness;
import model.player.Player;
import model.player.PlayerType;
import model.player.bowling.BowlerType;
import model.team.Team;
import util.MiscUtilities;
import engine.util.Misc;

public class PlayingElevenSelectionController implements Initializable, IController
{
	private String screenName;
	private MasterController masterController;
	private Team team;
	
    @FXML
    private TableColumn<Player, Number> positionColumn, batAbilityColumn,
    batFormColumn, bowlFormColumn, bowlAbilityColumn, matchesColumn, runsColumn,
    batAverageColumn, batSRColumn, sixesColumn, batBestColumn, thirtiesColumn, 
    fiftiesColumn, hundredsColumn, wicketsColumn, bowlAverageColumn, economyColumn,
    bowlSRColumn, fourWksColumn, fiveWksColumn;
    @FXML
    private TableColumn<Player, Handedness> batTypeColumn;
    @FXML
    private TableColumn<Player, PlayerType> playerTypeColumn;
    @FXML
    private TableColumn<Player, BowlerType> bowlTypeColumn;
    @FXML
    private TableColumn<Player, String> playerNameColumn, bestBowlingColumn;
    @FXML
    private TableColumn<Player, Player> moveColumn;
    
    
    @FXML
    private Label playerNameLabel, titleLabel;
    
    @FXML
    private ImageView playerImageView;

    @FXML
    private Button continueButton;
    
    @FXML
    private RadioButton currentTournamentScopeButton, bowlingDetailsButton,
    careerScopeButton, battingDetailsButton, attributesButton, generalDetailsButton;
    
    @FXML
    private TableView<Player> teamSquadTableView;
    
    @FXML
    private ToggleGroup detailsScope, tableViewDetailsType;

    public PlayingElevenSelectionController()
    {
    	
	}
    
    @FXML
    private void handleContinueClick(ActionEvent event) {
    	MiscUtilities.log("Continue button clicked");
    	if(true)
    	{
    		team.getCurrentPlayingEleven().clear();
    		for(int i=0;i<11;i++)
    		{
    			team.getCurrentPlayingEleven().add(i, team.getCurrentSquad().get(i));
    		}
    		
    		TossScreenController tsc  = (TossScreenController)masterController.getController("TossScreen");
    		tsc.setMatch(Misc.findActiveMatch(masterController.getTournament().getMatches()));
    		tsc.setUpScreen();
    		masterController.changeScreenTo("TossScreen");
    	}
    }

    @FXML
    private void handleBackClick(ActionEvent event) {
    	
		team.getCurrentPlayingEleven().clear();
		for(int i=0;i<10;i++)
		{
			team.getCurrentPlayingEleven().add(i, team.getCurrentSquad().get(i));
		}
    	masterController.changeScreenTo(masterController.getPreviousController().getScreenName());
    }

    @FXML
    private void handleDetailsButtonClick(ActionEvent event) {
    	RadioButton rb = (RadioButton)event.getSource();
    	if(rb==generalDetailsButton)
    	{
    		MiscUtilities.log("General Details Button clicked");
    		setAllColumnsInvisible();
    		MiscUtilities.setVisible(batAverageColumn,
    				positionColumn, playerNameColumn, playerTypeColumn, runsColumn, batSRColumn, matchesColumn,
    				bowlAverageColumn, wicketsColumn, economyColumn, moveColumn);
    	}
    	else if(rb==attributesButton)
    	{
    		MiscUtilities.log("Attributes Button clicked");
    		setAllColumnsInvisible();
    		MiscUtilities.setVisible(positionColumn, playerNameColumn, playerTypeColumn, batTypeColumn, bowlTypeColumn, batAbilityColumn, 
    				batFormColumn, bowlAbilityColumn, bowlFormColumn, moveColumn);
    	}
    	else if(rb==battingDetailsButton)
    	{
    		MiscUtilities.log("Batting Details Button clicked");
    		setAllColumnsInvisible();
    		MiscUtilities.setVisible(positionColumn, playerNameColumn, playerTypeColumn, batTypeColumn, batAverageColumn, runsColumn, batSRColumn,
    				sixesColumn, batBestColumn, fiftiesColumn,
    				thirtiesColumn, hundredsColumn, moveColumn);
    	}
    	else if(rb==bowlingDetailsButton)
    	{
    		MiscUtilities.log("Bowling Details Button clicked");
    		setAllColumnsInvisible();
    		MiscUtilities.setVisible(positionColumn, playerNameColumn, playerTypeColumn, bowlTypeColumn, bowlAverageColumn, wicketsColumn,
    				economyColumn, bowlSRColumn, fourWksColumn, fiveWksColumn, moveColumn);
    	}
    }
    
    private void setAllColumnsInvisible()
    {
		MiscUtilities.setInvisible(batAbilityColumn, batFormColumn, bowlAbilityColumn, 
				bowlFormColumn, sixesColumn, batBestColumn, fiftiesColumn,
				thirtiesColumn, hundredsColumn, bowlAverageColumn, wicketsColumn,
				economyColumn, bowlSRColumn, fourWksColumn, fiveWksColumn, moveColumn,
				batAverageColumn, runsColumn, batSRColumn, matchesColumn, bowlAverageColumn, wicketsColumn,
				economyColumn, moveColumn, batTypeColumn, bowlTypeColumn, playerTypeColumn,
				playerNameColumn, positionColumn);
    }
    
    @FXML
    private void handleScopeButtonClick(ActionEvent event)
    {
    	if((RadioButton)event.getSource()==currentTournamentScopeButton)
    	{
    		MiscUtilities.log("Current Tournament Button clicked");
    	}
    	else if((RadioButton)event.getSource()==careerScopeButton)
    	{
    		MiscUtilities.log("Career Button clicked");
    	}
    }
    
    public void setTeam(Team t) {
    	this.team = t;
    	setUpScreen();
    }
    
    public void setUpScreen()
    {
    	positionColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCurrentTeam().getCurrentSquad().indexOf(cellData.getValue())+1));
    	playerNameColumn.setCellValueFactory(cellData-> cellData.getValue().lastNameProperty());
    	playerTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getGeneralDetails().playerTypeProperty());
    	batTypeColumn.setCellValueFactory(cellData-> cellData.getValue().getGeneralDetails().battingHandednessProperty());
    	bowlTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getGeneralDetails().bowlerTypeProperty());
    	batAbilityColumn.setCellValueFactory(cellData-> cellData.getValue().getGeneralDetails().battingAbilityProperty());
    	batFormColumn.setCellValueFactory(cellData->cellData.getValue().getGeneralDetails().battingFormProperty());
    	bowlAbilityColumn.setCellValueFactory(cellData->cellData.getValue().getGeneralDetails().bowlingAbilityProperty());
    	bowlFormColumn.setCellValueFactory(cellData-> cellData.getValue().getGeneralDetails().bowlingFormProperty());
    	moveColumn.setCellValueFactory(cellData-> new SimpleObjectProperty<Player>(cellData.getValue()));
    	
    	moveColumn.setCellFactory(param -> new MoveTableCell<Player, Player>());
    	
    	teamSquadTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>() {

			@Override
			public void changed(ObservableValue<? extends Player> observable,
					Player oldValue, Player newValue)
			{
				showPlayerInfo(newValue);
			}
		});
    	
    	teamSquadTableView.setItems(this.team.currentSquadProperty());
    	
    	showPlayerInfo(null);
    	careerScopeButton.getOnAction().handle(new ActionEvent(careerScopeButton, null));
    	generalDetailsButton.getOnAction().handle(new ActionEvent(generalDetailsButton, null));
    	generalDetailsButton.setSelected(true);
    	careerScopeButton.setSelected(true);
    }
    
	private void showPlayerInfo(Player player)
	{
		if(player==null)
		{
			this.playerImageView.setImage(null);
			this.playerNameLabel.setText("");
		}
		else
		{
			try
			{
				this.playerNameLabel.setText(player.getFirstName() + " " + player.getLastName());
			}
			catch(NullPointerException e)
			{
				MiscUtilities.log("Nullpointerexception in showPLayerInfo()");
			}
		}
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	public class MoveTableCell<S, T> extends TableCell<S, T>
	{
		private final Button up, down;
		private final HBox hbox;
		private final Background highlightedBg, normalBg;
		
		public MoveTableCell()
		{
			super();
			up = new Button("^");
			down = new Button("v");
			up.setOnAction(event -> {				
				int currentIndex = getIndex();
				try
				{
					Collections.swap(team.getCurrentSquad(), currentIndex, currentIndex-1);
					teamSquadTableView.getSelectionModel().select(currentIndex-1);
				}
				catch (Exception e)
				{
					MiscUtilities.log("Cannot move player even further up.");
				}
			});
			down.setOnAction(event -> {
				int currentIndex = getIndex();
				try
				{
					Collections.swap(team.getCurrentSquad(), currentIndex, currentIndex+1);
					teamSquadTableView.getSelectionModel().select(currentIndex+1);
				}
				catch (Exception e)
				{
					MiscUtilities.log("Cannot move player even further down.");
				}
			});
			hbox  = new HBox();
			hbox.setSpacing(10);
			hbox.setAlignment(Pos.CENTER);
			hbox.getChildren().add(up);
			hbox.getChildren().add(down);
			
			normalBg = hbox.getBackground();
			
			BackgroundFill bgf = new BackgroundFill(Paint.valueOf("Green"), null, null);
			highlightedBg = new Background(bgf);
		}
		
		@Override
		protected void updateItem(T item, boolean empty)
		{
			super.updateItem(item, empty);
			if(empty)
			{
				
			}
			else if(item==null)
			{
				
			}
			else
			{
				if(getIndex()<11)hbox.setBackground(highlightedBg);
				else{hbox.setBackground(normalBg);}
				setGraphic(hbox);
			}
		}
	}
}