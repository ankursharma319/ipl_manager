package controller;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.match.Match;
import model.match.MatchState;
import model.player.BatsmanType;
import model.player.Player;
import model.player.PlayerType;
import model.player.bowling.BowlerType;
import model.player.details.MatchDetails;
import model.team.TeamMatchDetails;
import service.images.ImageRepository;
import util.MiscUtilities;
import engine.core.BallResult;
import engine.core.Engine;
import engine.util.Misc;

public class MatchScreenController implements IController, Initializable {

	private MasterController masterController;
	private String screenName;
	private Match match;
	private int userTeamIndex, numberOfOversInMatch;
	private ObservableList<MatchDetails> battingTeamPlayersMatchDetails, bowlingTeamPlayersMatchDetails,
		playersWhoHaveBowled, currentBatsmen, currentBowlers;
	private TeamMatchDetails battingTeamMatchDetails, bowlingTeamMatchDetails;
	private Engine engine;
	private boolean isNextBatsmanChosen, isCurrentBowlerChosen;
	
	@FXML
    private Label bowlerNameLabel, scorelineLabel, fow1, fow2, fow3, fow4, fow5, fow6, fow7, fow8, fow9, fow10,
    	batsmanNameLabel2, currentOverLabel, batsmanNameLabel1, relativeTargetLabel, rrrLabel, battingCardTitle, 
    	batsmanScoreLabel1, batsmanScoreLabel2, matchTitleLabel, belowBattingCardLabel, bowlerFiguresLabel, 
    	batsmanFormLabel1, batsmanFormLabel2, crrLabel, scoreLabel, last18DeliveriesLabel, bowlingFormLabel, 
    	bowlingAbilityLabel, batsmanAbilityLabel1, batsmanAbilityLabel2, projectedOrTargetLabel, paceLabel,
    	swingLabel, slownessLabel, spinLabel, batsmanTypeLabel1, batsmanTypeLabel2, bowlerTypeLabel, lastBallLabel;

    @FXML
    private ImageView bowlerPlayerTypeImageView, team1LogoImageView, channelLogoImageView,
    	bowlerImage, iplLogoImageView, batsmanImage2, batsmanImage1, team2LogoImageView, 
    	batsmanPlayerTypeImageView1, batsmanPlayerTypeImageView2;
    
    @FXML
    private Button nextBallButton, nextOverButton, batsmanChooseOKButton, bowlerChooseOKButton;    
    @FXML
    private ProgressBar settledProgressBar2, settledProgressBar1;
    @FXML
    private Slider bowlerAgressionSlider, agressionSlider1, agressionSlider2;
    @FXML
    private ComboBox<MatchDetails> bowlerChooseComboBox, batsmanChooseComboBox;

    @FXML
    private TableView<MatchDetails> twoBatsmenTableView, bowlingCardTableView, battingCardTableView, twoBowlersTableView;

    @FXML
    private TableColumn<MatchDetails, String> bowlerNameColumn, batsmanNameColumn,  
    batsmanStatusColumn, _bowlerNameColumn, _batsmanNameColumn, _bowlingOversColumn, bowlingOversColumn;
    @FXML
    private TableColumn<MatchDetails, PlayerType> bowlerPlayerTypeColumn, batsmanPlayerTypeColumn;
    @FXML
    private TableColumn<MatchDetails, BatsmanType> _batsmanTypeColumn, batsmanBattingTypeColumn;
    @FXML
    private TableColumn<MatchDetails, BowlerType> _bowlerTypeColumn, bowlerBowlingTypeColumn;
    
    @FXML
    private TableColumn<MatchDetails, Number> _bowlingDotsColumn, _bowlingBallsColumn, _battingDotsColumn, bowlingDotsColumn, 
    bowlingRunsColumn, _battingRunsColumn, battingDotsColumn, bowlingBallsColumn,
    battingRunsColumn, battingFoursColumn, _battingFoursColumn, battingSRColumn, _battingSRColumn, bowlingEconomyColumn,
    _bowlingEconomyColumn, bowlingAverageColumn, _bowlingAverageColumn, _battingSixesColumn, 
    battingSixesColumn, _bowlingSixesColumn, bowlingSixesColumn, _bowlingFoursColumn, bowlingFoursColumn, 
    battingPositionColumn, _bowlingMaidensColumn, bowlingMaidensColumn, _bowlingPointsColumn, bowlingPointsColumn, 
    battingBallsColumn, _battingBallsColumn, _bowlingSRColumn, bowlingSRColumn, bowlingWicketsColumn, 
    _bowlingWicketsColumn, _battingBallsColumnm, _battingPointsColumn, battingPointsColumn, _bowlingRunsColumn;
    
    @FXML
    private void handleNextBallClick(ActionEvent event) {
    	BallResult br = engine.nextBall(currentBatsmen.get(0).getPlayer(), currentBowlers.get(0).getPlayer(), match);
    	updateMatch(br);
    }

