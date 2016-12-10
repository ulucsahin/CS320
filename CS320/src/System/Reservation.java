package System;

public class Reservation {
	private int reservationID;
	private int timeInterval;
	private Student student;
	
	public Reservation(int timeInterval,Student student, int reservationID) {
		this.timeInterval=timeInterval;
		this.student=student;
		this.reservationID = reservationID;
	}

	public int getReservationID()
	{
		return reservationID;
	}
	
	public int getTimeInterval() {
		return timeInterval;
	}

	public Student getStudent() {
		return student;
	}
	
	
}
