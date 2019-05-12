/** Flavors.java Enum for the flavors of the ice cream scoops. 
 *  Has 5 flavors: BANANA, STRAWBERRY, MINT, ORANGE CREAM, BIRTHDAY CAKE, and includes a BOMB
 *  @author Carol Zeng 
 *  Collaborators: Lauren Ouyang, Helen Zhao
 *  Teacher: Mrs. Ishman
 *  Periods: 2, 3
 *  Due Date: 5/16/19
 */

import java.awt.Color;

public enum Flavors 
{	
	BANANA(new Color(249, 216, 52)),
	STRAWBERRY(new Color(255, 122, 122)), 
	MINT(new Color(185, 255, 105)), 
	ORANGE_CREAM(new Color(255, 166, 77)),
	BIRTHDAY_CAKE(new Color(46, 203, 234)), 
	BOMB(Color.black);
	
	private Color color;
	
	/** Creates a flavor with a given color
	 * @param color the color of the flavor
	 */
	Flavors(Color color)
	{
		this.color = color;
	}
	
	/** Returns the color corresponding with the flavor
	 *  @return the color of the flavor
	 */
	public Color getFlavor()
	{
		return this.color;
	}
}
