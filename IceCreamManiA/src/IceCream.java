import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;


public class IceCream 
{
	private static final Color CONE_COLOR = new Color(205, 133, 63);
	private static final int CONE_X_MID = 16;
	private static final int CONE_X_RIGHT = 31;
	private static final int CONE_HEIGHT = 40;
	private static final int SCOOP_HEIGHT = 36;
	private static final int CONE_SIDES = 3;
	
	private ArrayList<Scoop> scoops; 
	private int x; 
	private int y;
	private Polygon cone;
	
	public IceCream(int x, int y)
	{
		this.x = x; 
		this.y = y;		
		
		scoops = new ArrayList<>();

	}
	 public void draw(Graphics gr) 
	 { 
	        Graphics2D g2 = (Graphics2D) gr;
	        cone = new Polygon(new int[] {x + 1, x + CONE_X_MID, x +  CONE_X_RIGHT}, new int[] {y -  CONE_HEIGHT, y, y -  CONE_HEIGHT}, CONE_SIDES);
	        
	        gr.setColor(CONE_COLOR);
	        gr.fillPolygon(cone);
	        for (int i = 0; i < scoops.size(); i++)
	        {
	        	scoops.get(i).draw(g2);
	        }
	        
	 }
	 
	 public int getX()
	 {
		 return x;
	 }
	 
	 public int getY()
	 {
		 return y;
	 }
	 
	 public void addScoop(Scoop newScoop)
	 {
		scoops.add(newScoop);
	 }
	 
	 public void removeScoop()
	 {
		 scoops.remove(scoops.size() - 1);
	 }
	 
	 public ArrayList<Scoop> getScoops()
	 {
		 return scoops;
	 }
	 
	 public Scoop getTopScoop()
	 {
		 return scoops.get(scoops.size() - 1);
	 }
	 
	 public void shiftDown()
	 {
		 y += SCOOP_HEIGHT;
		 for (int i = 0; i < scoops.size(); i++)
	     {
	       	scoops.get(i).shiftScoopDown();
	     }
	 }
	 
	 public void shiftLeft()
	 {
		 x -= 5; 
		 for (int i = 0; i < scoops.size(); i++)
	     {
	       	scoops.get(i).shiftScoopLeft();
	     }
	 }
	 
	 public void shiftRight()
	 {
		 x += 5; 
		 for (int i = 0; i < scoops.size(); i++)
	     {
	       	scoops.get(i).shiftScoopRight();
	     }
	 }
}
