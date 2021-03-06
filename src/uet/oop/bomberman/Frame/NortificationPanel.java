package uet.oop.bomberman.Frame;

import uet.oop.bomberman.GameLoop;

import javax.swing.*;
import java.awt.*;

/**
 * Thanh hiển thị thời gian và số điểm trong game.
 */
public class NortificationPanel extends JPanel {
	
	private JLabel timeLabel;
	private JLabel pointsLabel;

	public NortificationPanel(GameLoop gameLoop) {
		setLayout(new GridLayout());
		
		timeLabel = new JLabel("Time: " + gameLoop.getBoard().getTime());
		timeLabel.setForeground(Color.white);
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		pointsLabel = new JLabel("Points: " + gameLoop.getBoard().getPoints());
		pointsLabel.setForeground(Color.white);
		pointsLabel.setHorizontalAlignment(JLabel.CENTER);
		
		add(timeLabel);
		add(pointsLabel);
		
		setBackground(Color.black);
		setPreferredSize(new Dimension(0, 40));
	}
	
	public void setTime(int t) {
		timeLabel.setText("Time: " + t);
	}

	public void setPoints(int t) {
		pointsLabel.setText("Score: " + t);
	}
	
}
