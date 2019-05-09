
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
	
	// Diagram instance variables
	private Queue<Scoop> scoopQueue;
	public static final int SCOOP_X = 395;
	public static final int SCOOP5_Y = 20;
	public static final int SCOOP4_Y = 80;
	public static final int SCOOP3_Y = 140;
	public static final int SCOOP2_Y = 200;
	public static final int SCOOP1_Y = 260;
		
	public GameModeClassic() 
	{
		super();
		score = 0;
		highscore = 0;
		done = false;
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
			if(scoopQueue.isEmpty())
			{
				score++;
				setHighScore();
				updateDiagram();
				return true;
			}
			addCheckMark();
		}
		else
			done = true;	
		return false;
	}
	
	private boolean correctFlavor()
	{		
		IceCream icecream = super.getIceCream();
		if(!icecream.getScoops().isEmpty())
		{
			Scoop checkScoop = scoopQueue.peek();
			if(checkScoop.getFlavor() == icecream.getTopScoop().getFlavor())
				return true;
		}
		return false;
	}
	
	private void addCheckMark()
	{
		ImageIcon check = new ImageIcon("checkmark.png");
		Image image = check.getImage();
		image = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		Graphics g = image.getGraphics();
		g.drawImage(image, SCOOP_X, scoopQueue.remove().getY(), null);
	}

	
	// DIAGRAM
	@Override
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
		createDiagram();
	}
	
	private void createDiagram()
	{
		scoopQueue = new LinkedList<>();
		int rand1 = (int) (Math.random()*8);
		int rand2 = (int) (Math.random()*8);
		int rand3 = (int) (Math.random()*8);
		int rand4 = (int) (Math.random()*8);
		int rand5 = (int) (Math.random()*8);
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP1_Y, rand1));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP2_Y, rand2));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP3_Y, rand3));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP4_Y, rand4));
		scoopQueue.add(new Scoop(SCOOP_X, SCOOP5_Y, rand5));
	}
}
