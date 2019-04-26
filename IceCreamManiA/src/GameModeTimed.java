

import java.util.Timer;
import java.util.TimerTask;
		
		
public class GameModeTimed extends GameMode
{

	public GameModeTimed() 
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
		GameModeTimed x = new GameModeTimed();
        Timer timer = new Timer();
        timer.schedule(x.new App(), 0, 1000);
    }
	
}
