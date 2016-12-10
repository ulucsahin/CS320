package System;

public class RoomManagementModel {

	
	public static void main(String[] args)
	{
		DBGetter getter = new DBGetter();
		XMLParser parser = new XMLParser(getter);
		parser.run();
		
		Student melih = getter.getStudent("ma4031");
		System.out.println("melih info = " + melih.getStudentInfo());
		
		Building EF = getter.getBuilding("01");
		System.out.println("building name : " + EF.getBuildingName());
		
		Room r = getter.getRoom("FEAS.114.B");
		System.out.println("room capacity: " + r.getCapacity());
		
		Reservation res = getter.getReservation(3);
		System.out.println("res time interval: " + res.getTimeInterval());
		// NOTE: time interval 0 çýkýyor çünkü o kýsmý henüz implement etmedim.
		// DB de ne tutacaz ve bunu nasýl döndürcem net deðil bende.
	} 
}
