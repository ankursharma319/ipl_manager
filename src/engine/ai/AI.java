package engine.ai;

import java.util.List;

import javafx.collections.FXCollections;
import model.match.Match;
import model.player.Player;

public class AI
{
	public static String chooseBatOrBowl(Match m)
	{
		return "bowl";
	}
	
	public static List<Player> choosePlayingEleven(List<Player> wholeSquad)
	{
		List<Player> pe = FXCollections.observableArrayList();
		for(int i=0;i<11;i++)
		{
			pe.add(wholeSquad.get(i));
		}
		return pe;
	}
}
