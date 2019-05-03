import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerExample implements KeyListener
{
	private GameMode game;
	public static final int speed = 5;
	
	public KeyListenerExample(GameMode g)
	{
		game = g; 

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) //right arrow code
		{
			game.MoveRight();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) //left arrow key
		{
			game.MoveLeft();
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
}
