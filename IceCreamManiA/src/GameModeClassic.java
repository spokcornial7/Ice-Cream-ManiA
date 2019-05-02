
import java.awt.*;

import javax.swing.*;


public class GameModeClassic extends GameMode
{

	public GameModeClassic() {
		// TODO Auto-generated constructor stub
	}

	
	
	public static void main(String[] args) {
		ImageIcon check = new ImageIcon("checkmark.png");
		Image image = check.getImage();
		Image newimg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		check = new ImageIcon(newimg); 
		JLabel pane = new JLabel(check);
		JFrame f = new JFrame();
		f.add(pane);
		f.setSize(300, 300);
		f.setVisible(true);
		
    }
}
