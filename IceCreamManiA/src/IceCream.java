import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JComponent;


public class IceCream 
{
	private static final int CONE_COLOR_R = 205; 
	private static final int CONE_COLOR_G = 133; 
	private static final int CONE_COLOR_B = 63;
	private static final int CONE_X_MID = 16;
	private static final int CONE_X_RIGHT = 31
	private static final int CONE_HEIGHT = 40
	private static final int SCOOP_HEIGHT = 36;
	private static final int CONE_SIDES = 3;
	
	private ArrayList<Scoop> scoops; 
	private int x; 
	private int y;
	private Polygon cone;
	private int coneShift; 
	
	public IceCream(int x, int y)
	{
		this.x = x; 
		this.y = y;		
		cone = new Polygon(new int[] {x + 1, x + CONE_X_MID, x +  CONE_Y_RIGHT}, new int[] {y -  CONE_HEIGHT, y, y -  CONE_HEIGHT}, CONE_SIDES);
		scoops = new ArrayList<>();

	}
	 public void paintComponent(Graphics gr)
	    {
	        Graphics2D g2 = (Graphics2D) gr;
	        Color coneColor = new Color(CONE_COLOR_R,CONE_COLOR_G,CONE_COLOR_B);
	        gr.setColor(coneColor);
	        gr.fillPolygon(cone);
	        for (int i = 0; i < scoops.size(); i++)
	        {
	        	scoops.get(i).draw(g2);
	        }

	    }
	 
	 public void addScoop(Scoop newScoop)
	 {
		scoops.add(newScoop);
	 }
	 
	 public void removeScoop()
	 {
		 scoops.remove(scoops.size() - 1);
	 }
	 
	 public void shiftDown()
	 {
		 coneShift += SCOOP_HEIGHT;
		 cone = new Polygon(new int[] {x + 1, x + CONE_X_MID, x + CONE_X_RIGHT}, new int[] {y -  CONE_HEIGHT + coneShift, y + coneShift, y -  CONE_HEIGHT + coneShift}, 3);
		 for (int i = 0; i < scoops.size(); i++)
	        {
	        	scoops.get(i).shiftScoopDown();
	        }
	 }
}
