/** The GameMode class contains basic functions shared by both the GameModeTimed and GameModeClassic classes. 
 *	It creates a seemingly never ending amount of random scoops to fall from the top of the screen. The class
 *  also determines whether or not the player had successfully stacked a scoop on the IceCream. 
 *  @author Helen Zhao
 *  Collaborators: Carol Zeng, Lauren Ouyang
 *  Teacher: Mrs. Ishman
 *  Periods: 2, 3
 *  Due Date: 5/16/19
 */

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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Timer;

public abstract class GameMode extends JComponent implements KeyListener, ActionListener
{
	//Constants relating to the frame
	public static final int RIGHT_BOUND = 334;
	public static final int LEFT_BOUND = 15;
	public static final int FRAME_HEIGHT = 600;
	public static final int FRAME_WIDTH = 450;
	private static final Rectangle BORDER = new Rectangle(0, 0, 450, 600 );
	private static final Rectangle RIGHT_BOX = new Rectangle(370, 0, 80, 600);
	private static final Rectangle DIA_BOX = new Rectangle(380, 6, 60, 310);
	private static final Color BORDER_COLOR = new Color(71, 87, 165);
	private static final Font SCORE_FONT = new Font("Lucida Grande", Font.BOLD, 50);
	private static final Font HISCORE_FONT = new Font("Lucida Grande", Font.BOLD, 25);
	private static final Rectangle SCORE_BOUNDS = new Rectangle(380, 400, 100, 100);
	private static final Rectangle HISCORE_BOUNDS = new Rectangle(380, 450, 100, 100);
	
	//Constants for the game
	public static final int NUM_FLAVORS = 6;
	public static final int CONE_START_Y = 560;
	public static final int NUM_SCOOPS = 12;
	public static final int SCOOP_DISTANCE = 40;
	
	//Instance variables for the game
	private ArrayList<Scoop> scoops;
	private IceCream iceCream; 
	private JLabel lblScore;
	private JLabel lblHighscore;
	private Timer timer;
	private GameViewer viewer;
	private boolean added;
	private int speed;
	private static final int ITL_SPEED = 8;
	
