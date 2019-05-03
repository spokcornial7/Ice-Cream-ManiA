import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class TimerListener implements ActionListener
{
	private GameMode game; 
	
	public TimerListener(GameMode g)
	{
		game = g; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		ArrayList<Scoop> scoops = game.getCircles(); 
		
		while(true)
		{
			for(int index = 0; index < scoops.size(); index++)
			{
				Scoop scoop = scoops.get(index);
			}
		}
		
		
	}

}
