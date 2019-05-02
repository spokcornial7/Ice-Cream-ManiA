
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Scoop 
{
	private int x;
	private int y;
	private int numFlavor;
	
	private Ellipse2D.Double mainCircle; 
	private Ellipse2D.Double leftCircle; 
	private Ellipse2D.Double middleCircle;
	private Ellipse2D.Double rightCircle;

	public Scoop(int x, int y, int numFlavor)
	{
		this.x = x; 
		this.y = y; 
		this.numFlavor = numFlavor; 
		mainCircle = new Ellipse2D.Double(x, y, 48, 48);
		leftCircle = new Ellipse2D.Double(x - 1, y + 30, 20, 20);
		middleCircle = new Ellipse2D.Double(x + 15, y + 32, 20, 20);
		rightCircle = new Ellipse2D.Double(x + 31, y + 30, 20, 20); 
	}
	
	public void draw(Graphics2D gr)
	{
		gr.setColor(getColorFlavor());
		gr.fill(mainCircle);
		gr.fill(leftCircle);
		gr.fill(rightCircle);
		gr.fill(middleCircle);
	}
	
	public Color getColorFlavor()
	{
		Color iceCreamFlavor = null; 
		if (numFlavor == 0)
			return Flavors.CHOCOLATE.getFlavor();
		else if (numFlavor == 1)
			iceCreamFlavor = Flavors.BANANA.getFlavor();
		else if (numFlavor == 2)
			iceCreamFlavor = Flavors.STRAWBERRY.getFlavor();
		else if (numFlavor == 3)
			iceCreamFlavor = Flavors.VANILLA.getFlavor();
		else if (numFlavor == 4)
			iceCreamFlavor = Flavors.MINT.getFlavor();
		else if (numFlavor == 5)
			iceCreamFlavor = Flavors.ORANGE_CREAM.getFlavor();
		else if (numFlavor == 6)
			iceCreamFlavor = Flavors.BIRTHDAY_CAKE.getFlavor();
		else
			iceCreamFlavor = Flavors.PARTY.getFlavor(); 
		return iceCreamFlavor; 
		
	}
	
		/*Scoop Class copied from the Classes doc does not necessarily follow everything on here
		-Circle Class
		-Constants: Big circle dia, small circlesdia; width, height, length and width of chips
		-instance variables: String flavor, Circle, left-middle-right circle, boxX, boxY
		-Constructor (int x, int y, int flavor): 
				take in a number for that flavor- grab the enum of that number and assign it to the flavor instance variable
				boxX = x;
				boxY = y;
		-public String getFlavor()
				returns flavor so that in game, can check if correct flavor was added on
		public void drop(int amount)
				boxY += amount;
		shift down()
				boxY -= (size of diam + small circles)
		-public void drawScoop(Graphics2D gr)
		draw a scoop with the color of the flavor
			i.e. if(flavor == Flavor.CHOCOLATE) => getFlavor() circle brown
		
		 *//** Retrieves the shape's bounding rectangle
		 *  @return the bounding rectangle that encompasses this shape
		 *//*
	 	public Rectangle getBox()
	 	{
	    	Rectangle box = new Rectangle(boxX, boxY, WIDTH, HEIGHT);
			return box;
		}*/

}
