

import java.util.Timer;
import java.util.TimerTask;
		
		
public class GameModeTimer extends GameMode
{

	public GameModeTimer() 
	{
		// TODO Auto-generated constructor stub
		 
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

	public static void main(String[] args) {
		GameModeTimer x = new GameModeTimer();
        Timer timer = new Timer();
        timer.schedule(x.new App(), 0, 1000);
    }
	
}