    private void updateMatch(BallResult br)
    {
    	battingTeamMatchDetails.setTotalScored(battingTeamMatchDetails.getTotalScored() + br.getRuns());
    	battingTeamMatchDetails.setBallsPlayed(battingTeamMatchDetails.getBallsPlayed() + 1);
    	bowlingTeamMatchDetails.setTotalConceded(bowlingTeamMatchDetails.getTotalConceded()+br.getRuns());
    	bowlingTeamMatchDetails.setBallsBowled(bowlingTeamMatchDetails.getBallsBowled()+1);
    	
    	currentBatsmen.get(0).setBattingBalls(currentBatsmen.get(0).getBattingBalls()+1);
    	currentBowlers.get(0).setBowlingBalls(currentBowlers.get(0).getBowlingBalls()+1);
    	currentBatsmen.get(0).setBattingRuns(currentBatsmen.get(0).getBattingRuns()+br.getRuns());
    	currentBowlers.get(0).setBowlingRuns(currentBowlers.get(0).getBowlingRuns()+br.getRuns());
    	
    	switch(br)
    	{
			case Chance:
				currentBatsmen.get(0).setBattingDots(currentBatsmen.get(0).getBattingDots()+1);
				currentBowlers.get(0).setBowlingDots(currentBowlers.get(0).getBowlingDots()+1);
				break;
			case Dot:
				currentBatsmen.get(0).setBattingDots(currentBatsmen.get(0).getBattingDots()+1);
				currentBowlers.get(0).setBowlingDots(currentBowlers.get(0).getBowlingDots()+1);
				break;
			case Four:
				currentBatsmen.get(0).setBattingFours(currentBatsmen.get(0).getBattingFours()+1);
				currentBowlers.get(0).setBowlingFours(currentBowlers.get(0).getBowlingFours()+1);
				break;
			case NoBall:
		    	currentBowlers.get(0).setBowlingBalls(currentBowlers.get(0).getBowlingBalls()-1);
		    	currentBatsmen.get(0).setBattingRuns(currentBatsmen.get(0).getBattingRuns()-1);
		    	break;
			case Six:
				currentBatsmen.get(0).setBattingSixes(currentBatsmen.get(0).getBattingSixes()+1);
				currentBowlers.get(0).setBowlingSixes(currentBowlers.get(0).getBowlingSixes()+1);
				break;
			case Wide:
		    	currentBatsmen.get(0).setBattingBalls(currentBatsmen.get(0).getBattingBalls()-1);
		    	currentBowlers.get(0).setBowlingBalls(currentBowlers.get(0).getBowlingBalls()-1);
		    	currentBatsmen.get(0).setBattingRuns(currentBatsmen.get(0).getBattingRuns()-1);
				break;
			case Out:
				currentBatsmen.get(0).setBattingDots(currentBatsmen.get(0).getBattingDots()+1);
				currentBowlers.get(0).setBowlingDots(currentBowlers.get(0).getBowlingDots()+1);
				currentBatsmen.get(0).setOutWicket(true);
				currentBatsmen.get(0).setBattingStatus(Misc.generateBattingStatus(currentBowlers.get(0).getPlayer().getLastName(), 
						bowlingTeamPlayersMatchDetails.get(MiscUtilities.generateRandomInt(0, 10)).getPlayer().getLastName(), "wk"));
				currentBowlers.get(0).setBowlingWickets(currentBowlers.get(0).getBowlingWickets()+1);
				break;
			default:
				break;
    	}
    	
    	if(br==BallResult.Wide||br==BallResult.NoBall) {
    		battingTeamMatchDetails.setExtrasGained(battingTeamMatchDetails.getExtrasGained()+1);
    		bowlingTeamMatchDetails.setExtrasConceded(bowlingTeamMatchDetails.getExtrasConceded()+1);        	
    		battingTeamMatchDetails.setBallsPlayed(battingTeamMatchDetails.getBallsPlayed() - 1);
    		bowlingTeamMatchDetails.setBallsBowled(bowlingTeamMatchDetails.getBallsBowled()-1);
    	}
    	else if(br==BallResult.Out) {
    		isNextBatsmanChosen = false;
    		battingTeamMatchDetails.setWicketsLost(battingTeamMatchDetails.getWicketsLost()+1);
    		bowlingTeamMatchDetails.setWicketsGained(bowlingTeamMatchDetails.getWicketsGained()+1);
    		
    		switch(battingTeamMatchDetails.getWicketsLost())
    		{
    			case 1:
    				fow1.setText(Misc.getFOWText(battingTeamMatchDetails.getTotalScored(), battingTeamMatchDetails.getWicketsLost(), 
    						battingTeamMatchDetails.getOversPlayed(), currentBatsmen.get(0).getPlayer()));
    				break;
    			case 2:
    				fow2.setText(Misc.getFOWText(battingTeamMatchDetails.getTotalScored(), battingTeamMatchDetails.getWicketsLost(), 
    						battingTeamMatchDetails.getOversPlayed(), currentBatsmen.get(0).getPlayer()));
    				break;
    			case 3:
    				fow3.setText(Misc.getFOWText(battingTeamMatchDetails.getTotalScored(), battingTeamMatchDetails.getWicketsLost(), 
    						battingTeamMatchDetails.getOversPlayed(), currentBatsmen.get(0).getPlayer()));
    				break;
    			case 4:
    				fow4.setText(Misc.getFOWText(battingTeamMatchDetails.getTotalScored(), battingTeamMatchDetails.getWicketsLost(), 
    						battingTeamMatchDetails.getOversPlayed(), currentBatsmen.get(0).getPlayer()));
    				break;
    			case 5:
    				fow5.setText(Misc.getFOWText(battingTeamMatchDetails.getTotalScored(), battingTeamMatchDetails.getWicketsLost(), 
    						battingTeamMatchDetails.getOversPlayed(), currentBatsmen.get(0).getPlayer()));
    				break;
    			case 6:
    				fow6.setText(Misc.getFOWText(battingTeamMatchDetails.getTotalScored(), battingTeamMatchDetails.getWicketsLost(), 
    						battingTeamMatchDetails.getOversPlayed(), currentBatsmen.get(0).getPlayer()));
    				break;
    			case 7:
    				fow7.setText(Misc.getFOWText(battingTeamMatchDetails.getTotalScored(), battingTeamMatchDetails.getWicketsLost(), 
    						battingTeamMatchDetails.getOversPlayed(), currentBatsmen.get(0).getPlayer()));
    				break;
    			case 8:
    				fow8.setText(Misc.getFOWText(battingTeamMatchDetails.getTotalScored(), battingTeamMatchDetails.getWicketsLost(), 
    						battingTeamMatchDetails.getOversPlayed(), currentBatsmen.get(0).getPlayer()));
    				break;
    			case 9:
    				fow9.setText(Misc.getFOWText(battingTeamMatchDetails.getTotalScored(), battingTeamMatchDetails.getWicketsLost(), 
    						battingTeamMatchDetails.getOversPlayed(), currentBatsmen.get(0).getPlayer()));
    				break;
    			case 10:
    				fow10.setText(Misc.getFOWText(battingTeamMatchDetails.getTotalScored(), battingTeamMatchDetails.getWicketsLost(), 
    						battingTeamMatchDetails.getOversPlayed(), currentBatsmen.get(0).getPlayer()));
    				break;
    		}
    		
    		if(battingTeamMatchDetails.getWicketsLost()==10)
    		{
    			MiscUtilities.log("Innings Over! All out.");
    			nextBallButton.setDisable(true);
    			nextOverButton.setDisable(true);
    		}
    		else
    		{
    			goToNewBatsman();
     		}
    	}
    	
    	currentBatsmen.get(0).setBattingPoints(Misc.calculateBattingPoints(currentBatsmen.get(0)));
    	currentBowlers.get(0).setBowlingPoints(Misc.calculateBowlingPoints(currentBowlers.get(0)));
    	
    	if(battingTeamMatchDetails.getBallsPlayed()%6==0&&br!=BallResult.NoBall&&br!=BallResult.Wide)
    	{
    		MiscUtilities.log("Over up came in if loop. Balls played" + battingTeamMatchDetails.getBallsPlayed());
    		battingTeamMatchDetails.setOverNumber(battingTeamMatchDetails.getOverNumber()+1);
    		bowlingTeamMatchDetails.setOverNumber(bowlingTeamMatchDetails.getOverNumber()+1);
    		
    		if(bowlingTeamMatchDetails.getOverNumber()<numberOfOversInMatch)
    		{
        		isCurrentBowlerChosen = false;
        		goToNextOver();        		
    		}
    		else
    		{
    			MiscUtilities.log("Innings over. 20 overs done");
    			nextBallButton.setDisable(true);
    			nextOverButton.setDisable(true);
    		}
    	}
    	
    	if(!MiscUtilities.isEven(br.getRuns()) && !br.equals(BallResult.Wide) && !br.equals(BallResult.NoBall))	{
    		changeStrike();
    		renewCurrentBatsmenBindings();
    	}
    	
    	updateLabels(br);
    }

