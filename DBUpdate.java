package System;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class DBUpdate {
	private final String FILE = "c:\\roomDBxml.xml";
	private final String RESERVATION_ID = "reservationID";
	private final String RESERVATIONS = "Reservations";
	private final String ID = "ID";
	private File xmlFile;
	private SAXBuilder builder;
	private Document doc;

	public DBUpdate() {
		xmlFile = null;
		builder = null;
		doc = null;
		try {
			builder = new SAXBuilder();
			xmlFile = new File(FILE);
			doc = (Document) builder.build(xmlFile);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to find the xml file.");
		}
	}

	public boolean addReservation(int buildingID, String roomID, Reservation reservation) {
		boolean success = false;

		Element reservations = getReservations(buildingID, roomID);
		List reservationsToList = reservations.getChildren();
		Element reservationXML = null;
		for (int i = 0; i < reservationsToList.size(); i++) {
			if (reservation.getReservationID() == Integer
					.parseInt(((Element) reservationsToList.get(i)).getChild(RESERVATION_ID).getText())) {
				reservationXML = (Element) reservationsToList.get(i);
			}
		}
		if (reservationXML == null) {
			Element newReservationXML = new Element("Reservation");
			newReservationXML.addContent(new Element(RESERVATION_ID).setText(reservation.getReservationID() + ""));
			newReservationXML.addContent(new Element("timeInterval").setText(reservation.getTimeInterval() + ""));
			newReservationXML.addContent(new Element("username").setText(reservation.getStudent().getUsername() + ""));
			reservations.addContent(newReservationXML);
			success = true;
		}

		writeToXML();
		return success;
	}

	private Element getReservations(int buildingID, String roomID) {

		Element rootNode = doc.getRootElement();

		Element buildings = rootNode.getChild("Buildings");

		List building = buildings.getChildren();

		List rooms = null;
		for (int i = 0; i < building.size(); i++) {
			if (buildingID == Integer.parseInt(((Element) building.get(i)).getChild(ID).getText())) {
				rooms = (List) ((Element) building.get(i)).getChild("Rooms").getChildren();
			}
		}
		if (rooms == null) {
			System.out.println("could not find the rooms");
		}

		Element room = null;
		for (int i = 0; i < rooms.size(); i++) {
			if (((Element) rooms.get(i)).getChild(ID).getText().equals(roomID)) {
				room = ((Element) rooms.get(i));
			}
		}
		if (room == null) {
			System.out.println("room does not exist!");
		}

		return room.getChild(RESERVATIONS);
	}

	public boolean removeReservation(int buildingID, String roomID, Reservation reservation) {
		boolean success = false;
		Element reservations = getReservations(buildingID, roomID);
		List reservationsToList = reservations.getChildren();

		for (int i = 0; i < reservationsToList.size(); i++) {
			if (reservation.getReservationID() == Integer
					.parseInt(((Element) reservationsToList.get(i)).getChild(RESERVATION_ID).getText())) {
				reservations.removeContent((Element) reservationsToList.get(i));
				success = true;
				break;
			}
		}

		writeToXML();
		return success;
	}

	private void writeToXML() {
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		try {
			xmlOutput.output(doc, new FileWriter(FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
