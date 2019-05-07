
import java.awt.*;
import java.util.*;
import java.util.Queue;

import javax.swing.*;


public class GameModeClassic extends GameMode
{
	
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
		scoopQueue = new LinkedList<>();
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
	public boolean updateScore(){
		return done;
	}
	
	/*
	private void updateScore()
	{	
		if(super.ifScoopAdded())
		{
			if(correctFlavor())
			{
				if(scoopQueue.isEmpty())
				{
					score++;
					updateDiagram();
				}
			}
			else
				done = true;
		}
	}*/
	
	private void addCheckMark()
	{
		ImageIcon check = new ImageIcon("checkmark.png");
		Image image = check.getImage();
		Image newimg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		check = new ImageIcon(newimg);
	}
	
	@Override
	public boolean isGameOver()
	{
		return done;
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
	
	private boolean correctFlavor()
	{		
		//null?
		Scoop checkScoop = scoopQueue.peek();
		IceCream icecream = super.getIceCream();
		if(checkScoop.getFlavor() == icecream.getTopScoop().getFlavor())
			return true;
		return false;
	}
}
