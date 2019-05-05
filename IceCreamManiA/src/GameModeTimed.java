

import java.util.List;

import javax.swing.Timer;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		super();
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
		//if(super.ifScoopAdded())
			score++;
		//if wrong scoop added
			score=-3;
	}
	
	public boolean isGameOver()
	{
		return done;
	}
	
	private void createDiagram(Graphics2D gr)
	{
		createRandomizedScoops();
		while(!scoopQueue.isEmpty())
		{
			scoopQueue.remove().draw(gr);
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
	
	private int i;
	private JLabel time;
	private JFrame f;
	private Timer timer;
	
	//TESTING OUT THE TIMER
	public void drawTimer()
	{
		i = 10;
		f = new JFrame();
		f.setSize(300, 300);
		time = new JLabel();
		f.add(time);
		f.setVisible(true);
		
        timer = new Timer(1000, new timerListener());
        timer.start();
	}

    class timerListener implements ActionListener 
    {
    	  public void actionPerformed(ActionEvent e) 
    	  {
    		  i--;
    		  time.setText(String.valueOf(i));
    		  if(i == 0)
    		  {
    	        	timer.stop();
    	        	done = true;
    		  }
    	  }
    }
	
	public static void main(String[] args) {
		GameModeTimed x = new GameModeTimed();
		x.drawTimer();
		
    }

	@Override
	int getHighScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	void setHighScore(int highScore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void addPoints(int points) {
		// TODO Auto-generated method stub
		
	}

	@Override
	int getPoints() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
