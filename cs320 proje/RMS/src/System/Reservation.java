package System;

public class Reservation {
	private int timeInterval;
	private Student student;
	
	public Reservation(int timeInterval,Student student) {
		this.timeInterval=timeInterval;
		this.student=student;
	}

	public int getTimeInterval() {
		return timeInterval;
	}

	public Student getStudent() {
		return student;
	}
	
	
}
