package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView implements AbstractView{
	protected JTextField loginField;
	protected JPasswordField passwordField;
	
	public LoginView(){
		int xStartLocation = MainView.DEFAULT_X_SIZE/4;
		int xWidth = MainView.DEFAULT_X_SIZE/3;
		
		int yStartLocation = MainView.DEFAULT_Y_SIZE/6;
		int yWidth = MainView.DEFAULT_Y_SIZE/20;
		
		int yInterval = MainView.DEFAULT_Y_SIZE/10;
		
		loginField = new JTextField();
		loginField.setBackground(Color.CYAN);
		loginField.setLocation(xStartLocation, yStartLocation);
		loginField.setSize(xWidth,yWidth);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.CYAN);
		passwordField.setLocation(xStartLocation, yStartLocation+yInterval);
		passwordField.setSize(xWidth, yWidth);
	}

	public void paint(Graphics g) {
	}

	public void mouseMoved(int x, int y) {
	}

	public void mousePressed(int x, int y) {
	}

}
