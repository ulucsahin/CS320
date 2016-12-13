package Controller;

import java.util.ArrayList;

import System.Building;
import System.DBGetter;
import System.Room;
import System.XMLParser;

public class RoomController {
	DBGetter dbGetter = new DBGetter();
	
	public ArrayList<Room> getRooms(){	
		ArrayList<Building> buildings = dbGetter.getBuildings();
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		for(int i = 0; i < buildings.size(); i++){
			Building building = buildings.get(i);
			ArrayList<Room> tempRoomList = building.getRooms();
			for(int j = 0; j < tempRoomList.size(); j++){
				rooms.add(tempRoomList.get(j));
			}
		}
		
		return rooms;
	}
	
}
