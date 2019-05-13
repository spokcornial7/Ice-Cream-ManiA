/** GameModeClassic.java creates methods for the rules and scores and draws 
 *  the necessary diagram for the Classic game mode. It determines when the game is over, 
 *  whether the added flavor was correct, the current score and high score, and draws a 
 *  diagram of scoops to be added randomly in sets of 5. 
 *  @author Carol Zeng
 *  Collaborators: Helen Zhao, Lauren Ouyang
 *  Teacher: Mrs. Ishman
 *  Periods: 2, 3
 *  Due Date: 5/16/19
 */

import java.awt.*;

import java.util.*;


public class GameModeClassic extends GameMode
{
	// Diagram constants
	private static final int SCOOP_X = 394;
	private static final int SCOOP5_Y = 20;
	private static final int SCOOP4_Y = 80;
	private static final int SCOOP3_Y = 140;
	private static final int SCOOP2_Y = 200;
	private static final int SCOOP1_Y = 260;
	private static final int NUM_FLAVORS_BOUND = 4;
	private static final int DIA_FLAVORS = 5;
	
	// Game instance constants/variables
	private static final int LVL_SPEED = 3;
	private static int highscore = 0;
	private int score;
	private boolean done;
	private IceCream icecream;
	private Queue<Scoop> scoopQueue;
		
 
	/** Instantiates game mode classic
	 *  @param viewer to view the game
	 */
	public GameModeClassic(GameViewer viewer) 
	{
		super(viewer);
		score = 0;
		done = false;
		scoopQueue = new LinkedList<>();
		icecream = super.getIceCream();
		createDiagram();
	}

	/** Resets the game
	 */
	@Override
	public void reset()
	{
		super.reset();
		score = 0;
		done = false;
		icecream = super.getIceCream();
		createDiagram();
	}
	
	/** Returns the number of flavors used in this game mode 
	 */
	public int flavorNum()
	{
		return NUM_FLAVORS_BOUND;
	}
	
	/** Returns the high score
	 *  @return high score
	 */
	@Override
	public int getHighScore()
	{
		return highscore;
	}
	
	/** Returns the score
	 *  @return score 
	 */
	@Override
	public int getPoints()
	{
		return score;
	}
	
	/** Sets/updates the high score
	 */
	@Override
	public void setHighScore()
	{
		if(score > highscore)
			highscore = score;
	}
	
	/** Returns if the game is over 
	 *  @return variable holding true if game over, false if not
	 */
	@Override
	public boolean isGameOver()
	{
		return done;
	}
	
	
	/** Updates and returns true if the score was updated
	 *  @return returns true if score increased
	 */
	@Override
	public boolean updateScore()
	{		
		if(correctFlavor())
		{
			scoopQueue.remove();
			if(scoopQueue.isEmpty())
			{
				score++;
				if(score % LVL_SPEED == 0)
					super.increaseSpeed();
				updateDiagram();
				icecream.clearScoops();
				return true;
			}
		}
		else
			done = true;	
		return false;
	}
	
	/**
	 *  Returns if the correct flavor was added 
	 *  @return true if correct flavor added, false if otherwise
	 */
	private boolean correctFlavor()
	{		
		if(!icecream.getScoops().isEmpty())
		{
			Scoop checkScoop = scoopQueue.peek();
			if(checkScoop.getFlavor() == icecream.getTopScoop().getFlavor())
				return true;
		}
		return false;
	}

	
	/** Draws the diagram  
	 */
	@Override
	public void drawDiagram(Graphics g)
	{
		Graphics2D gr2 = (Graphics2D) g;
		for(Scoop s : scoopQueue)
		{
			s.draw(gr2);
		}
	}
	 
	/** Updates the diagram with a new diagram
	 */
	private void updateDiagram()
	{
		createDiagram();
	}
	
	/** Creates a new diagram 
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

}
