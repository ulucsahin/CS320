package System;

public class RoomManagementModel {

	
	public static void main(String[] args)
	{
		Adder adder = new Adder();
		XMLParser parser = new XMLParser(adder);
		parser.run();
		adder.printInfo();
	} 
}
