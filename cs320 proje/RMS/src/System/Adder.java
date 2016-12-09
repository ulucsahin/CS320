package System;

import java.util.ArrayList;

public class Adder {

	ArrayList<Building> buildings;
	
	public Adder(){
		buildings = new ArrayList<Building>();
	}
	
	public void printInfo(){
		for (int i = 0; i < buildings.size(); i++) {
			System.out.println("--------------------------------");
			Building b = buildings.get(i);
			String buildingID = b.getBuildingID();
			String buildingName = b.getBuildingName();
			System.out.println("BuildingID: " + buildingID + "  BuildingName: " + buildingName);
			for(int j = 0; j < b.getRooms().size(); j++){
				Room r = b.getRooms().get(j);
				System.out.println("RoomID: " + r.getRoomID() + " capacity: " + r.getCapacity());
				for(int k = 0; k < r.getReservations().size(); k++){
					Reservation res = r.getReservations().get(k);
					System.out.println("Reservation time: " + res.getTimeInterval());
					System.out.println("student hired: " + res.getStudent().getStudentInfo());
				}
			}
			System.out.println("---------------------------------");
		}
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
	
	public Reservation newReservation(int timeInterval, Student student){
		return new Reservation(timeInterval,student);
	}
	
	public Student newStudent(String name, String surname, String username, String password){
		return new Student(name,surname,username,password);
	}
}
