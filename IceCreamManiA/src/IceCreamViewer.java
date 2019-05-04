import java.awt.Component;

import javax.swing.JFrame;

public class IceCreamViewer 
{
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 600;
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Car Race");
		IceCream ice = new IceCream(209, 400); 
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocation(5, 5);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(ice);
		frame.setVisible(true);
}
}