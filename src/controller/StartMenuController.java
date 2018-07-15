package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class StartMenuController implements Initializable, IController
{
	private MasterController masterController;
	private String screenName;

    @FXML
    private void handlePlayClick(ActionEvent event)
    {
    	if(masterController.getTournament().getUserTeam()==null)masterController.changeScreenTo("TeamSelection");
    	else
    	{
    		GamePortalController gpc = (GamePortalController) masterController.getController("GamePortal");
    		gpc.setupScreen();
    		masterController.changeScreenTo("GamePortal");
    	}
    }

    @FXML
    private void handleSettingsClick(ActionEvent event)
    {
    	masterController.changeScreenTo("Settings");
    }

    @FXML
    private void handleExitClick(ActionEvent event)
    {
    	this.masterController.closeApp();
    }

	@Override
	public void setMasterController(MasterController masterController) 
	{
		this.masterController = masterController;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
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
}