	private void updateLabels(BallResult br)
	{
		scoreLabel.setText("Score: "+ Integer.toString(battingTeamMatchDetails.getTotalScored()) + "-" + 
    			Integer.toString(battingTeamMatchDetails.getWicketsLost()));
		scorelineLabel.setText(battingTeamMatchDetails.getTeam().getShortName() + "  " + 
				Integer.toString(battingTeamMatchDetails.getTotalScored()) + "-" + 
    			Integer.toString(battingTeamMatchDetails.getWicketsLost())
    			+ "  " + battingTeamMatchDetails.getOversPlayed());
    	
		batsmanScoreLabel1.setText(currentBatsmen.get(0).getBattingRuns() + " (" + currentBatsmen.get(0).getBattingBalls()+ ")*");
    	batsmanScoreLabel2.setText(currentBatsmen.get(1).getBattingRuns() + " (" + currentBatsmen.get(1).getBattingBalls()+ ")*");
    	belowBattingCardLabel.setText(scoreLabel.getText() + " in " + battingTeamMatchDetails.getOversPlayed() + 
    			" overs   Extras: "+ Integer.toString(battingTeamMatchDetails.getExtrasGained()));
    	lastBallLabel.setText("Last Ball: "+ br.getSymbol());
    	crrLabel.setText("Current Run Rate: " + Double.toString(MiscUtilities.round((double)battingTeamMatchDetails.getTotalScored()*6/battingTeamMatchDetails.getBallsPlayed(), 2)));
    	
    	if(battingTeamMatchDetails.getBallsPlayed()%6==1)
		{
    		{
	    		currentOverLabel.setText("Current Over: ");
				if(battingTeamMatchDetails.getBallsPlayed()!=1)MiscUtilities.appendToLabel(last18DeliveriesLabel, "| ");
			}
		}
    	MiscUtilities.appendToLabel(currentOverLabel, br.getSymbol()+ " ");
    	
    	if(battingTeamMatchDetails.getBallsPlayed()<18)
    	{
    		MiscUtilities.appendToLabel(last18DeliveriesLabel, br.getSymbol() + " ");
    	}
    	else
    	{
    		String t = last18DeliveriesLabel.getText();
    		String t2 = t.substring(20);
    		t2.trim();
    		if(t2.startsWith("|"))t2 = t2.substring(2);
    		t2 = t2+br.getSymbol()+" ";
    		int i = t2.indexOf(' ');
    		String correctVersion = t2.substring(i);
    		correctVersion = "Last 18 Deliveries:" + correctVersion;
    		last18DeliveriesLabel.setText(correctVersion);
    	}
    	
    	if(match.getMatchState()==MatchState.InningsOneInProgress)
    	{
    		projectedOrTargetLabel.setText("Projected: " + Integer.toString((battingTeamMatchDetails.getTotalScored()*numberOfOversInMatch*6/battingTeamMatchDetails.getBallsPlayed())));
    		rrrLabel.setText("");
    		relativeTargetLabel.setText("Overs left in innings: " + Misc.getOversFromBalls(numberOfOversInMatch*6 - bowlingTeamMatchDetails.getBallsBowled()));
    	}
    	else if(match.getMatchState()==MatchState.InningsTwoInProgress)
    	{
    		projectedOrTargetLabel.setText("Target: " + bowlingTeamMatchDetails.getTotalScored()+1);
    		rrrLabel.setText("Required Run Rate: " + Misc.calculateRequiredRunRate(
    				bowlingTeamMatchDetails.getTotalScored()+1, battingTeamMatchDetails.getTotalScored(), battingTeamMatchDetails.getBallsPlayed()));
    		relativeTargetLabel.setText("Runs required: " + " " +" Balls left: ");
    	}
	}

