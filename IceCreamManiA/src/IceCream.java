/** Draws the IceCream which is an ArrayList of scoops and a cone. Moves the IceCream as needed
 *  down, left, or right and adds/removes scoops from the IceCream
 *  @author Lauren Ouyang
 *  Collaborators: Helen Zhao, Carol Zeng 
 *  Teacher: Mrs. Ishman
 *  Periods: 2. 3
 *  Due Date: 5/16/19
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;


public class IceCream 
{
	// Constants for the cone
	private static final Color CONE_COLOR = new Color(205, 133, 63);
	private static final int CONE_X_MID = 16;
	private static final int CONE_X_RIGHT = 31;
	private static final int CONE_HEIGHT = 40;
	private static final int SCOOP_HEIGHT = 36;
	private static final int CONE_SIDES = 3;
	private static final int SHIFT_AMT = 15; 
	
	// Instance variables
	private ArrayList<Scoop> scoops; 
	private int x; 
	private int y;
	private Polygon cone;
 
	
	/** Constructs an array list of Scoop that is an IceCream based of a given (x,y) coordinate
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public IceCream(int x, int y)
	{
		this.x = x; 
		this.y = y;		
		
		scoops = new ArrayList<>();

	}
	
	 /** Draws the ice cream cone and the ice cream itself (its scoops)
	  * @param gr what is being drawn
	  */
	 public void draw(Graphics gr) 
	 { 
	        Graphics2D g2 = (Graphics2D) gr;
	        cone = new Polygon(new int[] {x + 1, x + CONE_X_MID, x +  CONE_X_RIGHT}, 
	        		new int[] {y -  CONE_HEIGHT, y, y -  CONE_HEIGHT}, CONE_SIDES);
	        gr.setColor(CONE_COLOR);
	        gr.fillPolygon(cone);
	        for (int i = 0; i < scoops.size(); i++)
	        {
	        	scoops.get(i).draw(g2);
	        }
	        
	 }
	 
	 /** Returns x coordinate of the ice cream
	  *  @return the x coordinate
	  */
	 public int getX()
	 {
		 return x;
	 }
	 
	 /** Returns the y coordinate of the ice cream
	  * @return the y coordinate
	  */
	 public int getY()
	 {
		 return y;
	 }
	 
	 /** Shifts ice cream with its cone up
	  *  @param shiftAmt is the amount shifted
	  */
	 public void shiftY(int shiftAmt)
	 {
		 y -= shiftAmt;
		 for(int i = 0; i < scoops.size(); i++)
			 scoops.get(i).setY(shiftAmt);
	 }
	 
	 /** Sets y to a desired y value
	  *  @param newY is new y value
	  */
	 public void setY(int newY)
	 {
		 y = newY;
	 }
	 
	 /** Adds a scoop to the ice cream
	  * @param newScoop the scoop to be added
	  */
	 public void addScoop(Scoop newScoop)
	 {
		scoops.add(newScoop);
	 }
	 
	 /** Removes 3 scoops from the ice cream (+1 for the incorrect scoop)
	  */
	 public void removeScoops()
	 {
		 scoops.remove(scoops.size() - 1);
		 scoops.remove(scoops.size() - 1);
		 scoops.remove(scoops.size() - 1);
		 scoops.remove(scoops.size() - 1);
	 }
	 
	 /** Clears all scoops from the ice cream
	  */
	 public void clearScoops()
	 {
		 scoops.clear();
	 }
	 
	 /** Returns the ArrayList of scoops
	  * @return the ArrayList of scoops
	  */
	 public ArrayList<Scoop> getScoops()
	 {
		 return scoops;
	 }
	 
	 /** Returns the last added scoop
	  * @return the top scoop that was added last
	  */
	 public Scoop getTopScoop()
	 {
		 return scoops.get(scoops.size() - 1);
	 }
	 
	 /** Changes the y coordinate so that the ice cream shifts down
	  */
	 public void shiftDown()
	 {
		 y += SCOOP_HEIGHT;
		 for (int i = 0; i < scoops.size(); i++)
	     {
	       	scoops.get(i).shiftScoopDown();
	     }
	 }
	 
	 /** Shifts the ice cream to the left
	  */
	 public void shiftLeft()
	 {
		 x -= SHIFT_AMT; 
		 for (int i = 0; i < scoops.size(); i++)
	     {
	       	scoops.get(i).shiftScoopLeft();
	     }
	 }
	 
	 /** Shifts the ice cream to the right
	  */
	 public void shiftRight()
	 {
		 x += SHIFT_AMT; 
		 for (int i = 0; i < scoops.size(); i++)
	     {
	       	scoops.get(i).shiftScoopRight();
	     }
	 }
	 
	 /** Checks if ice cream is empty
	  * @return whether or not the ice cream is empty
	  */
	 public boolean isEmpty()
	 {
		 if (scoops.size() == 0)
			 return true; 
		 else
			 return false; 
	 }

	 	 
	 
}
