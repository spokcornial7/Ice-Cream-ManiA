import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.Ellipse2D;

public /*abstract*/ class GameMode extends JComponent
{
	private ArrayList<Scoop> circles;
	private Rectangle player;
	private IceCream iceCream; 
	
	public static final int speed = 5;
	
	public GameMode()
	{
		setIceCream(new IceCream());
		circles = new ArrayList<>(); //instantiate with scoops 
		for(int num = 0; num < 10; num++)
		{
			Scoop scoop = makeScoop(500);
			circles.add(scoop);
		}
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
		
		for(int index = 0; index < circles.size(); index++)
		{
			Scoop s = circles.get(index);
			s.draw(gr2);
		}
		
		
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
	
	public void advanceScoop(Scoop scoop)
	{
		

	}
	
	public boolean ifScoopAdded(Scoop s)
	{
		// get the scoop's x1 and y1 coordinate value 
		//get the top scoop on the ice cream's x2 and y2 coordinate 
		//if(x1 > (x2 - radius) && x1 < (x2 + radius))
		//		return true; 
		
		return false; 
	}
	
	public void MoveRight()
	{
		int playerWidth = 50;
		if((player.x + speed + playerWidth) < 500) //frameheight
		{
			player.x += speed; 
		}
		
		repaint();
	}
	
	public void MoveLeft()
	{
		if((player.x - speed) > 0)
		{
			player.x -= speed; 
		}
		
		repaint(); 
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

	public void setIceCream(IceCream iceCream) {
		this.iceCream = iceCream;
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
	
	/*
	abstract int getHighScore();
	
	abstract void setHighScore(int highScore);
	
	abstract void addPoints(int points);
	
	abstract int getPoints();
	
	abstract boolean isGameOver();
	*/
	
}

