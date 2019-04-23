import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GameViewer extends JFrame
{
	
	public GameViewer() 
	{
		// TODO Auto-generated constructor stub
		JOptionPane.showMessageDialog(null, "");
		JOptionPane.showMessageDialog(null, "");
	}

	public static void main(String[] args) 
	{
		JPanel pane1 = new JPanel();
		JLabel welcome = new JLabel("Welcome to Ice Cream Mania!");
		JButton mode1 = new JButton("Timer");
		JButton mode2 = new JButton("Classic");
		pane1.add(welcome);
		pane1.add(mode1);
		pane1.add(mode2);
		
		JOptionPane.showMessageDialog(null, "");
		
		JButton restart = new JButton("Restart");
		JButton diff = new JButton("Different Game Mode");
		JButton exitB = new JButton("Exit Game");
	
	}

}
