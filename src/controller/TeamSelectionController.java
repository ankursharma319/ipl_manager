package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.team.Team;
import util.MiscUtilities;

public class TeamSelectionController implements Initializable, IController {

	private MasterController masterController;
	private String screenName;
	
	@Override
	public void setMasterController(MasterController masterController)
	{
		this.masterController=masterController;
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

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
	}
	
	@FXML
	private void handleTeamClick(ActionEvent e)
	{
		Button b = (Button)e.getSource();
		String teamName = b.getText();
		MiscUtilities.log("Click Detected   |    Button Text: " + teamName);
		List<Team> tl = masterController.getTournament().getTeams();
		
		for(Team t:tl)
		{
			if(teamName.equals(t.getFullName()))
			{
				masterController.getTournament().setUserTeam(t);
				GamePortalController gpc = (GamePortalController) masterController.getController("GamePortal");
	    		gpc.setupScreen();
				this.masterController.changeScreenTo("GamePortal");
			}
		}
	}

	@FXML
	private void handleBackClick(ActionEvent e)
	{
		masterController.changeScreenTo(masterController.getPreviousController().getScreenName());
	}
}
