import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GameViewer
{
	private GameMode game;
	//private String gameMode;

	private JFrame frame1;
	private JPanel welcomePanel;
	private String rules;
	private JButton btnTimed;
	private JButton btnClassic;
	private JPanel rulesPanel;
	private JButton btnBegin;
	
	private JPanel gamePanel;
	private JPanel endOptPanel;
	private JButton btnReplay;
	private JButton btnMenu;
	
	private static final int FRAME_X = 500;
	private static final int FRAME_Y = 100;
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 600;
	private static final int BTN_X = 150;
	
	public GameViewer() 
	{
		initialize();
		frame1.setVisible(true);
	}
	

	/** Initializes the game */
	private void initialize() {
		ButtonListener listener = new ButtonListener();
		
		frame1 = new JFrame();
		frame1.setResizable(false);
		frame1.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(new CardLayout(0, 0));
		
		// WELCOME PANEL
		welcomePanel = new JPanel();
		frame1.getContentPane().add(welcomePanel, "Welcome to Ice Cream Mania");
		welcomePanel.setLayout(null);
		welcomePanel.setVisible(true);
		
		btnTimed = new JButton("Timed");
		btnTimed.setBounds(BTN_X, 300, 150, 60);
		btnTimed.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		welcomePanel.add(btnTimed);
		btnTimed.addActionListener(listener);
		
		btnClassic = new JButton("Classic");
		btnClassic.setBounds(BTN_X, 410, 150, 60);
		btnClassic.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		welcomePanel.add(btnClassic);
		btnClassic.addActionListener(listener);
		
		// RULES PANEL
		rulesPanel = new JPanel();
		frame1.getContentPane().add(rulesPanel, "Rules");
		rulesPanel.setLayout(null);
		rulesPanel.setVisible(false);
		
		btnBegin = new JButton("Begin Game!");
		btnBegin.setBounds(BTN_X, 450, 150, 60);
		rulesPanel.add(btnBegin);
		btnBegin.addActionListener(listener);
		
		// ADDING IMAGE BACKGROUND
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		welcomePanel.add(layeredPane);
		ImageIcon img = new ImageIcon("/Users/carolzeng/workspace/COMSPCI/tumblr_p7ultlfFGw1x9wpiro1_500.jpg");
		JLabel lblBackground = new JLabel(img);
		lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		layeredPane.add(lblBackground);
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
   		//game.repaint();
    	gamePanel = new JPanel();
    	gamePanel.setLayout(null);
    	gamePanel.setVisible(true);
    	frame1.getContentPane().add(gamePanel, "Ice Cream Mania");
    	//frame1.add(game);
    	
    	JLabel lblScore = new JLabel("70");
		lblScore.setFont(new Font("Lucida Grande", Font.PLAIN, 70));
		lblScore.setBounds(300, 200, 50, 52);
		endOptPanel.add(lblScore);
	
		JLabel lblHighscore = new JLabel("70");
		lblHighscore.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		lblHighscore.setBounds(300, 87, 32, 41);
		endOptPanel.add(lblHighscore);
    	
    	/*if(game.isGameOver())
    	{
    		gamePanel.setVisible(false);
    		endGameOpt();
    	}*/
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
				timedRules.setBounds(40, 90, 330, 300);
				rulesPanel.add(timedRules);
				game =  new GameModeTimed();
				//gameMode = "timed";
			}
			else if(e.getSource() == btnClassic){
				rulesPanel.setVisible(true);
				welcomePanel.setVisible(false);
				createCRules();
				JLabel classicRules = new JLabel(rules);
				classicRules.setBounds(40, 90, 330, 300);
				rulesPanel.add(classicRules);
				game = new GameModeClassic();
				//gameMode = "classic";
			}
			else if(e.getSource() == btnBegin){
				rulesPanel.setVisible(false);
				createGame();
			}
			else if(e.getSource() == btnReplay){
				endOptPanel.setVisible(false);
				createGame();
			}
			else if(e.getSource() == btnMenu){
				endOptPanel.setVisible(false);
				initialize();
			}
		}
    	
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void endGameOpt() {
		
		endOptPanel = new JPanel();
		frame1.getContentPane().add(endOptPanel, "Thanks for playing!");
		endOptPanel.setLayout(null);
		endOptPanel.setVisible(true);
		
		/*JLabel lblScore = new JLabel(game.getScore());
		lblScore.setFont(new Font("Lucida Grande", Font.PLAIN, 70));
		lblScore.setBounds(300, 200, 50, 52);
		endOptPanel.add(lblScore);*/
	
		/*JLabel lblHighscore = new JLabel(game.getHighScore());
		lblHighscore.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		lblHighscore.setBounds(300, 87, 32, 41);
		endOptPanel.add(lblHighscore);*/
		
		btnReplay = new JButton("REPLAY");
		btnReplay.setBounds(90, 179, 117, 29);
		endOptPanel.add(btnReplay);
		
		btnMenu = new JButton("MENU");
		btnMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 7));
		btnMenu.setBounds(90, 220, 117, 29);
		endOptPanel.add(btnMenu);
		
		ImageIcon img = new ImageIcon("/Users/carolzeng/workspace/COMSPCI/tumblr_p7ultlfFGw1x9wpiro1_500.jpg");
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1, 1);
		endOptPanel.add(layeredPane);
		
		JLabel lblNewLabel_1 = new JLabel(img);
		lblNewLabel_1.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		endOptPanel.add(lblNewLabel_1);
	}

	private void createTimer()
	{
		JLabel timerLabel = new JLabel();
	}
	
	
	public static void main(String[] args) 
	{
		GameViewer gameGame = new GameViewer();	
	}
	
}
