
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class GameModeClassic extends GameMode
{
	
	private int score;
	private int highscore;
	private boolean done;
	private Queue<Scoop> scoopQueue;

	public GameModeClassic() 
	{
		super();
		score = 0;
		highscore = 0;
		done = false;
		scoopQueue = new LinkedList<>();
	}

	@Override
	public int getHighScore()
	{
		return highscore;
	}
	
	@Override
	public int getPoints()
	{
		return score;
	}
	
	private void setHighScore()
	{
		if(score > highscore)
			highscore = score;
	}
	
	@Override
	public boolean isGameOver()
	{
		super.isGameOver();
		return done;
	}
	
	@Override
	public void createDiagram(Graphics g)
	{
		
	}
	
	public static void main(String[] args) {
		ImageIcon check = new ImageIcon("checkmark.png");
		Image image = check.getImage();
		Image newimg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		check = new ImageIcon(newimg); 
		JLabel pane = new JLabel(check);
		JFrame f = new JFrame();
		f.add(pane);
		f.setSize(300, 300);
		f.setVisible(true);
		
    }
}
