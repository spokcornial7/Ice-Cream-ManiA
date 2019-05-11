

import java.util.List;

import javax.swing.ImageIcon;
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
	private static int highscore = 0;
	private boolean done;
	private IceCream icecream;
	
	// Diagram instance variables and constants
	private Queue<Scoop> scoopQueue;
	private static final int SCOOP_X = 394;
	private static final int SCOOP5_Y = 20;
	private static final int SCOOP4_Y = 80;
	private static final int SCOOP3_Y = 140;
	private static final int SCOOP2_Y = 200;
	private static final int SCOOP1_Y = 260;
	private static final int DIA_SHIFT_AMT = 60;
	private static final int DIA_FLAVORS = 4;
	private static final int NUM_FLAVORS = 5;
	private static final int MAX_SCOOPS = 5;
	
	private static final Rectangle TIMED_BOX = new Rectangle(385, 253, 50, 55);
	private static final Color BOX_COLOR = new Color(254, 195, 207);
	
	// Timer instance variables/constants
	private int i;
	private JLabel time;
	private Timer timer;
	private static final int TIMER_START = 70;
	
	/**
	 *  Instantiates game mode timed
	 */
	public GameModeTimed(GameViewer viewer) 
	{
		super(viewer);
		score = 0;
		scoopQueue = new LinkedList<>();
		done = false;
		icecream = super.getIceCream();
		createDiagram();
		drawTimer();	
	}

	
	@Override
	public void reset()
	{
		super.reset();
		score = 0;
		scoopQueue = new LinkedList<>();
		done = false;
		icecream = super.getIceCream();
		createDiagram();
		i = TIMER_START;
		timer.restart();
	}
	
	@Override
	public void drawBorder(Graphics2D gr)
	{
		super.drawBorder(gr);
		gr.setColor(BOX_COLOR);
		gr.setStroke(new BasicStroke(4));
		gr.draw(TIMED_BOX);
	}
	
	/**
	 *  Returns the number of flavors in this game mode
	 */
	public int flavorNum()
	{
		return NUM_FLAVORS;
	}
	
	/**
	 *  Returns the high score
	 *  @return high score
	 */
	@Override
	public int getHighScore()
	{
		return highscore;
	}
	
	/**
	 *  Returns the score
	 *  @return score
	 */
	@Override
	public int getPoints()
	{
		return score;
	}
	
	/**
	 *  Sets/updates the high score
	 */
	@Override
	public void setHighScore()
	{
		if(score > highscore)
			highscore = score;
	}
	
	/**
	 *  Returns if the game is over 
	 *  @return variable holding true if game over, false if not
	 */
	@Override
	public boolean isGameOver()
	{
		return done;
	}
	
	/**
	 *  Updates and returns true if the score was updated
	 *  @return returns true if score increased
	 */
	@Override
	public boolean updateScore()
	{
		if(correctFlavor())
		{
			score++;
			if(icecream.getScoops().size() > MAX_SCOOPS)
				icecream.shiftDown();
		}
		else if(touchedBomb())
			done = true;
		else
		{
			if(score <= 3)
			{
				icecream.clearScoops();
				score = 0;
				if(icecream.getY() > 560)
					icecream.setY(560);
			}
			else
			{
				icecream.removeScoops();
				score -= 3;
				if(icecream.getTopScoop().getY() > 500)
				{
					int shiftUpVal = icecream.getTopScoop().getY() - 350;
					if(icecream.getY() - shiftUpVal < 560)
						shiftUpVal = icecream.getY() - 560;
					icecream.shiftY(shiftUpVal);
				}
			}
			
		}
		return true;
	}

	/**
	 *  Returns if the correct flavor was added 
	 *  @return true if correct flavor added, false if otherwise
	 */
	private boolean correctFlavor()
	{
		if(!icecream.isEmpty())
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
	
	/**
	 *  Returns whether the scoop added was a bomb
	 *  @return true if the scoop was a bomb
	 */
	private boolean touchedBomb()
	{
		if(icecream.isEmpty())
			return false;
		return icecream.getTopScoop().getFlavor() == 5;
	}
	
	
	/**
	 *  Draws the diagram
	 */
	public void drawDiagram(Graphics g)
	{
		Graphics2D gr2 = (Graphics2D) g;
		for(Scoop s : scoopQueue)
		{
			s.draw(gr2);
		}
	}
	
	/**
	 *  Updates the diagram by shifting down the previous diagram
	 */
	private void updateDiagram()
	{
		scoopQueue.remove();
		for(Scoop s : scoopQueue)
		{
			s.dropDown(DIA_SHIFT_AMT);
		}
		addRandomScoop();
	}
	
	/**
	 *  Creates a new diagram
	 */
	private void createDiagram()
	{
		scoopQueue.clear();
		int rand1 = (int) (Math.random() * DIA_FLAVORS);
		int rand2 = (int) (Math.random() * DIA_FLAVORS);
		int rand3 = (int) (Math.random() * DIA_FLAVORS);
		int rand4 = (int) (Math.random() * DIA_FLAVORS);
		int rand5 = (int) (Math.random() * DIA_FLAVORS);
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP1_Y, rand1));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP2_Y, rand2));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP3_Y, rand3));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP4_Y, rand4));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP5_Y, rand5));
	}
	
	/**
	 *  Adds a new random scoop for the top of the diagram
	 */
	private void addRandomScoop()
	{
		int rand = (int) (Math.random() * DIA_FLAVORS);
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP5_Y, rand));
	}

	public void increaseSpeed()
	{
		super.increaseSpeed();
	}
	
	/**
	 *  Draws a label for the countdown timer
	 *  @return the timer label
	 */
	public void drawTimer()
	{
		i = TIMER_START;
		time = new JLabel();
		time.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		time.setBounds(180, 100, 100, 50);
		timer = new Timer(1000, new timerListener(this));
	    timer.start();
		super.getViewer().getGameFrame().add(time);
	}

	/**
	 * An ActionListener for the countdown timer
	 */
    class timerListener implements ActionListener 
    {
    	private GameModeTimed game;
    	
    	public timerListener(GameModeTimed g)
    	{
    		game = g;
    	}
    	
    	public void actionPerformed(ActionEvent e) 
    	{
	    	i--;
	    	if(i % 10 == 0)
	    		game.increaseSpeed();
	    	time.setText(String.valueOf(i));
	    	if(i == 0 || done)
	    	{
	    		timer.stop();
	    		done = true;
	    	}
    	}
    }
}
