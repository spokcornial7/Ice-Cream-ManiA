import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public abstract class GameMode extends JComponent implements KeyListener 
{
	private ArrayList<Circle> circles;
	private int speed;
	
	public GameMode()
	{
		speed = 5; 
		circles = new ArrayList<>(); //instantiate with scoops 
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D gr2 = (Graphics2D) g;
	}
	
	public Ellipse2D.Double makeScoop(int frameWidth, int frameHeight)
	{
		int x = (int) (Math.random() * frameWidth);
		int y = (int) (Math.random() * frameHeight); 
		int diameter = 50; 
		Ellipse2D.Double scoop = new Ellipse2D.Double(x, y, diameter, diameter); 
		//^ this can be replaced with the scoop's own draw method 
		return scoop; 
	}
	
	public void scoopFall(Ellipse2D.Double circle, int frameHeight) //i used ellipse for now 
	{
		if(circle.y < frameHeight)
		{
			circle.y += speed; 
		}
		
		//will need to repaint after each call
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == 39) //right arrow code
		{
			//move the icecream right
		}
		
		if(e.getKeyCode() == 37) //left arrow key
		{
			//move the icecream left 
		}
			
		repaint(); //idk if gameViewer could/should do this 
	}
	
	public ArrayList<Circle> getCircles() 
	{
		return circles;
	}

	public void addCircles(ArrayList<Circle> circles) {
		this.circles = circles;
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
	
	abstract int getHighScore();
	
	abstract void setHighScore(int highScore);
	
	abstract void addPoints(int points);
	
	abstract int getPoints();
	
	abstract boolean isGameOver();
	
}
