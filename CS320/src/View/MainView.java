package View;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import System.Building;

public class MainView extends JPanel {
	protected static final int DEFAULT_X_SIZE = 700;
	protected static final int DEFAULT_Y_SIZE = 700;
	
	public static void main(String[] args){
		MainView view = new MainView();
	}
	
	JFrame frame;
	
	AbstractView currentView;
	static BuildingMapView buildingMapView;
	static LoginView loginView;
	static RoomReservationView roomReservationView;
	
	public MainView(){
		initializeFrame();
		initializeViews();
		connectMouseListener();
		switchToLoginScreen();
	}
	
	public void paintComponent(Graphics g){
		currentView.paint(g);
	}
	
	public void switchToLoginScreen(){
		currentView = loginView;
		this.add(loginView.loginField);
		this.add(loginView.passwordField);
		repaint();
		
	}
	
	private void detachTextFields(){
		this.remove(loginView.loginField);
		this.remove(loginView.passwordField);
	}
	
	public void switchToReservationScreen(Building b){
		
	}
	
	public void switchToMapScreen(){
		
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
		buildingMapView = new BuildingMapView();
		loginView = new LoginView();
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
	}
	
	public void mouseMoved(MouseEvent e){
		int x_cor = e.getX();
		int y_cor = e.getY();
		view.currentView.mouseMoved(x_cor, y_cor);
	}
	
}
