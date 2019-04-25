import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GameViewer extends JFrame
{
	JPanel pane1;
	GameMode game;
	
	public GameViewer() 
	{
		setSize(500, 500);
		setTitle("Ice Cream Mania");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		askGameMode();
				
		JOptionPane.showMessageDialog(null, "");
		
		JButton restart = new JButton("Restart");
		JButton diff = new JButton("Different Game Mode");
		JButton exitB = new JButton("Exit Game");
	}
	
	private void askGameMode()
	{
		pane1 = new JPanel();
		JLabel welcome = new JLabel("Welcome to Ice Cream Mania!");
		JButton mode1 = new JButton("Timer");
		JButton mode2 = new JButton("Classic");
		pane1.add(welcome);
		pane1.add(mode1);
		pane1.add(mode2);
		add(pane1);
		mode1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				game = new GameModeTimer();
			}
		});
		mode2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				game = new GameModeClassic();
			}
		});
	}

	public static void main(String[] args) 
	{
		GameViewer game = new GameViewer();
		game.setVisible(true);	
	}

}