	private void goToNextOver()
	{
		nextBallButton.setDisable(true);
		nextOverButton.setDisable(true);
		bowlerChooseComboBox.setDisable(false);
		bowlerChooseOKButton.setDisable(false);
		if(currentBowlers.size()==2)bowlerChooseComboBox.setValue(currentBowlers.get(1));
	}
	
	private void goToNewBatsman()
	{
		nextBallButton.setDisable(true);
		nextOverButton.setDisable(true);
		batsmanChooseComboBox.setDisable(false);
		batsmanChooseOKButton.setDisable(false);
		int higherIndex=0;
		if(battingTeamPlayersMatchDetails.indexOf(currentBatsmen.get(0))<battingTeamPlayersMatchDetails.indexOf(currentBatsmen.get(1)))
		{
			higherIndex=1;
		}
		batsmanChooseComboBox.setValue(battingTeamPlayersMatchDetails.get(
				battingTeamPlayersMatchDetails.indexOf(currentBatsmen.get(higherIndex))+1	
			));
	}

	@FXML
    private void handleNextOverClick(ActionEvent event) {
    	
    }

	@FXML
	private void handleBatsmanChooseComboBoxOKClick(ActionEvent e) {
		
		MatchDetails b = batsmanChooseComboBox.getValue();
		
		if(b!=null)
		{
			if(b.isBatted())
			{
				MiscUtilities.log("Batsman has already batted or is batting");
			}
			else
			{
				batsmanChooseComboBox.setDisable(true);
				batsmanChooseOKButton.setDisable(true);
				
				if (isCurrentBowlerChosen) {
					nextBallButton.setDisable(false);
					nextOverButton.setDisable(false);
				}
				isNextBatsmanChosen = true;
				currentBatsmen.set(0, b);
				currentBatsmen.get(0).setBatted(true);
				currentBatsmen.get(0).setBattingStatus("n.o.");
				
				if(battingTeamPlayersMatchDetails.indexOf(currentBatsmen.get(0))!=(battingTeamMatchDetails.getWicketsLost()+1))
				{
					battingTeamPlayersMatchDetails.remove(currentBatsmen.get(0));
					battingTeamPlayersMatchDetails.add(battingTeamMatchDetails.getWicketsLost()+1, currentBatsmen.get(0));
				}
				
				renewCurrentBatsmenBindings();
			}
		}
		else
		{
			MiscUtilities.log("Batsman chosen is null");
		}
	}
	
