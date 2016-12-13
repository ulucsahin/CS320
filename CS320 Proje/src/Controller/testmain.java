package Controller;

import java.util.ArrayList;

import System.DBGetter;
import System.Room;
import System.XMLParser;

public class testmain {
	
	public static void main(String[] args){
		MainController mc = new MainController();	
		ArrayList<Room> rooms = mc.getRooms();		
		System.out.println(rooms.get(13));
	}
}
