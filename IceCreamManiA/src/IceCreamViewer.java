import java.awt.Component;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class IceCreamViewer 
{
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 600;
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(450,600);
		frame.setLocation(0,0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameMode game = new GameModeClassic();
		frame.add(game);
		System.out.println(game.getHeight());
		System.out.println(game.getWidth());
		
		frame.setVisible(true);
}
}