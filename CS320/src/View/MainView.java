package View;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RepaintManager;

import Controller.MainController;
import System.Building;
import System.Room;

public class MainView extends JPanel {
	protected static final int DEFAULT_X_SIZE = 700;
	protected static final int DEFAULT_Y_SIZE = 700;
	
	MainController controller;
	
	public static void main(String[] args){
		MainView view = new MainView();
	}
	
	JFrame frame;
	
	AbstractView currentView;
	static LoginView loginView;
	static RoomReservationView roomReservationView;
	
	public MainView(){
		initializeFrame();
		initializeViews();
		connectMouseListener();
		switchToLoginScreen();
	}
	
	public void attachController(MainController controller){
		this.controller = controller;
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, 1000, 1000);
		if(currentView != null){
			currentView.paint(g);
		}
		if(currentView != null && currentView.equals(loginView)){
			validateTextFields();
		}
	}
	
	private void validateTextFields(){
		if(loginView.loginField != null){
			loginView.loginField.revalidate();
		}
		if(loginView.passwordField != null){
			loginView.passwordField.revalidate();
		}
	}
	
	public void switchToLoginScreen(){
		currentView = loginView;
		this.add(loginView.loginField);
		this.add(loginView.passwordField);
		repaint();
		
	}
	
	protected void detachTextFields(){
		this.remove(loginView.loginField);
		this.remove(loginView.passwordField);
	}
	
	public void switchToReservationScreen(ArrayList<Room> rooms){
		this.roomReservationView.buildRooms(rooms);
		this.currentView = roomReservationView;
	}
	
	private void initializeFrame(){
		frame = new JFrame("RMS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
		this.setSize(DEFAULT_X_SIZE, DEFAULT_Y_SIZE);
		this.setLayout(null);
		frame.add(this);
	}
	
	private void initializeViews(){
		loginView = new LoginView(this);
		roomReservationView = new RoomReservationView();
	}
	
	private void connectMouseListener(){
		MainViewMouseListener mouseListener=
				new MainViewMouseListener(this);
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
	}

}

class MainViewMouseListener extends MouseAdapter{
	MainView view;
	
	public MainViewMouseListener(MainView view){
		this.view = view;
	}
	
	public void mousePressed(MouseEvent e){
		int x_cor = e.getX();
		int y_cor = e.getY();
		view.currentView.mousePressed(x_cor, y_cor);
		view.repaint();
	}
	
	public void mouseMoved(MouseEvent e){
		int x_cor = e.getX();
		int y_cor = e.getY();
		view.currentView.mouseMoved(x_cor, y_cor);
		view.repaint();
	}
	
}