	@FXML
	private void handleBowlerChooseComboBoxOKClick(ActionEvent e) 
	{
		MatchDetails chosenBowler = bowlerChooseComboBox.getValue();
		
		if(chosenBowler!=null)
		{
			if (currentBowlers.size()!=0)
			{
				if (chosenBowler != currentBowlers.get(0))
				{
					if (chosenBowler.getBowlingBalls() < numberOfOversInMatch * 6 / 5) {
						if(currentBowlers.size()>1)
						{
							Collections.swap(currentBowlers, 0, 1);
							currentBowlers.set(0, chosenBowler);
						}
						else
						{
							currentBowlers.add(chosenBowler);
							Collections.swap(currentBowlers, 0, 1);
						}
						bowlerChooseComboBox.setDisable(true);
						bowlerChooseOKButton.setDisable(true);
						if (isNextBatsmanChosen) {
							nextBallButton.setDisable(false);
							nextOverButton.setDisable(false);
						}
						if (!playersWhoHaveBowled.contains(chosenBowler))
						{	
							playersWhoHaveBowled.add(chosenBowler);
						}
						
						isCurrentBowlerChosen = true;
						changeStrike();
						renewCurrentBatsmenBindings();
						renewCurrentBowlersBindings();
						MiscUtilities.log("Bowler Chosen: "
								+ currentBowlers.get(0).getPlayer()
										.getStyledName());
					}
					else
					{
						MiscUtilities.log("Choose a different bowler. This bowler has completed all its overs");
					}
				}
				else
				{
					MiscUtilities.log("Same bowler selected, not valid");
				}
			}
			else
			{
				currentBowlers.add(chosenBowler);
				bowlerChooseComboBox.setDisable(true);
				bowlerChooseOKButton.setDisable(true);
				if (isNextBatsmanChosen) {
					nextBallButton.setDisable(false);
					nextOverButton.setDisable(false);
				}
				if (!playersWhoHaveBowled.contains(chosenBowler))
					playersWhoHaveBowled.add(chosenBowler);
				isCurrentBowlerChosen = true;
				renewCurrentBowlersBindings();
				MiscUtilities.log("Bowler Chosen: "
						+ currentBowlers.get(0).getPlayer()
								.getStyledName());
			}
		}
		else
		{
			MiscUtilities.log("Bowler not chosen");
		}
	}
	
	public void setUpScreen()
	{		
		//Set batting and bowling team match details from match instance
		for(TeamMatchDetails tmd: match.getTeamsMatchDetails())	{
			if(tmd.isBatting()) {
				battingTeamMatchDetails = tmd;
			}
			else if(tmd.isBowling()) {
				bowlingTeamMatchDetails = tmd;
			}
		}
		
		setupBattingCard();
		setupBowlingCard();
		setupSummaryTab();
		renewCurrentBatsmenBindings();
		
		engine = new Engine();
		
		nextBallButton.setDisable(true);
		nextOverButton.setDisable(true);
		
		bowlerChooseComboBox.setDisable(false);
		bowlerChooseOKButton.setDisable(false);
		batsmanChooseComboBox.setDisable(true);
		batsmanChooseOKButton.setDisable(true);
		
		bowlerChooseComboBox.setCellFactory(new Callback<ListView<MatchDetails>, ListCell<MatchDetails>>() {
			@Override
			public ListCell<MatchDetails> call(ListView<MatchDetails> param) {
				return new ListCell<MatchDetails>()
				{
					@Override
					protected void updateItem(MatchDetails item, boolean empty) {
						super.updateItem(item, empty);
						if(!empty&&item!=null)
						{
							setText(item.getPlayer().getStyledName());
						}
					}
				};
			}
		});
		batsmanChooseComboBox.setCellFactory(new Callback<ListView<MatchDetails>, ListCell<MatchDetails>>() {
			
			@Override
			public ListCell<MatchDetails> call(ListView<MatchDetails> param) {
				return new ListCell<MatchDetails>() {
					@Override
					protected void updateItem(MatchDetails item, boolean empty) {
						super.updateItem(item, empty);
						if(item!=null&&!empty)
						{
							setText(item.getPlayer().getStyledName());
						}
					}
				};
			}
		});

		bowlerChooseComboBox.setConverter(new StringConverter<MatchDetails>() {			
			@Override
			public String toString(MatchDetails object) {
				if(object!=null)
				{
					return object.getPlayer().getStyledName();
				}
				else
				{
					return null;
				}
			}
			
			@Override
			public MatchDetails fromString(String string) {
				return null;
			}
		});
		batsmanChooseComboBox.setConverter(new StringConverter<MatchDetails>() {			
			@Override
			public String toString(MatchDetails object) {
				return object.getPlayer().getStyledName();
			}
			
			@Override
			public MatchDetails fromString(String string) {
				return null;
			}
		});
		
		bowlerChooseComboBox.setItems(bowlingTeamPlayersMatchDetails);
		batsmanChooseComboBox.setItems(battingTeamPlayersMatchDetails);
		
		currentBatsmen.get(0).setBatted(true);
		currentBatsmen.get(1).setBatted(true);
		numberOfOversInMatch = 20;
		isNextBatsmanChosen = true;
		isCurrentBowlerChosen = false;
		
		channelLogoImageView.setImage(ImageRepository.getInstance().getUiImages().get("SonySixLogo"));
		matchTitleLabel.setText("Match #__: " + match.getTeams().get(0).getShortName() + " vs. " + match.getTeams().get(1).getShortName() + " live from " + match.getStadium().getName());
		battingCardTitle.setText(battingTeamMatchDetails.getTeam().getFullName());
	}
	
