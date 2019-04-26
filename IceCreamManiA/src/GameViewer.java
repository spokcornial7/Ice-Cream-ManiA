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
	private JPanel classicPanel;
	private JPanel timerPanel;
	private String rules;
	private JButton btnTimer;
	private JButton btnClassic;
	private JButton btnTBegin;
	private JButton btnCBegin;
	
	private JFrame frame2;
	
	
	public GameViewer() 
	{
		askGameMode();
		frame1.setVisible(true);
		
		JButton restart = new JButton("Restart");
		JButton diff = new JButton("Different Game Mode");
		JButton exitB = new JButton("Exit Game");
	}
	

	private void askGameMode() {
		ButtonListener listener = new ButtonListener();
		
		frame1 = new JFrame();
		frame1.setBounds(100, 100, 420, 390);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(new CardLayout(0, 0));
		
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
		
		btnTimer = new JButton("Timer");
		btnTimer.setBounds(160, 180, 90, 30);
		welcomePanel.add(btnTimer);
		btnTimer.addActionListener(listener);
		
		btnClassic = new JButton("Classic");
		btnClassic.setBounds(157, 250, 90, 30);
		welcomePanel.add(btnClassic);
		btnClassic.addActionListener(listener);
		
		//CLASSIC PANEL
		classicPanel = new JPanel();
		frame1.getContentPane().add(classicPanel, "Rules");
		classicPanel.setLayout(null);
		classicPanel.setVisible(false);
		
		btnCBegin = new JButton("Begin Game!");
		btnCBegin.setBounds(150, 310, 120, 30);
		classicPanel.add(btnCBegin);
		btnCBegin.addActionListener(listener);
		
		
		//TIMER PANEL
		timerPanel = new JPanel();
		frame1.getContentPane().add(timerPanel, "Rules");
		timerPanel.setLayout(null);
		timerPanel.setVisible(false);
		
		btnTBegin = new JButton("Begin Game!");
		btnTBegin.setBounds(150, 310, 120, 30);
		timerPanel.add(btnTBegin);
		btnTBegin.addActionListener(listener);
	}
	
	private void createCRules()
	{
		try {
			rules = new Scanner(new File("ClassicModeRules.txt")).nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void createTRules()
	{
		try {
			rules = new Scanner(new File("TimerModeRules.txt")).nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

    public String getGameMode()
    {
    	return gameMode;
    }
    
    private void createGame()
    {
    	frame2 = new JFrame("Ice Cream Mania");
    	frame2.setSize(500, 500);
    	frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private class ButtonListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == btnTimer){
				timerPanel.setVisible(true);
				welcomePanel.setVisible(false);
				createTRules();
				JLabel timerRules = new JLabel(rules);
				timerRules.setBounds(35, 40, 360, 260);
				timerPanel.add(timerRules);
			}
			else if(e.getSource() == btnClassic){
				classicPanel.setVisible(true);
				welcomePanel.setVisible(false);
				createCRules();
				JLabel classicRules = new JLabel(rules);
				classicRules.setBounds(35, 40, 360, 260);
				classicPanel.add(classicRules);
			}
			else if(e.getSource() == btnTBegin){
				game = new GameModeTimer();
				frame1.setVisible(false);
				createGame();
			}
			else if(e.getSource() == btnCBegin){
				game = new GameModeClassic();
				frame1.setVisible(false);
				createGame();
			}
		}
    	
    }
    
	public static void main(String[] args) 
	{
		GameViewer gameGame = new GameViewer();	
	}

}
