import java.awt.BasicStroke;
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
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Timer;

public abstract class GameMode extends JComponent implements KeyListener, ActionListener
{
	private ArrayList<Scoop> scoops;
	private IceCream iceCream; 
	private JLabel lblScore;
	private JLabel lblHighscore;
	private Timer timer;
	private GameViewer viewer;
	
	private boolean added;
	private int speed;
	private static final int ITL_SPEED = 8;
	
	public static final int RIGHT_BOUND = 334;
	public static final int LEFT_BOUND = 15;
	public static final int NUM_FLAVORS = 6;
	
	public static final int FRAME_HEIGHT = 600;
	public static final int FRAME_WIDTH = 450;
	public static final int CONE_STARTY = 560;
	
	private static final Rectangle BORDER = new Rectangle(0, 0, 450, 600 );
	private static final Rectangle RIGHT_BOX = new Rectangle(370, 0, 80, 600);
	private static final Rectangle DIA_BOX = new Rectangle(380, 6, 60, 310);
	private static final Color BORDER_COLOR = new Color(71, 87, 165);
	
	
	public GameMode(GameViewer viewer)
	{
		speed = ITL_SPEED;
		this.viewer = viewer; 
		iceCream = new IceCream(RIGHT_BOUND / 2, CONE_STARTY);

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		timer = new Timer(speed, this);
		timer.start();
		
		scoops = new ArrayList<>();
		randScoops();
		
		addScoreLabel();
		addHighScoreLabel();
	}	
	
	@Override
	public void paintComponent(Graphics g)
	{	
		iceCream.draw(g);
		drawBorder((Graphics2D) g);
		drawDiagram(g);
		
		for(Scoop s : scoops)
			s.draw((Graphics2D) g);
		
		if(added) 
			updateScoreLabels();		
	}
	
	public void reset()
	{
		updateScoreLabels();
		iceCream = new IceCream(RIGHT_BOUND / 2, CONE_STARTY);
		timer.stop();
		speed = ITL_SPEED;
		timer = new Timer(speed, this);
		timer.start();
		scoops = new ArrayList<>();
		randScoops();
	}
	
	public void drawBorder(Graphics2D gr)
	{
		gr.setColor(BORDER_COLOR);
		gr.setStroke(new BasicStroke(11));
		gr.draw(BORDER);
		gr.fill(RIGHT_BOX);
		gr.setColor(Color.white);
		gr.fill(DIA_BOX);
	}
	
	public void increaseSpeed()
	{
		timer.stop();
		speed --;
		timer = new Timer(speed, this);
		timer.start();
	}
	
	public boolean ifScoopAdded(Scoop s)
	{
		//Get 
		double sX = s.getBoundingBox().getX();
		double sY = s.getBoundingBox().getY() + s.getBoundingBox().getHeight();
		
		if(iceCream.isEmpty())
		{
			if(sX >= (iceCream.getX() - 18) && sX <= (iceCream.getX() + 18)) // 18 is half the cone's width
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
		
		if(sX >= (topScoopX - radius) && sX <= (topScoopX + radius)) //36 is scoop's width
		{
			if(sY == topScoopY)
				return true; 
		}		
		return false; 
	}
	
	public IceCream getIceCream() {
		return iceCream;
	}
	
	public GameViewer getViewer() {
		return viewer;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			if(iceCream.isEmpty())
				iceCream.shiftRight();
			else if(iceCream.getTopScoop().getX() + 36 < RIGHT_BOUND)
				iceCream.shiftRight();
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			if(iceCream.isEmpty())
				iceCream.shiftLeft();
			else if(iceCream.getTopScoop().getX() + 36 < LEFT_BOUND)
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

	public void randScoops()
	{
		for(int index = 0; index < 12; index++)
		{
			Scoop s = makeScoop();
			scoops.add(s);
			
		}

	}
	
	public Scoop makeScoop()
	{	
		Scoop s;
		do 
		{
			int x = (int) (Math.random() * (RIGHT_BOUND - LEFT_BOUND) + LEFT_BOUND);
			int y = (int) (Math.random() * -FRAME_HEIGHT);
			int flavor = (int) (Math.random() * NUM_FLAVORS);
			s = new Scoop(x, y, flavor); 
		}
		while(ifOverlap(s));
		return s; 
	}
	
	public boolean ifOverlap(Scoop s)
	{	
		for(int index = 0; index < scoops.size(); index++)
		{
			Scoop temp = scoops.get(index);
			if(scoopTouch(s, temp)) // if the scoops touch
				return true;
		}
		return false; 
	}
	
	public boolean scoopTouch(Scoop s, Scoop temp)
	{
		//this returns that the scoops do touch
		if(s.getX()  < temp.getX() + 50 && s.getX() + 40 > temp.getX())
			if(s.getY() < temp.getY() + 40 && s.getY() + 40 > temp.getY()) //a size bigger than the width 
				return true; 
		
		//returns that the scoops don't touch 
		return false; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{ 
		if(isGameOver())
		{
			timer.stop();
			setHighScore();
			updateScoreLabels();
			viewer.endGame();
		}
		
		for(int index = scoops.size() - 1; index >= 0 ; index--)
		{
			Scoop s = scoops.get(index);
			if(ifScoopAdded(s))
			{
				iceCream.addScoop(s);
				scoops.set(index, makeScoop());
				added = updateScore();
			}
			else if(s.getBoundingBox().y > FRAME_HEIGHT)
			{
				scoops.set(index, makeScoop());
			}	
			repaint();
			s.dropDown(1);
		}
	
	}
	

	public void addScoreLabel()
	{
		lblScore = new JLabel(String.valueOf(getPoints()));
		lblScore.setFont(new Font("Lucida Grande", Font.BOLD, 50));
		lblScore.setForeground(Color.white);
		lblScore.setBounds(380, 400, 100, 100);
		viewer.getGameFrame().add(lblScore);
	}
	
	public void addHighScoreLabel()
	{
		lblHighscore = new JLabel(String.valueOf(getHighScore()));
		lblHighscore.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		lblHighscore.setForeground(Color.white);
		lblHighscore.setBounds(380, 450, 100, 100);
		viewer.getGameFrame().add(lblHighscore);
	}
	
	public void updateScoreLabels()
	{
		lblScore.setText(String.valueOf(getPoints()));
		lblHighscore.setText(String.valueOf(getHighScore()));
	}

	abstract boolean isGameOver();
	
	abstract void drawDiagram(Graphics gr);

	abstract int getHighScore();
	
	abstract int getPoints();	
	
	abstract void setHighScore();
	
	abstract boolean updateScore();
	
	

}

