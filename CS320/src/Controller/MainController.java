package Controller;

import java.util.ArrayList;

import System.Room;

public class MainController {
	private LoginController loginController;
	private ReservationController reservationController;
	private RoomController roomController;
	
	public MainController(){
		loginController = new LoginController();
		reservationController = new ReservationController();
		roomController = new RoomController();
	}
	
	public boolean login(String username, String password){
		return loginController.login(username, password);
	}
	
	public ArrayList<Room> getRooms(){
		return roomController.getRooms();
	}
	
}
