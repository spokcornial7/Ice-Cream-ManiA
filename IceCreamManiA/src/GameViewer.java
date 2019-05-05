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
	private static final int BTN_WIDTH = 150;
	private static final int BTN_HEIGHT = 60;
	
	
	private JLayeredPane layeredPaneR;
	
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
		
		createWelcomePanel(listener);
		createRulesPanel(listener);
		
	}
	
	private void createWelcomePanel(ButtonListener listener)
	{
		welcomePanel = new JPanel();
		frame1.getContentPane().add(welcomePanel, "Welcome to Ice Cream Mania");
		welcomePanel.setLayout(null);
		welcomePanel.setVisible(true);
		
		btnTimed = new JButton("Timed");
		btnTimed.setBounds(BTN_X, 350, BTN_WIDTH, BTN_HEIGHT);
		btnTimed.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		welcomePanel.add(btnTimed);
		btnTimed.addActionListener(listener);
		
		btnClassic = new JButton("Classic");
		btnClassic.setBounds(BTN_X, 450, BTN_WIDTH, BTN_HEIGHT);
		btnClassic.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		welcomePanel.add(btnClassic);
		btnClassic.addActionListener(listener);
		
		JLayeredPane layeredPaneW = new JLayeredPane();
		layeredPaneW.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		welcomePanel.add(layeredPaneW);
		ImageIcon img = new ImageIcon("IMG_9048.JPG");
		JLabel lblBackground = new JLabel(img);
		lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		layeredPaneW.add(lblBackground);
	}
	
	private void createRulesPanel(ButtonListener listener)
	{
		rulesPanel = new JPanel();
		frame1.getContentPane().add(rulesPanel, "Rules");
		rulesPanel.setLayout(null);
		rulesPanel.setVisible(false);
		
		btnBegin = new JButton("Begin Game!");
		btnBegin.setBounds(BTN_X, 450, BTN_WIDTH, BTN_HEIGHT);
		rulesPanel.add(btnBegin);
		btnBegin.addActionListener(listener);
		
		layeredPaneR = new JLayeredPane();
		layeredPaneR.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		ImageIcon img = new ImageIcon("IMG_9059.JPG");
		JLabel lblBackground = new JLabel(img);
		lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		layeredPaneR.add(lblBackground);
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
   		game.repaint();
    	gamePanel = new JPanel();
    	frame1.getContentPane().add(gamePanel, "Ice Cream Mania");
    	gamePanel.setLayout(null);
    	gamePanel.setVisible(true);
    	gamePanel.add(game);
    	
    	
    	JLabel lblScore = new JLabel("70");
		lblScore.setFont(new Font("Lucida Grande", Font.PLAIN, 70));
		lblScore.setBounds(300, 200, 50, 52);
		gamePanel.add(lblScore);
	
		JLabel lblHighscore = new JLabel("70");
		lblHighscore.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		lblHighscore.setBounds(300, 87, 32, 41);
		gamePanel.add(lblHighscore);
    	
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
				timedRules.setBounds(30, 90, 330, 300);
				rulesPanel.add(timedRules);
				rulesPanel.add(layeredPaneR);
				game =  new GameModeTimed();
				//gameMode = "timed";
			}
			else if(e.getSource() == btnClassic){
				rulesPanel.setVisible(true);
				welcomePanel.setVisible(false);
				createCRules();
				JLabel classicRules = new JLabel(rules);
				classicRules.setBounds(30, 90, 330, 300);
				rulesPanel.add(classicRules);
				rulesPanel.add(layeredPaneR);
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
	private void endGameOpt() 
	{
		endOptPanel = new JPanel();
		frame1.getContentPane().add(endOptPanel, "Thanks for playing!");
		endOptPanel.setLayout(null);
		endOptPanel.setVisible(true);
		
		/*JLabel lblScore = new JLabel(game.getPoints());
		lblScore.setFont(new Font("Lucida Grande", Font.PLAIN, 70));
		lblScore.setBounds(300, 200, 50, 52);
		endOptPanel.add(lblScore);
	
		JLabel lblHighscore = new JLabel(game.getHighScore());
		lblHighscore.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		lblHighscore.setBounds(300, 87, 32, 41);
		endOptPanel.add(lblHighscore);*/
		
		btnReplay = new JButton("REPLAY");
		btnReplay.setBounds(BTN_X, 179, BTN_WIDTH, BTN_HEIGHT);
		endOptPanel.add(btnReplay);
		
		btnMenu = new JButton("MENU");
		btnMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 7));
		btnMenu.setBounds(BTN_X, 220, BTN_WIDTH, BTN_HEIGHT);
		endOptPanel.add(btnMenu);
		
		ImageIcon img = new ImageIcon("IMG_9061.JPG");
		JLayeredPane layeredPaneE = new JLayeredPane();
		layeredPaneE.setBounds(0, 0, 1, 1);
		endOptPanel.add(layeredPaneE);
		JLabel lblBackground = new JLabel(img);
		lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		endOptPanel.add(lblBackground);
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
