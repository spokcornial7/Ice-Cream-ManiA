import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JComponent;


public class IceCream extends JComponent
{
	private ArrayList<Scoop> scoops; 
	private int x; 
	private int y;
	private Polygon cone;
	private int coneShift; 
	
	public IceCream(int x, int y)
	{
		this.x = x; 
		this.y = y;		
		cone = new Polygon(new int[] {x + 1, x + 16, x + 31}, new int[] {y - 40, y, y - 40}, 3);
		scoops = new ArrayList<>();

	}
	 public void paintComponent(Graphics gr)
	    {
	        Graphics2D g2 = (Graphics2D) gr;
	        Color coneColor = new Color(205,133,63);
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
		 coneShift += 36;
		 cone = new Polygon(new int[] {x + 1, x + 16, x + 31}, new int[] {y - 40 + coneShift, y + coneShift, y - 40 + coneShift}, 3);
		 for (int i = 0; i < scoops.size(); i++)
	        {
	        	scoops.get(i).shiftScoopDown();
	        }
	 }
}