	private void renewCurrentBatsmenBindings()
	{
		//UnBindShit
		agressionSlider1.valueProperty().unbindBidirectional(currentBatsmen.get(1).getPlayer().getGeneralDetails().battingAgressionLevelProperty());
		agressionSlider2.valueProperty().unbindBidirectional(currentBatsmen.get(0).getPlayer().getGeneralDetails().battingAgressionLevelProperty());
		agressionSlider1.valueProperty().unbindBidirectional(currentBatsmen.get(0).getPlayer().getGeneralDetails().battingAgressionLevelProperty());
		agressionSlider2.valueProperty().unbindBidirectional(currentBatsmen.get(1).getPlayer().getGeneralDetails().battingAgressionLevelProperty());
		
		settledProgressBar1.progressProperty().unbind();
		settledProgressBar2.progressProperty().unbind();
		
		//BindShit
		agressionSlider1.valueProperty().bindBidirectional(currentBatsmen.get(0).getPlayer().getGeneralDetails().battingAgressionLevelProperty());
		agressionSlider2.valueProperty().bindBidirectional(currentBatsmen.get(1).getPlayer().getGeneralDetails().battingAgressionLevelProperty());
		
		settledProgressBar1.progressProperty().bind(currentBatsmen.get(0).getPlayer().getGeneralDetails().battingSettlementProperty());
		settledProgressBar2.progressProperty().bind(currentBatsmen.get(1).getPlayer().getGeneralDetails().battingSettlementProperty());
		
		batsmanImage1.setImage(ImageRepository.getInstance().getPlayerImage(currentBatsmen.get(0).getPlayer()));
		batsmanImage2.setImage(ImageRepository.getInstance().getPlayerImage(currentBatsmen.get(1).getPlayer()));
		
		batsmanPlayerTypeImageView1.setImage(ImageRepository.getInstance().getUiImages().get(currentBatsmen.get(0).getPlayer().getGeneralDetails().getPlayerType().toString()));
		batsmanPlayerTypeImageView2.setImage(ImageRepository.getInstance().getUiImages().get(currentBatsmen.get(0).getPlayer().getGeneralDetails().getPlayerType().toString()));
		
		batsmanTypeLabel1.setText(currentBatsmen.get(0).getPlayer().getGeneralDetails().getBatsmanType().toString());
		batsmanTypeLabel2.setText(currentBatsmen.get(1).getPlayer().getGeneralDetails().getBatsmanType().toString());
		
		batsmanNameLabel1.setText(currentBatsmen.get(0).getPlayer().getFullName());
		batsmanNameLabel2.setText(currentBatsmen.get(1).getPlayer().getFullName());
		
		batsmanAbilityLabel1.setText(Integer.toString(currentBatsmen.get(0).getPlayer().getGeneralDetails().getBattingAbility()));
		batsmanAbilityLabel2.setText(Integer.toString(currentBatsmen.get(1).getPlayer().getGeneralDetails().getBattingAbility()));
		
		batsmanFormLabel1.setText(Integer.toString(currentBatsmen.get(0).getPlayer().getGeneralDetails().getBattingForm()));
		batsmanFormLabel2.setText(Integer.toString(currentBatsmen.get(1).getPlayer().getGeneralDetails().getBattingForm()));
		
    	batsmanScoreLabel1.setText(currentBatsmen.get(0).getBattingRuns() + " (" + currentBatsmen.get(0).getBattingBalls()+ ")*");
    	batsmanScoreLabel2.setText(currentBatsmen.get(1).getBattingRuns() + " (" + currentBatsmen.get(1).getBattingBalls()+ ")");

    	/*bowlerNameLabel, scorelineLabel, fow1, fow2, fow3, fow4, fow5, fow6, fow7, fow8, fow9, fow10,
    	batsmanNameLabel2, currentOverLabel, batsmanNameLabel1, relativeTargetLabel, rrrLabel, battingCardTitle, 
    	batsmanScoreLabel1, batsmanScoreLabel2, matchTitleLabel, belowBattingCardLabel, bowlerFiguresLabel, 
    	batsmanFormLabel1, batsmanFormLabel2, crrLabel, scoreLabel, last18DeliveriesLabel, bowlingFormLabel, 
    	bowlingAbilityLabel, batsmanAbilityLabel1, batsmanAbilityLabel2, projectedOrTargetLabel;*/
	}
	private void renewCurrentBowlersBindings()
	{
		//Remove bindings
		bowlerAgressionSlider.valueProperty().unbindBidirectional(currentBowlers.get(0).getPlayer().getGeneralDetails().bowlingAgressionLevelProperty());
		if(currentBowlers.size()>1)bowlerAgressionSlider.valueProperty().unbindBidirectional(currentBowlers.get(1).getPlayer().getGeneralDetails().bowlingAgressionLevelProperty());
		bowlerFiguresLabel.textProperty().unbind();		

		//Bind and set
		bowlerAgressionSlider.valueProperty().bindBidirectional(currentBowlers.get(0).getPlayer().getGeneralDetails().bowlingAgressionLevelProperty());
		bowlerFiguresLabel.textProperty().bind(currentBowlers.get(0).bowlingFigureProperty());
		
		bowlerNameLabel.setText(currentBowlers.get(0).getPlayer().getFullName());
		bowlingAbilityLabel.setText(Integer.toString(currentBowlers.get(0).getPlayer().getGeneralDetails().getBowlingAbility()));
		bowlingFormLabel.setText(Integer.toString(currentBowlers.get(0).getPlayer().getGeneralDetails().getBowlingForm()));
		bowlerPlayerTypeImageView.setImage(ImageRepository.getInstance().getUiImages().get(
				currentBowlers.get(0).getPlayer().getGeneralDetails().getPlayerType().toString()));
		bowlerTypeLabel.setText(currentBowlers.get(0).getPlayer().getGeneralDetails().getBowlerType().toString());
		bowlerImage.setImage(ImageRepository.getInstance().getPlayerImage(currentBowlers.get(0).getPlayer()));
	}

