package controller;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.tournament.Tournament;
import service.dao.impl.TournamentDAO;
import util.MiscUtilities;
import util.database.HibernateUtils;
import util.database.TemporaryHelper;

public class MasterController extends StackPane
{
	private Stage primaryStage;
	private HashMap<String, Node> screens;
	private HashMap<String, IController> controllers;
	private Node previousScreen, currentScreen;
	private IController previousController, currentController;
	private TournamentDAO tournamentDAO;
	private Tournament tournament;
	
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}

	public void closeApp()
	{
		this.getPrimaryStage().getOnCloseRequest().handle(null);
	}
	
	public void setUpStage(Stage primaryStage)
	{		
		this.primaryStage = primaryStage;
		tournamentDAO = new TournamentDAO();
		
		//----------REMEMBER TO CHANGE THIS-------------
		//tournament = tournamentDAO.getFirst();
		tournament = TemporaryHelper.createRandomData();
		
		screens = new HashMap<String, Node>();
		controllers = new HashMap<String, IController>();
		setPreviousScreen(null);
		currentScreen=null;
		setPreviousController(null);
		currentController = null;
		Parent root = (Parent)this;
		Scene scene = new Scene(root, 1024, 640);
		
		try
		{	
			//LOADING CSS FILE FOR SCENE
			try
			{
				//All of these work in eclipse
				
				//works on deployment as well
				//scene.getStylesheets().add("file:resources/ui/styles/IPLTheme.css");
				
				//works on deployment as well
				//scene.getStylesheets().add("/fxml/styles/IPLTheme.css");
				
				//works only on eclipse
				//scene.getStylesheets().add("file:src/fxml/styles/IPLTheme.css");
				
				//For getClass().getResource("..")
				// (.) means the directory where the class file is located
				// (..) means one level up from the directory where class file is located
				// (../..) one more level up
				//(../../..) one more level up
				// Cannot go upper than the src using this method
				// works in eclipse, does not work in a deployed jar file for some reason
				
			}
			catch (Exception e)
			{
				MiscUtilities.log("Error: Could not load the stylesheet\r\n" + e.toString());
			}
			
			this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					MiscUtilities.log("Closing application");
					if(HibernateUtils.getSessionFactory()!=null)
					{
						MiscUtilities.log("Closing hibernate sessionfactory");
						HibernateUtils.getSessionFactory().close();
					}
					Platform.exit();
				}
			});
			
			this.primaryStage.setScene(scene);
			this.primaryStage.setTitle("IPL Manager");
			//this.primaryStage.getIcons().add(new Image("file:resources/images/adressbook.png"));
			this.primaryStage.show();
			
			loadScreen("StartMenu","/view/StartMenu.fxml");
			loadScreen("Settings","/view/Settings.fxml");
			loadScreen("TeamSelection", "/view/TeamSelection.fxml");
			loadScreen("GamePortal","/view/GamePortal.fxml");
			loadScreen("PlayingElevenSelection", "/view/PlayingElevenSelection.fxml");
			loadScreen("TossScreen", "/view/TossScreen.fxml");
			loadScreen("MatchScreen", "/view/MatchScreen.fxml");
			changeScreenTo("StartMenu");
		}
		
		catch(Exception e)
		{
			MiscUtilities.log("Error encountered while setting up stage. In setUpStage() function of MasterController.java: " + e.getMessage());
		}
	}
	
	public void loadScreen(String name, String url)
	{
		FXMLLoader loader;
		try
		{
			loader = new FXMLLoader(getClass().getResource(url));
			Parent screen = (Parent)loader.load();
			IController ic = loader.getController();
			ic.setMasterController(this);
			ic.setScreenName(name);
			addController(name, ic);
			addScreen(name, screen);
		}
		catch (Exception e)
		{
			MiscUtilities.log("ERROR ENCOUNTERED WHILE LOADING SCREENS. In loadScreen() function of MasterController.java: "
								+ e.toString()+ " : " +  e.getMessage());
		}
	}
	
	public void unloadScreen(String name)
	{
		if(getScreen(name)!=currentScreen)screens.remove(name);
		if(getController(name)!=currentController)controllers.remove(name);
	}
		
	public void changeScreenTo(String name)
	{
		try
		{
			if(!this.getChildren().isEmpty())
			{
				setPreviousScreen(getCurrentScreen());
				setPreviousController(getCurrentController());
				this.getChildren().remove(0);
			}
			setCurrentScreen(getScreen(name));
			setCurrentController(getController(name));
			this.getChildren().add(currentScreen);
		}
		catch(Exception e)
		{
			MiscUtilities.log("ERROR ENCOUNTERED WHILE SWITCHING SCREENS. In changeScreenTo(name) function of StageController.java: "
								+ e.toString() + " : " + e.getMessage());
		}
	}
	
	private Node getScreen(String name)
	{
		return screens.get(name);
	}
	private void addScreen(String name, Node screen)
	{
		this.screens.put(name, screen);
	}	
	
	public IController getController(String name)
	{
		return controllers.get(name);
	}	
	private void addController(String name, IController controller)
	{
		this.controllers.put(name, controller);
	}

	
	public Node getPreviousScreen() {
		return previousScreen;
	}

	private void setPreviousScreen(Node previousScreen) {
		this.previousScreen = previousScreen;
	}
	public IController getPreviousController() {
		return previousController;
	}
	private void setPreviousController(IController previousController) {
		this.previousController = previousController;
	}
	
	public Node getCurrentScreen() {
		return currentScreen;
	}
	private void setCurrentScreen(Node currentScreen) {
		this.currentScreen = currentScreen;
	}
	public IController getCurrentController() {
		return currentController;
	}
	private void setCurrentController(IController currentController) {
		this.currentController = currentController;
	}

	public TournamentDAO getTournamentDAO() {
		return tournamentDAO;
	}
	public void setTournamentDAO(TournamentDAO tournamentDAO) {
		this.tournamentDAO = tournamentDAO;
	}
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
}
