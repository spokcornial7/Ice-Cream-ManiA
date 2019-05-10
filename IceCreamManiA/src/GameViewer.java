import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GameViewer
{
	private GameMode game;
	private String gameMode;
	//private Timer gameTimer;

	private JFrame frame1;
	private JFrame frame2;
	private JPanel welcomePanel;
	private JButton btnTimed;
	private JButton btnClassic;
	private JPanel rulesPanel;
	private JButton btnBegin;
	
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
		
		frame2 = new JFrame();
		frame2.setResizable(false);
		frame2.setVisible(false);
		frame2.setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		
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
		btnBegin.setBounds(BTN_X, 450, BTN_WIDTH, BTN_HEIGHT);
		rulesPanel.add(btnBegin);
		btnBegin.addActionListener(listener);
		
		layeredPaneR = new JLayeredPane();
		layeredPaneR.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		ImageIcon img = new ImageIcon("Rules.jpeg");
		JLabel lblBackground = new JLabel(img);
		lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		layeredPaneR.add(lblBackground);
	}
	
	/** 
	 * 	Creates the game frame.
	 */
    private void createGame()
    {
    	if(gameMode.equals("classic"))
    		game = new GameModeClassic();
    	else
    		game = new GameModeTimed();
    	
    	game.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
    	frame2.setVisible(true);
    	frame2.getContentPane().add(game);
    	game.repaint();
    	
    	frame2.add(game.scoreLabel());
    	frame2.add(game.highScoreLabel());
    	
		if(gameMode.equals("classic")){
			ImageIcon img = new ImageIcon("Classic.JPG");
			JLabel lblBackground = new JLabel(img);
			lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
			frame2.add(lblBackground);}
		
		if(gameMode.equals("timed")){
			ImageIcon img = new ImageIcon("Classic.JPG");
			JLabel lblBackground = new JLabel(img);
			lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
			createTimer();
			frame2.add(lblBackground);
			}    	
		
		/*frame2.setVisible(false);
		frame1.setVisible(true);
		welcomePanel.setVisible(false);
		endGameOpt(new ButtonListener());*/
    }
    
    /**
	 * Creates the End Game Options Panel
	 * @param listener is the button listener
	 */
	private void endGameOpt(ButtonListener listener) 
	{
		endOptPanel = new JPanel();
		frame1.getContentPane().add(endOptPanel, "Thanks for playing!");
		endOptPanel.setLayout(null);
		endOptPanel.setVisible(true);
		
		JLabel lblScore = new JLabel(String.valueOf(game.getPoints()));
		lblScore.setFont(new Font("Lucida Grande", Font.BOLD, 70));
		lblScore.setBounds(300, 200, 50, 52);
		endOptPanel.add(lblScore);
	
		JLabel lblHighscore = new JLabel(String.valueOf(game.getHighScore()));
		lblHighscore.setFont(new Font("Lucida Grande", Font.BOLD, 50));
		lblHighscore.setBounds(300, 87, 32, 41);
		endOptPanel.add(lblHighscore);
		
		btnReplay = new JButton("REPLAY");
		btnReplay.setBounds(BTN_X, 350, BTN_WIDTH, BTN_HEIGHT);
		endOptPanel.add(btnReplay);
		btnReplay.addActionListener(listener);
		
		btnMenu = new JButton("MENU");
		btnMenu.setBounds(BTN_X, 450, BTN_WIDTH, BTN_HEIGHT);
		endOptPanel.add(btnMenu);
		btnMenu.addActionListener(listener);
		
		ImageIcon img = new ImageIcon("Classic.JPG");
		JLayeredPane layeredPaneE = new JLayeredPane();
		layeredPaneE.setBounds(0, 0, 1, 1);
		endOptPanel.add(layeredPaneE);
		JLabel lblBackground = new JLabel(img);
		lblBackground.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		endOptPanel.add(lblBackground);
	}

	/** Adds timer label from GameModeTimer */
	private void createTimer()
	{
		frame2.add(((GameModeTimed) game).drawTimer());
	}
	
	/** Read in timed mode rules */
	private void createTRules()
	{
		try {
			Scanner scan = new Scanner(new File("TimerModeRules.txt"));
			JLabel timedRules = new JLabel(scan.nextLine());
			timedRules.setBounds(30, 90, 330, 300);
			rulesPanel.add(timedRules);
			rulesPanel.add(layeredPaneR);
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** Read in classic mode rules */
	private void createCRules()
	{
		try {
			Scanner scan = new Scanner(new File("ClassicModeRules.txt"));
			JLabel classicRules = new JLabel(scan.nextLine());
			classicRules.setBounds(30, 90, 330, 300);
			rulesPanel.add(classicRules);
			rulesPanel.add(layeredPaneR);
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
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
				createGame();
			}
			else if(e.getSource() == btnMenu){
				endOptPanel.setVisible(false);
				initialize();
			}
		}
    }
	
	public static void main(String[] args) 
	{
		GameViewer gameGame = new GameViewer();	
	}
	
}
