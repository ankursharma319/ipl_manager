package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import service.images.ImageRepository;
import util.MiscUtilities;
import controller.MasterController;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			MasterController mc = new MasterController();
			mc.setUpStage(primaryStage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		MiscUtilities.log("---------------------Application IPL Manager Started-----------------------------");
		
		//ImageRepository.getInstance().loadUIImages();
		
//		String t = "Last 18 Deliveries: 0 4 wd 4 W 0 2 | 5 0 0 0 0 1| 0 1 2 3 0 2 | 4 5 ";
//		MiscUtilities.log("t: " + t);
//		String t2 = t.substring(20);
//		t2.trim();
//		if(t2.startsWith("|"))t2 = t2.substring(2);
//		t2 = t2+"1"+" ";
//		MiscUtilities.log("t2: " + t2);
//		int i = t2.indexOf(' ');
//		String correctVersion = t2.substring(i);
//		correctVersion = "Last 18 Deliveries:" + correctVersion;
//		MiscUtilities.log(correctVersion);
		
		//HibernateUtils.init();
		launch(args);
		
		//Engine e = new Engine();
		//e.engineTest();
		
		//System.exit(0);
	}
}

//remove unnecessary fetchmode.subselect annotations to increase speed
//check if the battingteam is user or bowlingteam is user and modfiy the matchscreen appropriately
//add a label to match screens which shows errors and things like: same bowler selected, or batsman has already batted
//reduce six percentage and increase four percentage
//automatic bowlingFigureSetter
//remove initial label text to make it look nice
//implement functionality to move to next innings
//implement settledness functionality
