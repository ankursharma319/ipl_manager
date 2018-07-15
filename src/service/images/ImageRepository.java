package service.images;

import java.util.HashMap;
import java.util.List;

import javafx.scene.image.Image;
import model.player.Player;
import model.player.PlayerType;
import model.team.Team;

public class ImageRepository
{
	private static final ImageRepository instance = new ImageRepository();
	private HashMap<Player, Image> playerImages;
	private HashMap<Team, Image> teamLogos;
	private HashMap<String, Image> uiImages;
	private final String imagesDir="file:resources/images/";
	
	public static ImageRepository getInstance()
	{
		return instance;
	}
	
	private ImageRepository()
	{
		playerImages = new HashMap<Player, Image>();
		teamLogos = new HashMap<Team, Image>();
		uiImages = new HashMap<String, Image>();
	}
	
	public void loadUIImages()
	{
		for(PlayerType p: PlayerType.values())
		{
			uiImages.put(p.toString(), new Image(imagesDir+"ui/" + p.toString() + "-symbol.png"));
		}
		uiImages.put("GenericPlayer", new Image(imagesDir+"ui/GenericPlayer.png"));
		uiImages.put("SonySixLogo", new Image(imagesDir+"ui/SonySix-logo.png"));
	}
	
	public void loadPlayerImages(List<Player> listOfPlayers)
	{
		
	}
	
	public void loadAllPlayersImages()
	{
		
	}
	
	public Image getPlayerImage(Player p)
	{
		if(playerImages.containsKey(p))
		{
			return playerImages.get(p);
		}
		else
		{
			return uiImages.get("GenericPlayer");
		}
	}
	
	public HashMap<Player, Image> getPlayerImages() {
		return playerImages;
	}

	public HashMap<Team, Image> getTeamLogos() {
		return teamLogos;
	}

	public HashMap<String, Image> getUiImages() {
		return uiImages;
	}
}
