import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JComponent;

public class IceCream extends JComponent 
{
	private ArrayList<Scoop> scoops; 
	
	public IceCream()
	{
		scoops = new ArrayList<>();
		int y = 305;
		for (int i = 0; i < 3; i++)
		{
			int randFlavor = (int) (Math.random() * 8);
			scoops.add(new Scoop(160, y, randFlavor));
			y = y - 50;
		}

	}
	 public void paintComponent(Graphics gr)
	    {
	        Graphics2D g2 = (Graphics2D) gr;
	        Polygon cone = new Polygon(new int[] {160, 175, 190}, new int[] {500, 550, 500}, 3);
	        Color coneColor = new Color(205,133,63);
	        gr.setColor(coneColor);
	        gr.fillPolygon(cone);
	        for (int i = 0; i < scoops.size(); i++)
	        {
	        	scoops.get(i).draw(g2);
	        }
	    	


	    }
}
