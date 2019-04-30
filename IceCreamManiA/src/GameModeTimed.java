

import java.util.List;
import java.util.Timer;
import java.util.*;
import java.awt.*;
import javax.swing.*;
		
public class GameModeTimed extends GameMode
{

	private int score;
	private int highscore;
	private PriorityQueue<Scoop> scoopQueue; 
	private boolean done;
	
	public static final int scoopX = 200;
	public static final int scoop5Y = 20;
	public static final int scoop4Y = 40;
	public static final int scoop3Y = 60;
	public static final int scoop2Y = 80;
	public static final int scoop1Y = 100;
	
	
	public GameModeTimed() 
	{
		score = 0;
		highscore = 0;
		scoopQueue = new PriorityQueue<>();
		done = false;
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
	
	private void updateScore()
	{
		//if(super.addScoop())
			score++;
		//if wrong scoop added
			score=-3;
	}
	
	private void createDiagram()
	{
		createRandomizedScoops();
		while(!scoopQueue.isEmpty())
		{
			scoopQueue.remove().draw();
		}
	}
	
	private void createRandomizedScoops()
	{
		int rand1 = (int) (Math.random()*8);
		int rand2 = (int) (Math.random()*8);
		int rand3 = (int) (Math.random()*8);
		int rand4 = (int) (Math.random()*8);
		int rand5 = (int) (Math.random()*8);
		scoopQueue.add(new Scoop(scoopX, scoop1Y, rand1));
		scoopQueue.add(new Scoop(scoopX, scoop2Y, rand2));
		scoopQueue.add(new Scoop(scoopX, scoop3Y, rand3));
		scoopQueue.add(new Scoop(scoopX, scoop4Y, rand4));
		scoopQueue.add(new Scoop(scoopX, scoop5Y, rand5));
	}
	
	
	
	//TESTING OUT THE TIMER
	public void drawTimer()
	{
		Timer timer = new Timer();
        timer.schedule(new Countdown(), 0, 1000);
	}

	class Countdown extends TimerTask 
	{
		int countdown = 100;
		public void run()
		{
			//add(new JLabel(countdown.toString()));
			countdown--;
		}
	}
	
	
	
	
	public static void main(String[] args) {
		GameModeTimed x = new GameModeTimed();
		x.drawTimer();
    }
	
}