	private void setupBattingCard()
	{
		battingTeamPlayersMatchDetails = FXCollections.observableArrayList();
		
		int indexOfMatchDetail=0;
		for(Player p:battingTeamMatchDetails.getPlayingEleven())
		{
			indexOfMatchDetail = p.getTournamentDetails().getMatchDetailsList().size()-1;
			battingTeamPlayersMatchDetails.add(p.getTournamentDetails().getMatchDetailsList().get(indexOfMatchDetail));
		}
		
		battingPositionColumn.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getPlayer().
				getCurrentTeam().getCurrentPlayingEleven().indexOf(param.getValue().getPlayer())+1));
		batsmanNameColumn.setCellValueFactory(p->p.getValue().getPlayer().styledNameProperty());
		batsmanPlayerTypeColumn.setCellValueFactory(p -> p.getValue().getPlayer().getGeneralDetails().playerTypeProperty());
		batsmanBattingTypeColumn.setCellValueFactory(p-> p.getValue().getPlayer().getGeneralDetails().batsmanTypeProperty());
		batsmanStatusColumn.setCellValueFactory(p->p.getValue().battingStatusProperty());
		battingRunsColumn.setCellValueFactory(p -> p.getValue().battingRunsProperty());
		battingBallsColumn.setCellValueFactory(p->p.getValue().battingBallsProperty());
		battingDotsColumn.setCellValueFactory(p->p.getValue().battingDotsProperty());
		battingFoursColumn.setCellValueFactory(p->p.getValue().battingFoursProperty());
		battingSixesColumn.setCellValueFactory(p->p.getValue().battingSixesProperty());
		battingSRColumn.setCellValueFactory(p->p.getValue().battingStrikeRateProperty());
		battingPointsColumn.setCellValueFactory(p->p.getValue().battingPointsProperty());
		
		battingCardTableView.setItems(battingTeamPlayersMatchDetails);
	}
	
	private void setupBowlingCard()
	{
		bowlingTeamPlayersMatchDetails = FXCollections.observableArrayList();
		playersWhoHaveBowled=FXCollections.observableArrayList();
	
		int indexOfMatchDetail=0;
		for(Player p:bowlingTeamMatchDetails.getPlayingEleven())
		{
			indexOfMatchDetail = p.getTournamentDetails().getMatchDetailsList().size()-1;
			bowlingTeamPlayersMatchDetails.add(p.getTournamentDetails().getMatchDetailsList().get(indexOfMatchDetail));
		}

		bowlerNameColumn.setCellValueFactory(p->p.getValue().getPlayer().styledNameProperty());
		bowlerPlayerTypeColumn.setCellValueFactory(p->p.getValue().getPlayer().getGeneralDetails().playerTypeProperty());
		bowlerBowlingTypeColumn.setCellValueFactory(p->p.getValue().getPlayer().getGeneralDetails().bowlerTypeProperty());
		bowlingOversColumn.setCellValueFactory(p->p.getValue().bowlingOversProperty());
		bowlingBallsColumn.setCellValueFactory(p->p.getValue().bowlingBallsProperty());
		bowlingDotsColumn.setCellValueFactory(p->p.getValue().bowlingDotsProperty());
		bowlingMaidensColumn.setCellValueFactory(p->p.getValue().bowlingMaidensProperty());
		bowlingRunsColumn.setCellValueFactory(p->p.getValue().bowlingRunsProperty());
		bowlingWicketsColumn.setCellValueFactory(p->p.getValue().bowlingWicketsProperty());
		bowlingFoursColumn.setCellValueFactory(p->p.getValue().bowlingFoursProperty());
		bowlingSixesColumn.setCellValueFactory(p->p.getValue().bowlingSixesProperty());
		bowlingSRColumn.setCellValueFactory(p->p.getValue().bowlingStrikeRateProperty());
		bowlingEconomyColumn.setCellValueFactory(p->p.getValue().bowlingEconomyProperty());
		bowlingAverageColumn.setCellValueFactory(p->p.getValue().bowlingAverageProperty());
		bowlingPointsColumn.setCellValueFactory(p->p.getValue().bowlingPointsProperty());
		
		bowlingCardTableView.setItems(playersWhoHaveBowled);
	}
		
	private void setupSummaryTab()
	{
		_batsmanNameColumn.setCellValueFactory(p->p.getValue().getPlayer().styledNameProperty());
		_batsmanTypeColumn.setCellValueFactory(p->p.getValue().getPlayer().getGeneralDetails().batsmanTypeProperty());
		_battingRunsColumn.setCellValueFactory(p -> p.getValue().battingRunsProperty());
		_battingBallsColumn.setCellValueFactory(p->p.getValue().battingBallsProperty());
		_battingDotsColumn.setCellValueFactory(p->p.getValue().battingDotsProperty());
		_battingFoursColumn.setCellValueFactory(p->p.getValue().battingFoursProperty());
		_battingSixesColumn.setCellValueFactory(p->p.getValue().battingSixesProperty());
		_battingSRColumn.setCellValueFactory(p->p.getValue().battingStrikeRateProperty());
		_battingPointsColumn.setCellValueFactory(p->p.getValue().battingPointsProperty());
		
		_bowlerNameColumn.setCellValueFactory(p->p.getValue().getPlayer().styledNameProperty());
		_bowlerTypeColumn.setCellValueFactory(p->p.getValue().getPlayer().getGeneralDetails().bowlerTypeProperty());
		_bowlingOversColumn.setCellValueFactory(p->p.getValue().bowlingOversProperty());
		_bowlingBallsColumn.setCellValueFactory(p->p.getValue().bowlingBallsProperty());
		_bowlingDotsColumn.setCellValueFactory(p->p.getValue().bowlingDotsProperty());
		_bowlingMaidensColumn.setCellValueFactory(p->p.getValue().bowlingMaidensProperty());
		_bowlingRunsColumn.setCellValueFactory(p->p.getValue().bowlingRunsProperty());
		_bowlingWicketsColumn.setCellValueFactory(p->p.getValue().bowlingWicketsProperty());
		_bowlingFoursColumn.setCellValueFactory(p->p.getValue().bowlingFoursProperty());
		_bowlingSixesColumn.setCellValueFactory(p->p.getValue().bowlingSixesProperty());
		_bowlingSRColumn.setCellValueFactory(p->p.getValue().bowlingStrikeRateProperty());
		_bowlingEconomyColumn.setCellValueFactory(p->p.getValue().bowlingEconomyProperty());
		_bowlingAverageColumn.setCellValueFactory(p->p.getValue().bowlingAverageProperty());
		_bowlingPointsColumn.setCellValueFactory(p->p.getValue().bowlingPointsProperty());
		
		currentBatsmen = FXCollections.observableArrayList();
		currentBowlers = FXCollections.observableArrayList();
		
		for(int i=0; i<2;i++)
		{
			currentBatsmen.add(battingTeamPlayersMatchDetails.get(i));
			currentBatsmen.get(i).setBattingStatus("n.o.");
		}
		
		twoBatsmenTableView.setItems(currentBatsmen);
		twoBowlersTableView.setItems(currentBowlers);
	}

	@FXML
	private void handleBackToMenuClick(ActionEvent e) {
		if(!false)
		{
			masterController.changeScreenTo("");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	private void changeStrike()
	{
		Collections.swap(currentBatsmen, 0, 1);
	}
	
	@Override
	public void setMasterController(MasterController masterController) {
		this.masterController= masterController;
	}

	@Override
	public String getScreenName() {
		return this.screenName;
	}

	@Override
	public void setScreenName(String name) {
		this.screenName= name;
	}
	
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public int getUserTeamIndex() {
		return userTeamIndex;
	}

	public void setUserTeamIndex(int userTeamIndex) {
		this.userTeamIndex = userTeamIndex;
	}
}