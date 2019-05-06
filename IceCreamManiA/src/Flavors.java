import java.awt.Color;


public enum Flavors 
{	
	CHOCOLATE(new Color(172, 115, 57)), 
	BANANA(new Color(249, 216, 52)),
	STRAWBERRY(new Color(198, 123, 138)), 
	VANILLA(new Color(238, 225, 165)), 
	MINT(new Color(170, 219, 132)), 
	ORANGE_CREAM(new Color(255, 166, 77)),
	BIRTHDAY_CAKE(new Color(46, 203, 234)), 
	PARTY(new Color(116, 18, 186)),
	BOMB(Color.black);
	
	private Color color;
	
	/** Create node type with a given symbol 
	 *  @param symbol the symbol associated with this type
	 */
	Flavors(Color color)
	{
		this.color = color;
	}
	
	/** @return this type's symbol
	 */
	public Color getFlavor()
	{
		return this.color;
	}
}
