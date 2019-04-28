

import java.util.List;
import java.util.*;
import java.awt.*;
		
public class GameModeTimed extends GameMode
{

	private int score;
	private int highscore;
	private List<Circle> circles; 
	
	public GameModeTimed() 
	{
		score = 0;
		highscore = 0;
		circles = new ArrayList<>();
	}
	
	

	//TESTING OUT THE TIMER
	class App extends TimerTask 
	{

	    int countdown = 100;

	    public void run() 
	    {		   
		   System.out.println(countdown);
		   countdown--;
		}

	}

	//@Override
	public int getHighscore()
	{
		return highscore;
	}
	
	//@Override
	public int getScore()
	{
		return score;
	}
	
	//@Override
	private void updateHighscore()
	{
		if(score > highscore)
			highscore = score;
	}
	
	
	public static void main(String[] args) {
		GameModeTimed x = new GameModeTimed();
        Timer timer = new Timer();
        timer.schedule(x.new App(), 0, 1000);
    }
	
}
