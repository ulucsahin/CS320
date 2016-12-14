package System;

public class Reservation {
	private int reservationID;
	private int timeInterval;
	private String username;
	
	public Reservation(int timeInterval,String username, int reservationID) {
		this.timeInterval=timeInterval;
		this.username = username;
		this.reservationID = reservationID;
	}

	public int getReservationID()
	{
		return reservationID;
	}
	
	public int getTimeInterval() {
		return timeInterval;
	}

	public String  getUsername() {
		return username;
	}
	
	
}
