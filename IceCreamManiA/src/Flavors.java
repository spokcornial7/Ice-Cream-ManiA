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
