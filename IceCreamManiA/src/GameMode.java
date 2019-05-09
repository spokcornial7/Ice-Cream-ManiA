import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JLabel;

public abstract class GameMode extends JComponent implements KeyListener
{
	private ArrayList<Scoop> circles;
	private IceCream iceCream; 
	private boolean ifGameOver;
	private JLabel lblScore;
	private JLabel lblHighscore;
	
	public static final int speed = 5;
	public static final int RIGHT_BOUND = 370;
	public static final int LEFT_BOUND = 5;
	
	
	public GameMode()
	{
		iceCream = new IceCream(RIGHT_BOUND/2, 560);
		//iceCream.addScoop(new Scoop(235, 400, 3));
		//iceCream.addScoop(new Scoop(235, 360, 2));
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		setGameOver(false); 
		
		circles = new ArrayList<>(); //instantiate with scoops 
		for(int num = 0; num < 10; num++)
		{
			Scoop scoop = makeScoop();
			circles.add(scoop);
		}
	
		
	}	
	
	@Override
	public void paintComponent(Graphics g)
	{	
		if(iceCream.getScoops().isEmpty())
			drawDiagram(g);
		iceCream.draw(g);	
		if(updateScore()) 
		{
			drawDiagram(g); 
			updateScoreLabels();
		} 
			
	}
	
	public boolean ifScoopAdded(Scoop s)
	{
		//Get 
		double sX = s.getBoundingBox().getX();
		double sY = s.getBoundingBox().getY() + s.getBoundingBox().getHeight();
		
		//if iceCreamScoops.isEmpty() --> check to see if scoop touches cone
		
		if(iceCream.getScoops().isEmpty())
		{
			if(sX >= (iceCream.getX() ) && sX <= (iceCream.getX()))
			{
				return true; 
			}
			return false; 
		}
		
		
		Scoop topScoop = iceCream.getTopScoop();
		double topScoopY = topScoop.getBoundingBox().getY();
		double topScoopX = topScoop.getBoundingBox().getX();
		
		double radius = topScoop.getBoundingBox().getWidth() / 2;
		
		if(sX >= (topScoopX - radius) && sX <= (topScoopX  + radius))
		{
			if(sY >= topScoopY)
			return true; 
		}		

		return false; 
	}
	
	public IceCream getIceCream() {
		return iceCream;
	}
	
	public boolean isGameOver() 
	{
		return ifGameOver; 
	}

	public void setGameOver(boolean ifGameOver) 
	{
		this.ifGameOver = ifGameOver;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) //right arrow code
		{
			if(iceCream.getX() + 36 < RIGHT_BOUND)
				iceCream.shiftRight();
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) //left arrow key
		{
			if(iceCream.getX() > LEFT_BOUND)
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

	public Scoop[] randScoops()
	{
		Scoop[] scoops = new Scoop[20];
		for(int index = 0; index < scoops.length; index++)
		{
			scoops[index] = makeScoop();
		}
		return scoops; 
	}
	
	public Scoop makeScoop()
	{	
		int x = (int) (Math.random() * getWidth());
		int flavor = (int) (Math.random() * 9); //make a constant 
		Scoop s = new Scoop(x, 0, flavor);  
		return s; 
	}
	
	public void resetScoop(Scoop s)
	{
		s.getBoundingBox().y = 0;
	}
	
	public class TimerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//while (game over variable is not true) 
			while(!isGameOver())
			{	
				Scoop[] scoops = randScoops();
				for(int index = circles.size() - 1; index >= 0 ; index--)
				{
					Scoop s = scoops[index];
					if(ifScoopAdded(s))
					{
						iceCream.addScoop(s);
						scoops[index] = makeScoop();
						updateScore();
					}
					s.shiftScoopDown();
					if(s.getBoundingBox().height > getHeight())
					{
						resetScoop(s); //could be in the scoop class? 
					}
					
				}
			}
			
			
		}

	}
	
	public JLabel scoreLabel()
	{
		lblScore = new JLabel(String.valueOf(getPoints()));
		lblScore.setFont(new Font("Lucida Grande", Font.BOLD, 50));
		lblScore.setForeground(Color.white);
		lblScore.setBounds(380, 400, 100, 100);
		return lblScore;
	}
	
	public JLabel highScoreLabel()
	{
		lblHighscore = new JLabel(String.valueOf(getHighScore()));
		lblHighscore.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		lblHighscore.setForeground(Color.white);
		lblHighscore.setBounds(380, 450, 100, 100);
		return lblHighscore;
	}
	
	private void updateScoreLabels()
	{
		lblScore.setText(String.valueOf(getPoints()));
		lblHighscore.setText(String.valueOf(getHighScore()));
	}
	
	abstract void drawDiagram(Graphics gr);

	abstract int getHighScore();
	
	abstract int getPoints();	
	
	abstract boolean updateScore();
	
	

}

