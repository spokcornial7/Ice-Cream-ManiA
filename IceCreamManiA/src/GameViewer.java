/** GameViewer.java manages the game. It displays the game and leads the user to their
 *  desired game mode. It also allows the user to restart the game or return to the menu
 *  to choose a new game mode. It also displays the rules of each game mode for the user
 *  @author Carol Zeng 
 *  Collaborators: Helen Zhao, Lauren Ouyang
 *  Teacher: Mrs. Ishman
 *  Periods: 2, 3
 *  Due Date: 5/16/19
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GameViewer
{	
	// Frame and Button constants
	private static final int FRAME_X = 500;
	private static final int FRAME_Y = 100;
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 600;
	private static final Rectangle BTN1_BOUNDS = new Rectangle(150, 350, 150, 60);
	private static final Rectangle BTN2_BOUNDS = new Rectangle(150, 450, 150, 60);
	private static final Rectangle HIGHSCORE_LABEL_BOUNDS = new Rectangle(250, 185, 70, 70);
	private static final Rectangle SCORE_LABEL_BOUNDS = new Rectangle(250, 155, 70, 70);
	private static final Rectangle RULES_BOUNDS = new Rectangle(30, 90, 330, 300);
	private static final Font SCORE_FONT = new Font("Lucida Grande", Font.BOLD, 30);
	private static final Font HISCORE_FONT = new Font("Lucida Grande", Font.BOLD, 25);
	
	// Game instance variables
	private GameMode game;
	private String gameMode;

	// Frame and Button instance variables
	private JFrame frame1;
	private JFrame gameFrame;
	private JPanel welcomePanel;
	private JButton btnTimed;
	private JButton btnClassic;
	private JPanel rulesPanel;
	private JButton btnBegin;	
	private JPanel endOptPanel;
	private JButton btnReplay;
	private JButton btnMenu;
	private JLayeredPane layeredPaneR;
	
	/** Creates a GameViewer object */
	public GameViewer() 
	{
		initialize();
	}
	
	/** Initializes the game */
	private void initialize() 
	{
		ButtonListener listener = new ButtonListener();
		
		frame1 = new JFrame();
		frame1.setResizable(false);
		frame1.setVisible(true);
		frame1.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(new CardLayout(0, 0));
		
		gameFrame = new JFrame();
		gameFrame.setResizable(false);
		gameFrame.setVisible(false);
		gameFrame.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.getContentPane().setLayout(null);
		
		createWelcomePanel(listener);
		createRulesPanel(listener);
	}
	
	/**
	 * Creates the welcome panel
	 * @param listener is the button listener
	 */
	private void createWelcomePanel(ButtonListener listener)
	{
		welcomePanel = new JPanel();
		frame1.getContentPane().add(welcomePanel, "Welcome to Ice Cream Mania");
		welcomePanel.setLayout(null);
		welcomePanel.setVisible(true);
		
		btnTimed = new JButton("Timed");
		btnTimed.setBounds(BTN1_BOUNDS);
		welcomePanel.add(btnTimed);
		btnTimed.addActionListener(listener);
		
		btnClassic = new JButton("Classic");
		btnClassic.setBounds(BTN2_BOUNDS);
		welcomePanel.add(btnClassic);
		btnClassic.addActionListener(listener);
		
		JLayeredPane layeredPaneW = new JLayeredPane();
		layeredPaneW.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		welcomePanel.add(layeredPaneW);
		ImageIcon img = new ImageIcon("Welcome.JPG");
		JLabel lblBackground = new JLabel(img);
		lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		layeredPaneW.add(lblBackground);
	}
	
	/**
	 * Creates the rules panel
	 * @param listener is the button listener
	 */
	private void createRulesPanel(ButtonListener listener)
	{
		rulesPanel = new JPanel();
		frame1.getContentPane().add(rulesPanel, "Rules");
		rulesPanel.setLayout(null);
		rulesPanel.setVisible(false);
		
		btnBegin = new JButton("Begin Game!");
		btnBegin.setBounds(BTN2_BOUNDS);
		rulesPanel.add(btnBegin);
		btnBegin.addActionListener(listener);
		
		layeredPaneR = new JLayeredPane();
		layeredPaneR.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		ImageIcon img = new ImageIcon("Rules.jpeg");
		JLabel lblBackground = new JLabel(img);
		lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		layeredPaneR.add(lblBackground);
	}
	
	/** Creates the game frame	
	 */
    private void createGame()
    {
    	if(gameMode.equals("classic"))
    		game = new GameModeClassic(this);
    	else
    		game = new GameModeTimed(this);
    	
    	game.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
    	gameFrame.getContentPane().add(game);    	
    
    	startGame();
    }
    
    /** Starts the game with the menu
     */
    private void startGame()
    {
    	gameFrame.setVisible(true);  
		
    	if(gameMode.equals("classic"))
		{
			ImageIcon img = new ImageIcon("Classic.JPG");
			JLabel lblBackground = new JLabel(img);
			lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
			gameFrame.add(lblBackground);
		}
		else
		{			
			ImageIcon img = new ImageIcon("TIMED.JPG");
			JLabel lblBackground = new JLabel(img);
			lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
			gameFrame.add(lblBackground);
		}
    }
    
    /** Creates the End Game Options Panel
	 *  @param listener is the button listener
	 */
	private void endGameOpt(ButtonListener listener) 
	{	
		endOptPanel = new JPanel();
		frame1.getContentPane().add(endOptPanel, "Thanks for playing!");
		endOptPanel.setLayout(null);
		endOptPanel.setVisible(true);
		
		gameFrame.setVisible(false);
		frame1.setVisible(true);
		welcomePanel.setVisible(false);
		
		addEndLabels();
		
		btnReplay = new JButton("Replay");
		btnReplay.setBounds(BTN1_BOUNDS);
		endOptPanel.add(btnReplay);
		btnReplay.addActionListener(listener);
		
		btnMenu = new JButton("Menu");
		btnMenu.setBounds(BTN2_BOUNDS);
		endOptPanel.add(btnMenu);
		btnMenu.addActionListener(listener);
		
		ImageIcon img = new ImageIcon("End Screen.JPG");
		JLayeredPane layeredPaneE = new JLayeredPane();
		layeredPaneE.setBounds(0, 0, 1, 1);
		endOptPanel.add(layeredPaneE);
		JLabel lblBackground = new JLabel(img);
		lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		endOptPanel.add(lblBackground);
	}
	
	/** Read in timed mode rules 
	 */
	private void createTRules()
	{
		try {
			Scanner scan = new Scanner(new File("TimerModeRules.txt"));
			JLabel timedRules = new JLabel(scan.nextLine());
			timedRules.setBounds(RULES_BOUNDS);
			rulesPanel.add(timedRules);
			rulesPanel.add(layeredPaneR);
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** Read in classic mode rules 
	 */
	private void createCRules()
	{
		try {
			Scanner scan = new Scanner(new File("ClassicModeRules.txt"));
			JLabel classicRules = new JLabel(scan.nextLine());
			classicRules.setBounds(RULES_BOUNDS);
			rulesPanel.add(classicRules);
			rulesPanel.add(layeredPaneR);
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** Adds score labels to the end option panel 
	 */
	private void addEndLabels()
	{
		JLabel lblScore = new JLabel(String.valueOf(game.getPoints()));
		lblScore.setFont(SCORE_FONT);
		lblScore.setForeground(Color.white);
		lblScore.setBounds(SCORE_LABEL_BOUNDS);
		endOptPanel.add(lblScore);
	
		JLabel lblHighscore = new JLabel(String.valueOf(game.getHighScore()));
		lblHighscore.setFont(HISCORE_FONT);
		lblHighscore.setForeground(Color.white);
		lblHighscore.setBounds(HIGHSCORE_LABEL_BOUNDS);
		endOptPanel.add(lblHighscore);	
	}
	
	/** Ends the game
	 */
	public void endGame()
	{
		endGameOpt(new ButtonListener());
	}
	
	/** ButtonListener listens to the button pressed and brings user to the game mode,
	 *  the menu, or back to the game
	 */
    private class ButtonListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == btnTimed){
				rulesPanel.setVisible(true);
				welcomePanel.setVisible(false);
				createTRules();
				gameMode = "timed";
			}
			else if(e.getSource() == btnClassic){
				rulesPanel.setVisible(true);
				welcomePanel.setVisible(false);
				createCRules();
				gameMode = "classic";
			}
			else if(e.getSource() == btnBegin){
				rulesPanel.setVisible(false);
				frame1.setVisible(false);
				createGame();
			}
			else if(e.getSource() == btnReplay){
				endOptPanel.setVisible(false);
				game.reset();
				startGame();
			}
			else if(e.getSource() == btnMenu){
				endOptPanel.setVisible(false);
				initialize();
			}
		}
    }
    
    /** Returns the frame
     * @return the first frame
     */
    public JFrame getFrame1()
    {
    	return frame1;
    }
	
    /** Returns the game's frame
     * @return the game frame
     */
    public JFrame getGameFrame()
    {
    	return gameFrame;
    }
    
 
	public static void main(String[] args) 
	{
		GameViewer gameGame = new GameViewer();	
	}
	
}