	/** Constructs a GameMode object
	 *  @param viewer is the GameViewer object
	 */
	public GameMode(GameViewer viewer)
	{
		speed = ITL_SPEED;
		this.viewer = viewer; 
		iceCream = new IceCream(RIGHT_BOUND / 2, CONE_START_Y);

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
	
	/** Draws the components of the Game
	 *  @param g is the Graphics object
	 */
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
	
	/** Resets the game
	 */
	public void reset()
	{
		updateScoreLabels();
		iceCream = new IceCream(RIGHT_BOUND / 2, CONE_START_Y);
		timer.stop();
		speed = ITL_SPEED;
		timer = new Timer(speed, this);
		timer.start();
		scoops = new ArrayList<>();
		randScoops();
	}
	
	/** Draws the border for the diagram
	 * 	@param gr is the Graphics object 
	 */
	public void drawBorder(Graphics2D gr)
	{
		gr.setColor(BORDER_COLOR);
		gr.setStroke(new BasicStroke(11));
		gr.draw(BORDER);
		gr.fill(RIGHT_BOX);
		gr.setColor(Color.white);
		gr.fill(DIA_BOX);
	}
	
	/** Increases the speed of the scoops falling from the top of the screen 
	 */
	public void increaseSpeed()
	{
		timer.stop();
		speed --;
		timer = new Timer(speed, this);
		timer.start();
	}
	
	/** Determines if the scoop can be added to the iceCream
	 * 	@param s the Scoop to be determined if added
	 *  @return true if the scoop is added to the iceCream, false otherwise
	 */
	public boolean ifScoopAdded(Scoop s)
	{
		double sX = s.getBoundingBox().getX();
		double sY = s.getBoundingBox().getY() + s.getBoundingBox().getHeight();
		
		double iceCreamWidth = (IceCream.CONE_X_RIGHT - IceCream.CONE_X_MID) * 2;
		if(iceCream.isEmpty())
		{
			if(sX >= (iceCream.getX() - iceCreamWidth) 
					&& sX <= (iceCream.getX() + iceCreamWidth))
			{
				if(sY == iceCream.getY() - IceCream.CONE_HEIGHT) 
					return true; 
			}
			return false; 
		}
		
		Scoop topScoop = iceCream.getTopScoop();
		double topScoopY = topScoop.getBoundingBox().getY();
		double topScoopX = topScoop.getBoundingBox().getX();
		
		double radius = Scoop.SCOOP_XY_LENGTH / 2;
		
		if(sX >= (topScoopX - radius) && sX <= (topScoopX + radius))
		{
			if(sY == topScoopY)
				return true; 
		}		
		return false; 
	}
	
	/** Returns the IceCream object
	 *  @return the IceCream object
	 */
	public IceCream getIceCream() 
	{
		return iceCream;
	}
	
	/** Returns the GameViewer object
	 *  @return the GameViewer object
	 */
	public GameViewer getViewer() 
	{
		return viewer;
	}
	
	/** Moves the iceCream right and left 
	 *	@param e is the key typed
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			if(iceCream.isEmpty())
			{
				if(iceCream.getX() < RIGHT_BOUND)
					iceCream.shiftRight();
			}	
			else if(iceCream.getTopScoop().getX() < RIGHT_BOUND)
				iceCream.shiftRight();
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			if(iceCream.isEmpty())
			{
				if(iceCream.getX() > LEFT_BOUND)
					iceCream.shiftLeft();
			}	
			else if(iceCream.getTopScoop().getX() > LEFT_BOUND)
				iceCream.shiftLeft();				
			repaint();
		}			
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	/** Creates a random list of scoops
	 */
	public void randScoops()
	{
		for(int index = 0; index < NUM_SCOOPS; index++)
		{
			Scoop s = makeScoop();
			scoops.add(s);
			
		}

	}
	
	/** Makes a random scoop
	 *  @return the newly created scoop
	 */
	public Scoop makeScoop()
	{	
		Scoop s;
		do 
		{
			int x = (int) (Math.random() * (RIGHT_BOUND - LEFT_BOUND) + LEFT_BOUND);
			int y = (int) (Math.random() * -FRAME_HEIGHT);
			int flavor = (int) (Math.random() * (flavorNum() + 1));
			s = new Scoop(x, y, flavor); 
		}
		while(ifOverlap(s));
		return s; 
	}
	
	/** Checks if the newly created scoop overlaps with the rest of the scoops in the list of scoops
	 *  @param s is the newly created Scoop
	 *  @return true if overlap exists, false otherwise
	 */
	public boolean ifOverlap(Scoop s)
	{	
		for(int index = 0; index < scoops.size(); index++)
		{
			Scoop temp = scoops.get(index);
			if(scoopTouch(s, temp))
				return true;
		}
		return false; 
	}
	
	/** Checks if the two scoops touch
	 *  @param s is the first Scoop
	 *  @param temp is the other Scoop
	 *  @return true if the scoops do touch, false otherwise
	 */
	public boolean scoopTouch(Scoop s, Scoop temp)
	{
		if(s.getX()  < temp.getX() + SCOOP_DISTANCE && s.getX() + SCOOP_DISTANCE > temp.getX())  
			if(s.getY() < temp.getY() + SCOOP_DISTANCE && s.getY() + SCOOP_DISTANCE > temp.getY()) 
				return true; 

		return false; 
	}
	
	/** Runs the game
	 *  @param e is the actionEvent
	 */
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
	
	/** Adds the score label to the game viewer
	 */
	public void addScoreLabel()
	{
		lblScore = new JLabel(String.valueOf(getPoints()));
		lblScore.setFont(SCORE_FONT);
		lblScore.setForeground(Color.white);
		lblScore.setBounds(SCORE_BOUNDS);
		viewer.getGameFrame().add(lblScore);
	}
	
	/** Adds the high score label to the game viewer
	 */
	public void addHighScoreLabel()
	{
		lblHighscore = new JLabel(String.valueOf(getHighScore()));
		lblHighscore.setFont(HISCORE_FONT);
		lblHighscore.setForeground(Color.white);
		lblHighscore.setBounds(HISCORE_BOUNDS);
		viewer.getGameFrame().add(lblHighscore);
	}
	
	/** Updates the score labels
	 */
	public void updateScoreLabels()
	{
		lblScore.setText(String.valueOf(getPoints()));
		lblHighscore.setText(String.valueOf(getHighScore()));
	}

	/** Returns whether or not the game is over
	 *  @return true if game is over, false otherwise
	 */
	abstract boolean isGameOver();
	
	/** draws the Diagram 
	 *  @param gr is the Graphics object
	 */
	abstract void drawDiagram(Graphics gr);
	
	/** Returns the highScore
	 *  @return the highScore
	 */
	abstract int getHighScore();
	
	/** Returns the points earned by the player
	 *  @return the points
	 */
	abstract int getPoints();	
	
	/** updates the high score
	 */
	abstract void setHighScore();
	
	/**  Updates and returns true if the score was updated
	 *  @return returns true if score increased
	 */
	abstract boolean updateScore();
	
	/** Returns the number of flavors in this game mode
	 *  @return the number of flavors
	 */
	abstract int flavorNum();
	
	

}

