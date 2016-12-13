package System;

import java.util.ArrayList;

public class Building {
	private String buildingID;
	private String name;
	private ArrayList<Room> rooms;
	
	public Building(String buildingID, String name){
		this.buildingID = buildingID;
		this.name = name;
		rooms = new ArrayList<Room>();
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void addRoom(Room room) {
		rooms.add(room);
	}

	public String getBuildingID() {
		return buildingID;
	}
	
	public String getBuildingName(){
		return name;
	}
	
}
