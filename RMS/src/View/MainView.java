package View;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JPanel {
	
	JFrame frame;
	
	AbstractView currentView;
	static BuildingMapView buildingMapView;
	static LoginView loginView;
	static RoomReservationView roomReservationView;
	
	public MainView(){
		currentView = loginView;
		initializeFrame();
		initializeViews();
	}
	
	private void initializeFrame(){
		frame = new JFrame("RMS");
		this.setSize(800, 800);
		frame.add(this);
	}
	
	private void initializeViews(){
		buildingMapView = new BuildingMapView();
		loginView = new LoginView();
		roomReservationView = new RoomReservationView();
	}

}
