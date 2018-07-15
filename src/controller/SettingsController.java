package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

public class SettingsController implements Initializable, IController
{
    @FXML
    private CheckBox indicatePlayerAttributesCheckBox, showPlayerAttributesCheckBox;

	private MasterController masterController;
	private String screenName;
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
	}
	
	@FXML
	private void handleDiscardChangesClick(ActionEvent ev)
	{
		this.masterController.changeScreenTo(this.masterController.getPreviousController().getScreenName());
	}
	
	@FXML
	private void handleSaveChangesClick(ActionEvent e)
	{
		this.masterController.changeScreenTo(this.masterController.getPreviousController().getScreenName());
	}
}
