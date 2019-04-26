import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
				
		//JOptionPane.showMessageDialog(null, "");
		
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
	
	public void listDictionary()throws IOException
	{
	    BufferedReader br = new BufferedReader(new FileReader("ClassicModeRules.txt"));
	    String aLineFromFile = null;
	    while ((br.readLine()) != null){
	            aLineFromFile += br.readLine();
	    }
	    JOptionPane.showMessageDialog(null, aLineFromFile);
	    br.close();
	    return;
	}
	
	private String text;

    public void readFileContent() throws FileNotFoundException{

      // text = new Scanner( new File("ClassicModeRules.txt") ).useDelimiter("<br>").next();
       text = new Scanner( new File("TimerModeRules.txt") ).useDelimiter("<br>").next();
       JOptionPane.showMessageDialog(null, text, "Rules", JOptionPane.INFORMATION_MESSAGE);
    }

	public static void main(String[] args) 
	{
		GameViewer game = new GameViewer();
		game.setVisible(true);	
	
			try {
				game.readFileContent();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
