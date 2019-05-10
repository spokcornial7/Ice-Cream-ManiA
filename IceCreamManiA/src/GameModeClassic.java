
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.*;

import javax.swing.*;


public class GameModeClassic extends GameMode
{
	// Game instance variables
	private int score;
	private int highscore;
	private boolean done;
	private IceCream icecream;
	
	// Diagram instance variables
	private Queue<Scoop> scoopQueue;
	private static final int SCOOP_X = 395;
	private static final int SCOOP5_Y = 20;
	private static final int SCOOP4_Y = 80;
	private static final int SCOOP3_Y = 140;
	private static final int SCOOP2_Y = 200;
	private static final int SCOOP1_Y = 260;
	private static final int NUM_FLAVORS = 4;
		
	
	/**
	 *  Instantiates game mode classic
	 */
	public GameModeClassic(GameViewer viewer) 
	{
		super(viewer);
		score = 0;
		highscore = 0;
		done = false;
		scoopQueue = new LinkedList<>();
		icecream = super.getIceCream();
		createDiagram();
	}

	@Override
	public void reset()
	{
		super.reset();
		score = 0;
		done = false;
		icecream = super.getIceCream();
		createDiagram();
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
			addCheckMark();
			if(scoopQueue.isEmpty())
			{
				score++;
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
	
	/**
	 *  Adds a check mark over the diagram
	 */
	private void addCheckMark()
	{
		ImageIcon check = new ImageIcon("checkmark.png");
		Image image = check.getImage();
		image = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		Graphics g = super.getGraphics();
		g.drawImage(image, SCOOP_X, scoopQueue.remove().getY(), null);
	}

	
	/**
	 *  Draws the diagram
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
	
	/**
	 *  Updates the diagram with a new diagram
	 */
	private void updateDiagram()
	{
		createDiagram();
	}
	
	/**
	 *  Creates a new diagram
	 */
	private void createDiagram()
	{
		scoopQueue.clear();
		int rand1 = (int) (Math.random() * NUM_FLAVORS);
		int rand2 = (int) (Math.random() * NUM_FLAVORS);
		int rand3 = (int) (Math.random() * NUM_FLAVORS);
		int rand4 = (int) (Math.random() * NUM_FLAVORS);
		int rand5 = (int) (Math.random() * NUM_FLAVORS);
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP1_Y, rand1));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP2_Y, rand2));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP3_Y, rand3));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP4_Y, rand4));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP5_Y, rand5));
	}
}
