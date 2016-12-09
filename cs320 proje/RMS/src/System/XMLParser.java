package System;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jdom.*;
import org.jdom.input.SAXBuilder;

public class XMLParser {

	Adder adder;
	
	public XMLParser(Adder adder)
	{
		this.adder = adder;
	}
	
	public  void run ()
	{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = null;
		try 
		{
			xmlFile = new File("c:\\roomDBxml.xml");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to read the xml file.");
		}
		
		try
		{
			Document document = (Document) builder.build(xmlFile);
			Element root = document.getRootElement();
			Element buildingData = root.getChild("Buildings");
			Element studentData = root.getChild("Students");
			
			List buildingList = buildingData.getChildren("Building");
			List studentList = studentData.getChildren("Student");
			
			
			for (int i = 0; i < buildingList.size(); i++) {
				Element building = (Element) buildingList.get(i);
				Element roomData = building.getChild("Rooms");
				
				String buildingID = building.getChildText("ID");
				String buildingName = building.getChildText("name");
				  
				Building b = new Building(buildingID, buildingName);
				   
				List roomList = roomData.getChildren("Room");
				   
				for(int j=0; j < roomList.size(); j++){
					 Element roomNode = (Element) roomList.get(j);
					 String roomId = roomNode.getChildText("ID");
					 String capacity = roomNode.getChildText("capacity");
					 String isEmpty = roomNode.getChildText("isEmpty");
					 int cap = Integer.valueOf(capacity);
					   	
					 Element reservationData = roomNode.getChild("Reservations");
					 List reservationList = reservationData.getChildren("Reservation");
					   
					 Room r = new Room(roomId, cap);
					   
					 System.out.println("RoomID: " + roomId + " capacity: " + cap);
					   
					 for(int l=0; l < reservationList.size(); l++){
						Element reservation = (Element) reservationList.get(l);
						String timeInterval = reservation.getChildText("timeInterval");
							
							
						Element student = reservation.getChild("Student");
						String name = student.getChildText("name");
						String surname = student.getChildText("surname");
						String username = student.getChildText("username");
						String password = student.getChildText("password");
							
						Student stud = new Student(name,surname,username,password);
											
						int timeInv = 0; // şimdilik böyle sonra düzeltilecek
							
						Reservation res = new Reservation(timeInv,stud);
							
						r.addReservation(res);
					}
					   b.addRoom(r);
				 }
				  
				   
				
				   
				 adder.addBuilding(b);
			}
	
		}
		catch(Exception e){
			System.out.println("Unable to parse data.");
			
		}
	
	}
	
}
