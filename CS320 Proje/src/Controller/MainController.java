package Controller;

import java.util.ArrayList;

import System.Reservation;
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
	
	public void reserveRoom(String userID, int buildingID, String RoomID, int timeInterval) {
		reservationController.reserveRoom(userID, buildingID, RoomID, timeInterval);
	}
	
	public Reservation reserveRoom(String userID, String RoomID, int timeInterval) {
		return reservationController.reserveRoom(userID, RoomID, timeInterval);
	}

	public void removeReservation(int buildingID, String RoomID, Reservation reservation) {
		reservationController.removeReservation(buildingID, RoomID, reservation);
	}
	public void removeReservation(Reservation reservation){
		reservationController.removeReservation(reservation);
	}
	
}
