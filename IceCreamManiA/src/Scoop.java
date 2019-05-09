
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
	private static final int MAIN_CIRCLE_DIA = 32; 
	private static final int SMALL_CIRCLE_DIA = 12; 
	private static final int LEFT_CIRCLE_X_SHIFT = 2;
	private static final int LEFTRIGHT_CIRCLE_XY_SHIFT = 22; 
	private static final int MID_CIRCLE_X_SHIFT = 10; 
	private static final int MID_CIRCLE_Y_SHIFT = 24; 
	private static final int SCOOP_XY_LENGTH = 36;
	private static final int SHIFT_AMT = 10;
	

	private static final int NUM_BANANA = 0;
	private static final int NUM_STRAWBERRY = 1; 
	private static final int NUM_MINT = 2; 
	private static final int NUM_ORANGE = 3;
	private static final int NUM_BIRTHDAY = 4; 
	
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

		mainCircle = new Ellipse2D.Double(x, y, MAIN_CIRCLE_DIA, MAIN_CIRCLE_DIA);
		leftCircle = new Ellipse2D.Double(x - LEFT_CIRCLE_X_SHIFT, y + LEFTRIGHT_CIRCLE_XY_SHIFT, 
									SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA);
		middleCircle = new Ellipse2D.Double(x +  MID_CIRCLE_X_SHIFT, y + MID_CIRCLE_Y_SHIFT, 
									SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA);
		rightCircle = new Ellipse2D.Double(x + LEFTRIGHT_CIRCLE_XY_SHIFT, 
						y + LEFTRIGHT_CIRCLE_XY_SHIFT, SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA); 

	}
	
	public void draw(Graphics2D gr)
	{
		mainCircle = new Ellipse2D.Double(x, y, MAIN_CIRCLE_DIA, MAIN_CIRCLE_DIA);
		leftCircle = new Ellipse2D.Double(x-LEFT_CIRCLE_X_SHIFT, y + LEFTRIGHT_CIRCLE_XY_SHIFT, SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA);
		middleCircle = new Ellipse2D.Double(x +  MID_CIRCLE_X_SHIFT, y + MID_CIRCLE_Y_SHIFT, SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA);
		rightCircle = new Ellipse2D.Double(x + LEFTRIGHT_CIRCLE_XY_SHIFT, y + LEFTRIGHT_CIRCLE_XY_SHIFT, SMALL_CIRCLE_DIA, SMALL_CIRCLE_DIA);
		gr.setColor(getColorFlavor());
		gr.fill(mainCircle);
		gr.fill(leftCircle);
		gr.fill(rightCircle);
		gr.fill(middleCircle);
	}
	
	public int getFlavor()
	{
		return numFlavor;
	}
	
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
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	public void dropDown (int yAmt)
	{
		y += yAmt;
	}
	

	public void shiftScoopDown()
	{
		y += getBoundingBox().getHeight();
	}

	public void shiftScoopRight()
	{
		x += SHIFT_AMT; 
	}
	

	public void shiftScoopLeft()
	{
		x -= SHIFT_AMT; 
	}
	

	public int scoopDiameter()
	{
		return SCOOP_XY_LENGTH;
	}
	

	public Rectangle getBoundingBox()
	{
		Rectangle box = new Rectangle(x - 1, y, SCOOP_XY_LENGTH, SCOOP_XY_LENGTH);
		return box;
	}
	

}
