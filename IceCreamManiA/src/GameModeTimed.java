/** GameModeTimed.java creates methods for the rules and scores and draws
 *  the necessary diagram for the Timed game mode. It determines when the game is over, 
 *  whether the added flavor was correct, the current score and high score, and draws a 
 *  diagram of scoops to be added randomly each time a correct scoop is added. 
 *  @author Carol Zeng 
 *  Collaborators: Helen Zhao, Lauren Ouyang
 *  Teacher: Mrs. Ishman
 *  Periods: 2, 3
 *  Due Date: 5/16/19
 */

import javax.swing.JLabel;
import javax.swing.Timer;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


		
public class GameModeTimed extends GameMode
{
	// Diagram constants
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
	
	// Constants for updating score
	private static final int REMOVE_SCOOPS = 3; 
	private static final int STROKE = 4; 
	private static final int MAX_Y = 560; 
	private static final int MAX_TOP_Y = 500; 
	private static final int SHIFT_UP = 350; 
	
	// Timer static constants
	private static final Rectangle TIMED_BOX = new Rectangle(385, 253, 50, 55);
	private static final Color BOX_COLOR = new Color(254, 195, 207);
	private static final Rectangle TIMER_BOUNDS = new Rectangle(180, 100, 100, 50);
	private static final int TIMER_START = 70;
	private static final Font TIMER_FONT = new Font("Lucida Grande", Font.BOLD, 30);
	private static final int TIMER_DELAY = 1000;
	private static final int SPEED_CHECK = 10;
		
	// Diagram instance variable
	private Queue<Scoop> scoopQueue;
	
	// Game instance constants/variables
	private static int highscore = 0;
	private int score;
	private boolean done;
	private IceCream icecream;
	
	// Timer instance variables
	private int i;
	private JLabel time;
	private Timer timer;

	/**
	 *  Instantiates game mode timed
	 *  @param viewer to view to game
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

	/** Resets the game
	 */
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
	
	/** Draws the border/rectangle around the scoop that 
	 *  should be added next
	 */
	@Override
	public void drawBorder(Graphics2D gr)
	{
		super.drawBorder(gr);
		gr.setColor(BOX_COLOR);
		gr.setStroke(new BasicStroke(STROKE));
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
		{
			score = 0;
			done = true;
		}
		else
		{
			if(score <= REMOVE_SCOOPS)
			{
				icecream.clearScoops();
				score = 0;
				if(icecream.getY() > MAX_Y)
					icecream.setY(MAX_Y);
			}
			else
			{
				icecream.removeScoops();
				score -= REMOVE_SCOOPS;
				if(icecream.getTopScoop().getY() > MAX_TOP_Y)
				{
					int shiftUpVal = icecream.getTopScoop().getY() -  SHIFT_UP;
					if(icecream.getY() - shiftUpVal < MAX_Y)
						shiftUpVal = icecream.getY() - MAX_Y;
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
		return icecream.getTopScoop().getFlavor() == NUM_FLAVORS;
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

	/** Increases the speed that the scoop drops
	 */
	public void increaseSpeed()
	{
		super.increaseSpeed();
	}
	
	/**
	 *  Draws a label for the count down timer
	 *  @return the timer label
	 */
	public void drawTimer()
	{
		i = TIMER_START;
		time = new JLabel();
		time.setFont(TIMER_FONT);
		time.setBounds(TIMER_BOUNDS);
		timer = new Timer(TIMER_DELAY, new timerListener(this));
	    timer.start();
		super.getViewer().getGameFrame().add(time);
	}

	/**
	 * An ActionListener for the count down timer
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
	    	if(i % SPEED_CHECK == 0)
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
