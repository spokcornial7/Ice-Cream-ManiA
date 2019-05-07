import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public abstract class GameMode extends JComponent implements KeyListener
{
	private ArrayList<Scoop> circles;
	private ArrayList<Scoop> iceCreamScoops;
	private IceCream iceCream; 
	private boolean gameDone;
	
	public static final int speed = 5;
	
	
	public GameMode()
	{
		iceCream = new IceCream(250, 250);
		iceCreamScoops = iceCream.getScoops();
		iceCream.addScoop(new Scoop(235, 400, 3));
		iceCream.addScoop(new Scoop(235, 360, 2));
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		gameDone = false;
		
		circles = new ArrayList<>(); //instantiate with scoops 
		for(int num = 0; num < 10; num++)
		{
			Scoop scoop = makeScoop(500);
			circles.add(scoop);
		}
		
	}	
	
	@Override
	public void paintComponent(Graphics g)
	{	
		iceCream.draw(g);	
		//if(updateScore()) 
			drawDiagram(g);
	}
	
	public Scoop makeScoop(int frameWidth)
	{
		int x = (int) (Math.random() * frameWidth);
		int y = 0;
		int flavor = (int) (Math.random() * 9);
		Scoop s = new Scoop(x, y, flavor);  
		//^ this can be replaced with the scoop's own draw method 
		return s; 
	}
	
	public boolean ifScoopAdded(Scoop s)
	{
		//if iceCreamScoops.isEmpty() --> check to see if scoop touches cone
		Scoop topScoop = iceCream.getTopScoop();
		
		double sY = s.getBoundingBox().getY() + s.getBoundingBox().getHeight();
		double topScoopY = topScoop.getBoundingBox().getY();
		
		double sX = s.getBoundingBox().getX();
		double topScoopX = topScoop.getBoundingBox().getX();
		
		double radius = topScoop.getBoundingBox().getWidth() / 2;
		
		if(sX >= (topScoopX - radius) && sX <= (topScoopX  + radius))
		{
			if(sY >= topScoopY)
			return true; 
		}		

		return false; 
	}
	
	public boolean ifScoopAdded()
	{
	
		/*
		 * if (scoop.boxy >= iceCream.boxy)
		 * 		if(scoop.boxX >= iceCream.boxX && scoop.boxX >= iceCream.boxX + diameter of scoop)
		 * 			return true; 
		 * 
		 * return false;
		 */
		return false;
	}
	
	public IceCream getIceCream() {
		return iceCream;
	}

	
	//won't need these once IceCream class is finished 
	public ArrayList<Scoop> getCircles() 
	{			
		return circles;
	}
	
	public void addCircles(ArrayList<Scoop> circles) 
	{			
		this.circles = circles;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) //right arrow code
		{
			Scoop topScoop = iceCream.getTopScoop();
			if(topScoop.getX() + topScoop.getBoundingBox().getWidth() < getWidth())
				iceCream.shiftRight();
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) //left arrow key
		{
			if(iceCream.getTopScoop().getX() > 0)
				iceCream.shiftLeft();
			repaint();
		}			
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	public class TimerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//while (game over variable is not true) 
			while(!gameDone)
			{
				for(int index = circles.size() - 1; index >= 0 ; index--)
				{
					Scoop scoop = circles.get(index);
					if(ifScoopAdded(scoop))
					{
						iceCream.addScoop(scoop);
						circles.remove(index);
					}
					scoop.shiftScoopDown();
					
				}
			}
			
			
		}

	}
	
	abstract void drawDiagram(Graphics gr);

	abstract int getHighScore();
	
	abstract int getPoints();	
	
	abstract boolean updateScore();
	
	abstract boolean isGameOver();

}

