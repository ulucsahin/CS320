package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends AbstractView{
	protected JTextField loginField;
	protected JPasswordField passwordField;
	
	private static int X_START_LOCATION = (int) (MainView.DEFAULT_X_SIZE/3.5);
	private static int X_WIDTH = (int) (MainView.DEFAULT_X_SIZE/2.5);
	
	private static int Y_START_LOCATION = MainView.DEFAULT_Y_SIZE/6;
	private static int Y_WIDTH = MainView.DEFAULT_Y_SIZE/20;
	
	private static int Y_INTERVAL = MainView.DEFAULT_Y_SIZE/9;
	private static int TEXT_DISTANCE = 10;
	
	public LoginView(){		
		loginField = new JTextField();
		loginField.setBackground(Color.CYAN);
		loginField.setLocation(X_START_LOCATION, Y_START_LOCATION);
		loginField.setSize(X_WIDTH,Y_WIDTH);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.CYAN);
		passwordField.setLocation(X_START_LOCATION, Y_START_LOCATION+Y_INTERVAL);
		passwordField.setSize(X_WIDTH, Y_WIDTH);
	}

	public void paint(Graphics g) {
		g.setFont(new Font("Courier",Font.BOLD,20));
		g.setColor(Color.DARK_GRAY);
		
		g.drawString("Username", X_START_LOCATION, Y_START_LOCATION-TEXT_DISTANCE);
		g.drawString("Password", X_START_LOCATION, Y_START_LOCATION+Y_INTERVAL-TEXT_DISTANCE);
	}

	public void mouseMoved(int x, int y) {
		super.mouseMoved(x, y);
	}

	public void mousePressed(int x, int y) {
		super.mousePressed(x, y);
	}

}
