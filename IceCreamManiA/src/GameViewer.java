import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GameViewer
{
	private GameMode game;
	private String gameMode;
	
	private JFrame frame1;
	private JPanel welcomePanel;
	private String rules;
	private JButton btnTimed;
	private JButton btnClassic;
	private JPanel rulesPanel;
	private JButton btnBegin;
	
	private JFrame frame2;
	
	private JFrame frame3;
	
	public GameViewer() 
	{
		initialize();
		frame1.setVisible(true);
		
		JButton restart = new JButton("Restart");
		JButton diff = new JButton("Different Game Mode");
		JButton exitB = new JButton("Exit Game");
	}
	

	/** Initializes the game */
	private void initialize() {
		ButtonListener listener = new ButtonListener();
		
		frame1 = new JFrame();
		frame1.setBounds(100, 100, 420, 390);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(new CardLayout(0, 0));
		
		// WELCOME PANEL
		welcomePanel = new JPanel();
		frame1.getContentPane().add(welcomePanel, "Welcome");
		welcomePanel.setLayout(null);
		welcomePanel.setVisible(true);
		
		JLabel lblWelcome = new JLabel("WELCOME TO ICECREAM MANIA");
		lblWelcome.setBounds(49, 55, 313, 31);
		welcomePanel.add(lblWelcome);
		lblWelcome.setFont(new Font("Desdemona", Font.BOLD, 26));
		
		JLabel lblChooseAGame = new JLabel("Choose a Game Mode");
		lblChooseAGame.setBounds(139, 123, 135, 16);
		welcomePanel.add(lblChooseAGame);
		
		btnTimed = new JButton("Timed");
		btnTimed.setBounds(160, 180, 90, 30);
		welcomePanel.add(btnTimed);
		btnTimed.addActionListener(listener);
		
		btnClassic = new JButton("Classic");
		btnClassic.setBounds(157, 250, 90, 30);
		welcomePanel.add(btnClassic);
		btnClassic.addActionListener(listener);
		
		// RULES PANEL
		rulesPanel = new JPanel();
		frame1.getContentPane().add(rulesPanel, "Rules");
		rulesPanel.setLayout(null);
		rulesPanel.setVisible(false);
		
		btnBegin = new JButton("Begin Game!");
		btnBegin.setBounds(150, 310, 120, 30);
		rulesPanel.add(btnBegin);
		btnBegin.addActionListener(listener);
		
		// ADDING IMAGE BACKGROUND
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 420, 368);
		welcomePanel.add(layeredPane);
		ImageIcon img = new ImageIcon("/Users/carolzeng/workspace/COMSPCI/tumblr_p7ultlfFGw1x9wpiro1_500.jpg");
		JLabel lblNewLabel = new JLabel(img);
		lblNewLabel.setBounds(0, 0, 420, 368);
		layeredPane.add(lblNewLabel);
	}
	
	/** Read in timed mode rules */
	private void createTRules()
	{
		try {
			rules = new Scanner(new File("TimerModeRules.txt")).nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** Read in classic mode rules */
	private void createCRules()
	{
		try {
			rules = new Scanner(new File("ClassicModeRules.txt")).nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
    
    
    private void createGame()
    {
    	frame2 = new JFrame("Ice Cream Mania");
    	frame2.setSize(500, 500);
    	frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame2.getContentPane().add(game);
    }
    
    private void endGameOpt()
    {
    	frame3 = new JFrame();
    	frame3.setBounds(100, 100, 420, 390);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame3.getContentPane();
    }
    
    private class ButtonListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == btnTimed){
				rulesPanel.setVisible(true);
				welcomePanel.setVisible(false);
				createTRules();
				JLabel timedRules = new JLabel(rules);
				timedRules.setBounds(35, 40, 360, 260);
				rulesPanel.add(timedRules);
				game = new GameModeTimed();
			}
			else if(e.getSource() == btnClassic){
				rulesPanel.setVisible(true);
				welcomePanel.setVisible(false);
				createCRules();
				JLabel classicRules = new JLabel(rules);
				classicRules.setBounds(35, 40, 360, 260);
				rulesPanel.add(classicRules);
				game = new GameModeClassic();
			}
			else if(e.getSource() == btnBegin){
				frame1.setVisible(false);
				createGame();
			}
		}
    	
    }
    
	/** Returns game mode 
	 *  @return game mode
	 */
    public String getGameMode()
    {
    	return gameMode;
    }
    
	public static void main(String[] args) 
	{
		GameViewer gameGame = new GameViewer();	
	}

}
