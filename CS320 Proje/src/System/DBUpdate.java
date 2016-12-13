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
	private File xmlFile;
	private SAXBuilder builder;

	public DBUpdate() {
		xmlFile = null;
		builder = null;
		try {
			builder = new SAXBuilder();
			xmlFile = new File("roomDBxml.xml");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to find the xml file.");
		}
	}

	public boolean update(int buildingID, String roomID, Reservation reservation) {
		boolean success = false;
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			System.out.println(rootNode);

			Element buildings = rootNode.getChild("Buildings");
			System.out.println(buildings);

			List building = buildings.getChildren();
			System.out.println(building);

			List rooms = null;
			for (int i = 0; i < building.size(); i++) {
				if (buildingID == Integer.parseInt(((Element) building.get(i)).getChild("ID").getText())) {
					rooms = (List) ((Element) building.get(i)).getChild("Rooms").getChildren();
				}
			}
			if (rooms == null) {
				System.out.println("could not find the rooms");
			}
			System.out.println(rooms);

			Element room = null;
			for (int i = 0; i < rooms.size(); i++) {
				if (((Element) rooms.get(i)).getChild("ID").getText().equals(roomID)) {
					room = ((Element) rooms.get(i));
				}
			}
			if (room == null) {
				System.out.println("room does not exist!");
			}
			System.out.println(room);

			List reservations = room.getChild("Reservations").getChildren();
			System.out.println(reservations);

			Element reservationXML = null;
			for (int i = 0; i < reservations.size(); i++) {
				if (reservation.getReservationID() == Integer
						.parseInt(((Element) reservations.get(i)).getChild("reservationID").getText())) {
					reservationXML = (Element) reservations.get(i);
				}
			}
			if (reservationXML == null) {
				Element newReservationXML = new Element("Reservation");
				newReservationXML.addContent(new Element("reservationID").setText(reservation.getReservationID() + ""));
				newReservationXML.addContent(new Element("timeInterval").setText(reservation.getTimeInterval() + ""));
				newReservationXML
						.addContent(new Element("username").setText(reservation.getStudent().getUsername() + ""));
				room.getChild("Reservations").addContent(newReservationXML);
				success = true;
			}

			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("roomDBxml.xml"));

		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		return success;
	}
}