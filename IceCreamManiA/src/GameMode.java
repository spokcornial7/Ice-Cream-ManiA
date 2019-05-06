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

public /*abstract*/ class GameMode extends JComponent implements KeyListener
{
	private ArrayList<Scoop> circles;
	private IceCream iceCream; 
	
	public static final int speed = 5;
	
	
	public GameMode()
	{
		iceCream = new IceCream(250, 250);
		iceCream.addScoop(new Scoop(235, 400, 3));
		iceCream.addScoop(new Scoop(235, 360, 2));
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		circles = new ArrayList<>(); //instantiate with scoops 
		for(int num = 0; num < 10; num++)
		{
			Scoop scoop = makeScoop(500);
			circles.add(scoop);
		}
		
	}
	/*
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		frame.setSize(450,600);
		frame.setLocation(0,0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameMode game = new GameMode();
		frame.add(game);
		System.out.println(game.getHeight());
		System.out.println(game.getWidth());
		
		frame.setVisible(true);
	
	}*/
	
	
	@Override
	public void paintComponent(Graphics g)
	{	
		iceCream.draw(g);		
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
		ArrayList<Scoop> iceCreamScoops = iceCream.getScoops();
		double sY = s.getBoundingBox().getY() + s.getBoundingBox().getHeight();
		double topScoopY = iceCreamScoops.get(iceCreamScoops.size() - 1).getBoundingBox().getY();
		
		double sX = s.getBoundingBox().getX();
		double topScoopX = iceCreamScoops.get(iceCreamScoops.size() - 1).getBoundingBox().getX();
		
		double radius = iceCreamScoops.get(iceCreamScoops.size() - 1).getBoundingBox().getWidth() / 2;
		
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
			iceCream.shiftRight();
			repaint();
			System.out.print("Key pressed");
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) //left arrow key
		{
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
			while(true)
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
	/*
	abstract int getHighScore();
	
	abstract void setHighScore(int highScore);
	
	abstract void addPoints(int points);
	
	abstract int getPoints();
	
	abstract boolean isGameOver();
	*/
	
}

