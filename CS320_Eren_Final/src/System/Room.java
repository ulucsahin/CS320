package System;

import java.util.ArrayList;

public class Room {

	private String roomID;
	private int capacity;
	private boolean isEmpty;
	private ArrayList<Reservation> reservations;

	public Room(String roomID, int capacity) {
		this.reservations = new ArrayList<Reservation>();
		this.isEmpty = true;
		this.roomID = roomID;
		this.capacity = capacity;
	}

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}

	public String getRoomID() {
		return roomID;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

}
