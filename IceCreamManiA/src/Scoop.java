
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
		mainCircle = new Ellipse2D.Double(x, y, 32, 32);
		leftCircle = new Ellipse2D.Double(x-1, y + 22, 12, 12);
		middleCircle = new Ellipse2D.Double(x + 11, y + 24, 12, 12);
		rightCircle = new Ellipse2D.Double(x + 23, y + 22, 12, 12); 
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
	
	public void dropDown (int yAmt)
	{
		y += yAmt;
		mainCircle.setFrame(mainCircle.getX(), mainCircle.getY() + yAmt, 48, 48);
		leftCircle.setFrame(leftCircle.getX(), leftCircle.getY() + yAmt, 20,  20);
		rightCircle.setFrame(rightCircle.getX(), rightCircle.getY() + yAmt,  20,  20);
		middleCircle.setFrame(middleCircle.getX(), middleCircle.getY() + yAmt, 20, 20);
		
	}
	
	public void shiftScoopDown()
	{
		y += 36;
	}
	
	public Rectangle getBoundingBox()
	{
		Rectangle box = new Rectangle(x - 1, y, 36, 36);
		return box;
	}
	

}
