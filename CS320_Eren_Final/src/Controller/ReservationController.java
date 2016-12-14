package Controller;

import java.util.ArrayList;

import System.Building;
import System.DBGetter;
import System.DBUpdate;
import System.Reservation;
import System.Room;

public class ReservationController {

	final private DBUpdate dbupdate = new DBUpdate();
	DBGetter dbGetter = new DBGetter();
	private int reservationID = 10;

	public void reserveRoom(String userID, int buildingID, String RoomID, int timeInterval) {
		Reservation reservation = new Reservation(timeInterval, userID, reservationID);
		reservationID++;
		dbupdate.addReservation(buildingID, RoomID, reservation);
	}
	
	public void reserveRoom(String userID, String RoomID, int timeInterval) {
		Reservation reservation = new Reservation(timeInterval, userID, reservationID);
		reservationID++;
		
		ArrayList<Building> buildings = dbGetter.getBuildings();
		
		for(int buildingNo = 0; buildingNo < buildings.size();buildingNo++){
			ArrayList<Room> rooms = buildings.get(buildingNo).getRooms();
			for(int i = 0; i<rooms.size();i++){
				if(rooms.get(i).getRoomID().equals(RoomID)){
					dbupdate.addReservation(Integer.parseInt(buildings.get(buildingNo).getBuildingID()), RoomID, reservation);
				}
			}
		}
	}

	public void removeReservation(int buildingID, String RoomID, Reservation reservation) {
		dbupdate.removeReservation(buildingID, RoomID, reservation);
	}
	
	public void removeReservation(Reservation reservation) {
		ArrayList<Building> buildings = dbGetter.getBuildings();
		
		for(int buildingNo = 0; buildingNo < buildings.size();buildingNo++){
			ArrayList<Room> rooms = buildings.get(buildingNo).getRooms();
			for(int i = 0; i<rooms.size();i++){
				ArrayList<Reservation> reservations = rooms.get(i).getReservations();
				for(int j = 0; j<reservations.size(); j++){
					if(reservations.get(j).getReservationID() == reservation.getReservationID()){
						removeReservation(Integer.parseInt(buildings.get(buildingNo).getBuildingID()),rooms.get(i).getRoomID(),reservation);
					}
				}
			}
		}
	}
}
