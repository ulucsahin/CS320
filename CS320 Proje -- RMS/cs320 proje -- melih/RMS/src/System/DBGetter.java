package System;

import java.util.ArrayList;

public class DBGetter {

	ArrayList<Building> buildings;
	
	public DBGetter(){
		buildings = new ArrayList<Building>();
	}
	
	public void addBuilding(Building building){
		buildings.add(building);
	}
	
	public Building newBuilding(String buildingID, String name, Room[] rooms){
		Building building = new Building(buildingID, name);
		for(int i=0; i < rooms.length; i++)
		{
			building.addRoom(rooms[i]);
		}
		return building;
	}
	
	public Room newRoom(String roomID, int capacity,  Reservation[] reservations){
		Room room = new Room(roomID, capacity); 
		for(int i=0; i < reservations.length; i++)
		{
			room.addReservation(reservations[i]);
		}
		return room;
	}
	
	public Reservation newReservation(int timeInterval, Student student, int reservationID){
		return new Reservation(timeInterval,student, reservationID);
	}
	
	public Student newStudent(String name, String surname, String username, String password){
		return new Student(name,surname,username,password);
	}
	
	public Building getBuilding(String buildingID){
		Building requested = null;
		for(int i=0; i < buildings.size(); i++){
			if(buildings.get(i).getBuildingID().equals(buildingID))
				requested = buildings.get(i);
		}
		return requested;
	}
	
	public Room getRoom(String roomID)
	{
		Room requested = null;
		for(int i=0; i < buildings.size(); i++){
			ArrayList<Room> rooms = buildings.get(i).getRooms();
			for(int j=0; j < rooms.size(); j++){
				Room r = rooms.get(j);
				if(r.getRoomID().equals(roomID))
					requested = r;
			}
		}
		return requested;
	}
	
	public Reservation getReservation(int reservationID)
	{
		Reservation requested = null;
		for(int i=0; i < buildings.size(); i++){
			ArrayList<Room> rooms = buildings.get(i).getRooms();
			for(int j=0; j < rooms.size(); j++){
				ArrayList<Reservation> reservations = rooms.get(j).getReservations();
				for(int k=0; k < reservations.size(); k++){
					if(reservations.get(k).getReservationID() == reservationID)
						requested = reservations.get(k);
				}
			}
		}
		return requested;
	}
	
	public Student getStudent(String username)
	{
		Student requested = null;
		for(int i=0; i < buildings.size(); i++){
			ArrayList<Room> rooms = buildings.get(i).getRooms();
			for(int j=0; j < rooms.size(); j++){
				ArrayList<Reservation> reservations = rooms.get(j).getReservations();
				for(int k=0; k < reservations.size(); k++){
					if(reservations.get(k).getStudent().getUsername().equals(username))
						requested = reservations.get(k).getStudent();
				}
			}
		}
		return requested;
	}
}
