package Controller;

import System.DBUpdate;
import System.Reservation;

public class ReservationController {

	final private DBUpdate dbupdate = new DBUpdate();
	private int reservationID = 10;

	public void reserveRoom(String userID, int buildingID, String RoomID, int timeInterval) {
		Reservation reservation = new Reservation(timeInterval, userID, reservationID);
		reservationID++;
		dbupdate.addReservation(buildingID, RoomID, reservation);
	}

	public void removeReservation(int buildingID, String RoomID, Reservation reservation) {
		dbupdate.removeReservation(buildingID, RoomID, reservation);
	}
}
