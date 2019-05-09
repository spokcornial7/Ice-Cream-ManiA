

import java.util.List;

import javax.swing.JLabel;
import javax.swing.Timer;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
		
public class GameModeTimed extends GameMode
{
	// Game instance variables
	private int score;
	private int highscore;
	private boolean done;
	private IceCream icecream;
	
	// Diagram instance variables and constants
	private Queue<Scoop> scoopQueue;
	public static final int SCOOP_X = 395;
	public static final int SCOOP5_Y = 20;
	public static final int SCOOP4_Y = 80;
	public static final int SCOOP3_Y = 140;
	public static final int SCOOP2_Y = 200;
	public static final int SCOOP1_Y = 260;
	public static final int DIA_SHIFT_AMT = 60;
	
	
	// Timer instance variables
	private int i;
	private JLabel time;
	private Timer timer;
	
	
	public GameModeTimed() 
	{
		super();
		score = 0;
		highscore = 0;
		scoopQueue = new LinkedList<>();
		done = false;
		icecream = super.getIceCream();
		createDiagram();
	}

	@Override
	public int getHighScore()
	{
		return highscore;
	}
	
	@Override
	public int getPoints()
	{
		return score;
	}
	
	private void setHighScore()
	{
		if(score > highscore)
			highscore = score;
	}
	
	@Override
	public boolean isGameOver()
	{
		return done;
	}
	
	
	@Override
	public boolean updateScore()
	{
		if(correctFlavor())
		{
			score++;
			//setHighScore();
		}
		else if(touchedBomb())
			done = true;
		else
		{
			if(score <= 3)
			{
				icecream.clearScoops();
				score = 0;
			}
			else
			{
				icecream.removeScoops();
				score -= 3;
			}
		}
		return true;
	}

	private boolean correctFlavor()
	{
		
		if(!icecream.getScoops().isEmpty())
		{
			Scoop checkScoop = scoopQueue.peek();
			if(checkScoop.getFlavor() == icecream.getTopScoop().getFlavor())
			{
				updateDiagram();
				return true;
			}
		}
		return false;
	}
	
	private boolean touchedBomb()
	{
		if(icecream.getScoops().isEmpty())
			return false;
		return icecream.getTopScoop().getFlavor() == 5;
	}
	
	
	// DIAGRAM
	public void drawDiagram(Graphics g)
	{
		Graphics2D gr2 = (Graphics2D) g;
		for(Scoop s : scoopQueue)
		{
			s.draw(gr2);
		}
	}
	
	private void updateDiagram()
	{
		scoopQueue.remove();
		for(Scoop s : scoopQueue)
		{
			s.dropDown(DIA_SHIFT_AMT);
		}
		addRandomScoop();
	}
	
	private void createDiagram()
	{
		int rand1 = (int) (Math.random()*4);
		int rand2 = (int) (Math.random()*4);
		int rand3 = (int) (Math.random()*4);
		int rand4 = (int) (Math.random()*4);
		int rand5 = (int) (Math.random()*4);
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP1_Y, rand1));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP2_Y, rand2));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP3_Y, rand3));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP4_Y, rand4));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP5_Y, rand5));
	}
	
	private void addRandomScoop()
	{
		int rand = (int) (Math.random()*4);
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP5_Y, rand));
	}

	
	
	// TIMER
	public JLabel drawTimer()
	{
		i = 60;
		time = new JLabel();
		time.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		time.setBounds(175, 90, 100, 50);
		timer = new Timer(1000, new timerListener());
	    timer.start();
		return time;
	}

    class timerListener implements ActionListener 
    {
    	  public void actionPerformed(ActionEvent e) 
    	  {
    		  i--;
    		  time.setText(String.valueOf(i));
    		  if(i == 0 || done)
    		  {
    	        	timer.stop();
    	        	done = true;
    		  }
    	  }
    }
}
