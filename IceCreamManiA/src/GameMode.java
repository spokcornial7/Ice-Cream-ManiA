import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Timer;

public abstract class GameMode extends JComponent implements KeyListener, ActionListener
{
	private Scoop[] scoops;
	private IceCream iceCream; 
	private JLabel lblScore;
	private JLabel lblHighscore;
	private Timer timer;
	
	public static final int RIGHT_BOUND = 334;
	public static final int LEFT_BOUND = 5;
	
	public static final int FRAME_HEIGHT = 600;
	
	
	public GameMode()
	{
		iceCream = new IceCream(RIGHT_BOUND/2, 560);
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(10, this);
		timer.start();
		
		scoops = randScoops();
	}	
	
	@Override
	public void paintComponent(Graphics g)
	{			
		if(iceCream.getScoops().isEmpty())
			drawDiagram(g);
		iceCream.draw(g);
		for(Scoop s : scoops)
			s.draw((Graphics2D) g);
		
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
			if(sX >= (iceCream.getX()) && sX <= (iceCream.getX() + 18)) // 18 is half the cone's width
			{
				if(sY == iceCream.getY() - 40) // 40 is cone height
					return true; 
			}
			return false; 
		}
		
		Scoop topScoop = iceCream.getTopScoop();
		double topScoopY = topScoop.getBoundingBox().getY();
		double topScoopX = topScoop.getBoundingBox().getX();
		
		double radius = topScoop.getBoundingBox().getWidth() / 2;
		
		if(sX >= (topScoopX - radius) && sX <= (topScoopX + 36 + radius)) //36 is scoop's width
		{
			if(sY == topScoopY)
				return true; 
		}		
		return false; 
	}
	
	public IceCream getIceCream() {
		return iceCream;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			if(iceCream.getX() < RIGHT_BOUND)
				iceCream.shiftRight();
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
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
		Scoop[] scoops = new Scoop[5];
		for(int index = 0; index < scoops.length; index++)
		{
			scoops[index] = makeScoop();
		}
		return scoops; 
	}
	
	public Scoop makeScoop()
	{	
		int x = (int) (Math.random() * (RIGHT_BOUND - LEFT_BOUND) + LEFT_BOUND);
		int y = (int) (Math.random() * 350) - FRAME_HEIGHT;
		int flavor = (int) (Math.random() * 9); //make a constant 
		Scoop s = new Scoop(x, y, flavor);  
		return s; 
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{ 
		if(isGameOver())
			timer.stop();
		
		for(int index = scoops.length - 1; index >= 0 ; index--)
		{
			Scoop s = scoops[index];
			repaint();
			s.dropDown(1);
			if(ifScoopAdded(s))
			{
				iceCream.addScoop(s);
				scoops[index] = makeScoop();
				updateScore();
			}
			else if(s.getBoundingBox().y > FRAME_HEIGHT)
			{
				scoops[index] = makeScoop();
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
	
	abstract boolean isGameOver();
	
	abstract void drawDiagram(Graphics gr);

	abstract int getHighScore();
	
	abstract int getPoints();	
	
	abstract boolean updateScore();
	
	

}

