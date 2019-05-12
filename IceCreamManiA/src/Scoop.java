/** Scoop.java creates the shapes that make up a scoop and draws them based on a given
 *  (x, y) coordinate and flavor. It also moves the scoop down, left, and right by 
 *  the given amounts. 
 */

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;


public class Scoop 
{
	// Measurements of scoop components
	public static final int MAIN_CIRCLE_DIA = 32; 
	public static final int SMALL_CIRCLE_DIA = 12; 
	public static final int LEFT_CIRCLE_X_SHIFT = 2;
	public static final int LEFTRIGHT_CIRCLE_XY_SHIFT = 22; 
	public static final int MID_CIRCLE_X_SHIFT = 10; 
	public static final int MID_CIRCLE_Y_SHIFT = 24; 
	public static final int SCOOP_XY_LENGTH = 36;
	public static final int SHIFT_AMT = 18;
	
	// Flavors
	public static final int NUM_BANANA = 0;
	public static final int NUM_STRAWBERRY = 1; 
	public static final int NUM_MINT = 2; 
	public static final int NUM_ORANGE = 3;
	public static final int NUM_BIRTHDAY = 4; 
	public static final int NUM_BOMB = 5; 

	// Constructor instance variables
	private int x;
	private int y;
	private int numFlavor;
	
	// Scoop instance variables
	private Ellipse2D.Double mainCircle; 
	private Ellipse2D.Double leftCircle; 
	private Ellipse2D.Double middleCircle;
	private Ellipse2D.Double rightCircle;

	/* Constructs a scoop based on a given (x, y) coordinate and flavor
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param numFlavor the number that corresponds with the flavor
	 */
	public Scoop(int x, int y, int numFlavor)
	{
		this.x = x; 
		this.y = y; 
		this.numFlavor = numFlavor; 

		mainCircle = new Ellipse2D.Double(x, y, MAIN_CIRCLE_DIA, MAIN_CIRCLE_DIA);
		leftCircle = new Ellipse2D.Double(x - LEFT_CIRCLE_X_SHIFT, y + LEFTRIGHT_CIRCLE_XY_SHIFT, 
									SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA);
		middleCircle = new Ellipse2D.Double(x +  MID_CIRCLE_X_SHIFT, y + MID_CIRCLE_Y_SHIFT, 
									SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA);
		rightCircle = new Ellipse2D.Double(x + LEFTRIGHT_CIRCLE_XY_SHIFT, 
						y + LEFTRIGHT_CIRCLE_XY_SHIFT, SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA); 

	}
	
	/** Draws the 4 circles that make up the scoop 
	 * @param gr the drawing that contains the scoop
	 */
	public void draw(Graphics2D gr)
	{
		mainCircle = new Ellipse2D.Double(x, y, MAIN_CIRCLE_DIA, MAIN_CIRCLE_DIA);
		leftCircle = new Ellipse2D.Double(x-LEFT_CIRCLE_X_SHIFT, y + LEFTRIGHT_CIRCLE_XY_SHIFT, SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA);
		middleCircle = new Ellipse2D.Double(x +  MID_CIRCLE_X_SHIFT, y + MID_CIRCLE_Y_SHIFT, SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA);
		rightCircle = new Ellipse2D.Double(x + LEFTRIGHT_CIRCLE_XY_SHIFT, y + LEFTRIGHT_CIRCLE_XY_SHIFT, SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA);
		gr.setColor(getColorFlavor());
		gr.fill(mainCircle);
		if(numFlavor != NUM_BOMB)
		{
			gr.fill(leftCircle);
			gr.fill(rightCircle);
			gr.fill(middleCircle);
		}
		
	}
	
	/** Returns number that corresponds with flavor of scoop 
	 * @return numFlavor number of flavor
	 */
	public int getFlavor()
	{
		return numFlavor;
	}
	
	/** Returns the color associated with the flavor based off its number 
	 * @return the color of the flavor
	 */
	public Color getColorFlavor()
	{
		Color iceCreamFlavor = null; 
		if (numFlavor == NUM_BANANA)
			iceCreamFlavor = Flavors.BANANA.getFlavor();
		else if (numFlavor == NUM_STRAWBERRY)
			iceCreamFlavor = Flavors.STRAWBERRY.getFlavor();
		else if (numFlavor == NUM_MINT)
			iceCreamFlavor = Flavors.MINT.getFlavor();
		else if (numFlavor == NUM_ORANGE)
			iceCreamFlavor = Flavors.ORANGE_CREAM.getFlavor();
		else if (numFlavor == NUM_BIRTHDAY)
			iceCreamFlavor = Flavors.BIRTHDAY_CAKE.getFlavor();
		else 
			iceCreamFlavor = Flavors.BOMB.getFlavor();
		return iceCreamFlavor; 
		
	}
	
	/** Returns x coordinate of the scoop
	 * @return the x coordinate
	 */
	public int getX()
	{
		return x;
	}
	
	/** Returns y coordinate of the scoop
	 * @return the y coordinate
	 */
	public int getY()
	{
		return y;
	}
	
	public void setY(int newY)
	{
		y -= newY;
	}

	/** Drops the scoop down the screen by a given amount
	 * @param yAmt the amount to be dropped
	 */
	public void dropDown (int yAmt)
	{
		y += yAmt;
	}
	
	/** Shifts scoop down by its height so that it doesn't go off the screen
	 */
	public void shiftScoopDown()
	{
		y += getBoundingBox().getHeight();
	}

	/** Shifts scoop to the right
	 */
	public void shiftScoopRight()
	{
		x += SHIFT_AMT; 
	}
	
	/** Shifts scoop to the left
	 */
	public void shiftScoopLeft()
	{
		x -= SHIFT_AMT; 
	}
	
	/** Returns the diameter of the scoop
	 * @return the scoop's diameter
	 */
	public int scoopDiameter()
	{
		return SCOOP_XY_LENGTH;
	}
	
	/** Returns the rectangle that bounds the scoop
	 * @return box the bounding box
	 */
	public Rectangle getBoundingBox()
	{
		Rectangle box = new Rectangle(x - 1, y, SCOOP_XY_LENGTH, SCOOP_XY_LENGTH);
		return box;
	}
	

}
