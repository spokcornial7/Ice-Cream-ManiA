
public class GameMode 
{
	private int points;
	
	public GameMode()
	{
		setPoints(0); 
	}
	
	public void addPoints()
	{
		setPoints(getPoints() + 1);
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
