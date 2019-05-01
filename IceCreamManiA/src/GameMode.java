import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public /*abstract*/ class GameMode extends JComponent
{
	private ArrayList<Circle> circles;
	private Rectangle player;
	
	public static final int speed = 5;
	
	public GameMode()
	{
		circles = new ArrayList<>(); //instantiate with scoops 
		player = new Rectangle(250, 449, 50, 50);
	}
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		frame.setSize(500,500);
		frame.setLocation(0,0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameMode game = new GameMode();
		frame.add(game);
		
		frame.setVisible(true);
		
		KeyListenerExample listener = new KeyListenerExample(game); 
		frame.addKeyListener(listener);
		
		
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D gr2 = (Graphics2D) g;
		
		gr2.setColor(Color.black);
		gr2.draw(player);
		
		Ellipse2D.Double scoop = makeScoop(500, 500);
		gr2.draw(scoop);
		scoopFall(scoop, 500);
	}
	
	public Ellipse2D.Double makeScoop(int frameWidth, int frameHeight)
	{
		int x =  100; //(int) (Math.random() * frameWidth);
		int y = 100; //(int) (Math.random() * frameHeight); 
		int diameter = 150; 
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
		
		repaint();//will need to repaint after each call
	}
	
	public void MoveRight()
	{
		player.x += speed; 
		repaint();
	}
	
	public void MoveLeft()
	{
		player.x -= speed; 
		repaint(); 
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
	/*
	abstract int getHighScore();
	
	abstract void setHighScore(int highScore);
	
	abstract void addPoints(int points);
	
	abstract int getPoints();
	
	abstract boolean isGameOver();
	*/
	
}

