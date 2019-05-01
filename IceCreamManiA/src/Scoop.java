
public class Scoop {

	public Scoop(int x, int y, int numFlavor)
	{
		
	}
	
	public void draw()
	{
		
	}
	
	
	
	
		/*Scoop Class copied from the Classes doc does not necessarily follow everything on here
		-Circle Class
		-Constants: Big circle dia, small circles’ dia; width, height, length and width of chips
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
