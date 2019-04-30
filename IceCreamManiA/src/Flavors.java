import java.awt.Color;


public enum Flavors 
{	
	CHOCOLATE(new Color(78, 58, 24)), 
	BANANA(new Color(236, 218, 60)),
	STRAWBERRY(new Color(198, 123, 138)), 
	VANILLA(Color.white), 
	MINT(new Color(170, 219, 132)), 
	ORANGE_CREAM(new Color(255, 198, 41)),
	BIRTHDAY_CAKE(new Color(46, 203, 234)), 
	PARTY(new Color(116, 18, 186));
	
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
