import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JInternalFrame;

import com.jgoodies.forms.factories.FormFactory;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;


public class GameViewerTest {

	private JFrame frame;
	private JPanel welcomePanel;
	private JPanel classicPanel;
	private JPanel endPanel;
	private String rules;
	private JPanel gamePanel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameViewerTest window = new GameViewerTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameViewerTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 390, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		welcomePanel = new JPanel();
		frame.getContentPane().add(welcomePanel, "Welcome");
		welcomePanel.setLayout(null);
		welcomePanel.setVisible(true);
		
		JLabel lblWelcome = new JLabel("WELCOME TO ICECREAM MANIA");
		lblWelcome.setBounds(49, 55, 313, 31);
		welcomePanel.add(lblWelcome);
		lblWelcome.setFont(new Font("Desdemona", Font.BOLD, 26));
		
		JLabel lblChooseAGame = new JLabel("Choose a Game Mode");
		lblChooseAGame.setBounds(139, 123, 135, 16);
		welcomePanel.add(lblChooseAGame);
		
		rules = "";
		JButton btnClassic = new JButton("Classic");
		btnClassic.setBounds(105, 410, 170, 85);
		welcomePanel.add(btnClassic);
		
		ImageIcon img = new ImageIcon("/Users/carolzeng/workspace/COMSPCI/tumblr_p7ultlfFGw1x9wpiro1_500.jpg");
		
		JButton btnTimer = new JButton("Timer");
		btnTimer.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		btnTimer.setBounds(105, 300, 170, 85);
		welcomePanel.add(btnTimer);
		btnTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endPanel.setVisible(true);
				welcomePanel.setVisible(false);
				createTRules();
				JLabel timerRules = new JLabel(rules);
				timerRules.setBounds(35, 40, 360, 260);
				endPanel.add(timerRules);
			}
		});
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 420, 368);
		welcomePanel.add(layeredPane);
		JLabel lblNewLabel = new JLabel(img);
		lblNewLabel.setBounds(0, 0, 420, 368);
		layeredPane.add(lblNewLabel);
		
		
		
		btnClassic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classicPanel.setVisible(true);
				welcomePanel.setVisible(false);
				createCRules();
				JLabel classicRules = new JLabel(rules);
				classicRules.setBounds(35, 40, 360, 260);
				classicPanel.add(classicRules);
			}
		});
		
		//CLASSIC PANEL
		classicPanel = new JPanel();
		frame.getContentPane().add(classicPanel, "Rules");
		classicPanel.setLayout(null);
		classicPanel.setVisible(false);
		
		JButton buttonC = new JButton("Begin Game!");
		buttonC.setBounds(112, 427, 120, 30);
		classicPanel.add(buttonC);
		
		
		//TIMER PANEL
		endPanel = new JPanel();
		frame.getContentPane().add(endPanel, "Rules");
		endPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endPanel.setVisible(false);
				gamePanel.setVisible(true);
			}
		});
		btnNewButton.setBounds(121, 277, 140, 52);
		endPanel.add(btnNewButton);
		
		JButton button = new JButton("New button");
		button.setBounds(121, 356, 140, 52);
		endPanel.add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(121, 436, 140, 52);
		endPanel.add(button_1);
		
		gamePanel = new JPanel();
		frame.getContentPane().add(gamePanel, "name_520658050597437");
		gamePanel.setLayout(null);
		GameMode gameMode = new GameMode();
		gameMode.setBounds(0, 0, 390, 548);
		gamePanel.add(gameMode);
		
		JLabel score = new JLabel("New label");
		score.setBounds(298, 407, 61, 16);
		gamePanel.add(score);
		
		JLabel highschore = new JLabel("New label");
		highschore.setBounds(298, 466, 61, 16);
		gamePanel.add(highschore);
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
}
