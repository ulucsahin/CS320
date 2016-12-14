package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import System.Reservation;
import System.Room;

public class RoomReservationView extends AbstractView {
	private final static int X_START = 80,Y_START = 30;
	private static int PAGE_ROOM_COUNT = 15;
	private final static int COLUMN_INTERVAL = 45 , ROW_INTERVAL = 30;
	private ArrayList<Room> rooms;
	
	private static int resId= 100;
	
	private boolean[][] mouseLocation = new boolean[13][PAGE_ROOM_COUNT];
	private Reservation[][] reservations = new Reservation[13][PAGE_ROOM_COUNT];
	
	private int currentPageNo = 0;
	private int pageCount;
	
	
	MainView view;
	
	public RoomReservationView(MainView view){
		this.view = view;
	}
	
	private void updateRooms(){
		buildRooms(this.view.controller.getRooms());
	}

	public void buildRooms(ArrayList<Room> rooms){
		this.rooms = rooms;
		PAGE_ROOM_COUNT = rooms.size();
		reservations = new Reservation[13][PAGE_ROOM_COUNT];
		mouseLocation = new boolean[13][PAGE_ROOM_COUNT];
		cleanReservationsArray();
		
		for(int i = 0;i<rooms.size();i++){
			ArrayList<Reservation> reservations = rooms.get(i).getReservations();
			for(int j = 0; j<reservations.size();j++){
				Reservation rez = reservations.get(j);
				this.reservations[rez.getTimeInterval()][i] = rez;
			}
		}
		
	}
	
	private void cleanReservationsArray(){
		for(int i=0; i<reservations.length;i++){
			for(int j =0; j<reservations[0].length;j++){
				reservations[i][j] = null;
			}
		}
	}
	

	public void paint(Graphics g) {
		drawReservations(g);
		drawRows(g);
		drawColumns(g);
	}
	
	private void drawReservations(Graphics g){
		for(int x = 0;x<13;x++){
			for(int y = 0;y<PAGE_ROOM_COUNT;y++){
				if(mouseLocation[x][y]){
					if(reservations[x][y] == null){
						g.setColor(new Color(155,244,154));
					}
					else{
						g.setColor(Color.red);
					}
					g.fillRect(X_START+x*COLUMN_INTERVAL, Y_START+y*ROW_INTERVAL, COLUMN_INTERVAL, ROW_INTERVAL);
				}
				else{
					if(reservations[x][y] == null){
						g.setColor(Color.cyan);
					}
					else{
						Reservation rev = reservations[x][y];
						if(rev.getUsername().equals(this.view.loginView.loggedInUsername)){
							g.setColor(Color.blue);
						}
						else{
							g.setColor(Color.yellow);
						}
					}
					g.fillRect(X_START+x*COLUMN_INTERVAL, Y_START+y*ROW_INTERVAL, COLUMN_INTERVAL, ROW_INTERVAL);
				}
			}
		}
	}
	
	private int countReservations(){
		int count = 0;
		
		for(int i = 0;i<this.reservations.length;i++){
			for(int j=0;j<this.reservations[0].length;j++){
				if(reservations[i][j] != null &&
						reservations[i][j].getUsername().equals(this.view.loginView.loggedInUsername)){
					count++;
				}
			}
		}
		System.out.println(count);
		return count;
	}
	
	private void drawRows(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri",Font.BOLD,16));
		
		for(int i = 0;i<PAGE_ROOM_COUNT+1;i++){
			g.setColor(Color.BLACK);
			g.drawLine(X_START, Y_START+i*ROW_INTERVAL, X_START+COLUMN_INTERVAL*13, Y_START+i*ROW_INTERVAL);
			if(i != 0){
				g.setColor(new Color(102,0,102));
				g.drawString(""+this.rooms.get(i-1).getRoomID(), X_START-75, Y_START+i*ROW_INTERVAL-7);
			}
		}
		
	}
	
	private void drawColumns(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri",5,16));
		
		for(int i = 0;i<14;i++){
			if(i!=0){
				g.drawString(""+(i+8)+"-"+(i+9), X_START+(i-1)*COLUMN_INTERVAL+3, Y_START-3);
			}
			g.drawLine(X_START+i*COLUMN_INTERVAL, Y_START, X_START+i*COLUMN_INTERVAL, Y_START+ROW_INTERVAL*(PAGE_ROOM_COUNT));
			
		}
	}

	public void mouseMoved(int x, int y) {
		super.mouseMoved(x, y);
		
		int[] coordinate = calculateMouseLocation(x, y);
		
		if(isMouseOnReservationBox(x, y)){
			resetLocationArray();
			mouseLocation[coordinate[0]][coordinate[1]]=true;
		}
	}
	
	private int[] calculateMouseLocation(int x,int y){
		int boxXCor = (x-X_START)/COLUMN_INTERVAL;
		int boxYCor = (y-Y_START)/ROW_INTERVAL;
		
		int[] coordinate = new int[2];
		coordinate[0] = boxXCor;
		coordinate[1] = boxYCor;
		return coordinate;
	}
	
	private boolean isMouseOnReservationBox(int x,int y){
		int[] coordinate = calculateMouseLocation(x, y);
		return coordinate[0] >= 0 && coordinate[0] < 13 &&
			coordinate[1] >= 0 && coordinate[1] < PAGE_ROOM_COUNT;
	}
	
	private void resetLocationArray(){
		for(int x = 0;x<mouseLocation.length;x++){
			for(int y=0;y<mouseLocation[0].length;y++){
				mouseLocation[x][y] = false;
			}
		}
	}

	public void mousePressed(int x, int y) {
		super.mousePressed(x, y);

		int[] coordinate = calculateMouseLocation(x, y);
		int xNew = coordinate[0];
		int yNew = coordinate[1];

		if(isMouseOnReservationBox(x, y)){
			if(reservations[xNew][yNew] != null){
				Reservation rew = reservations[xNew][yNew];
				if(rew.getUsername().equals(this.view.loginView.loggedInUsername)){
					this.view.controller.removeReservation(reservations[xNew][yNew]);
					this.reservations[xNew][yNew] = null;
					//this.updateRooms();
				}
			}
			else{
				if(countReservations() < 3){
					resId++;
					this.reservations[xNew][yNew] = this.view.controller.reserveRoom(this.view.loginView.loginField.getText().trim(), this.rooms.get(yNew).getRoomID(), xNew);
					//this.updateRooms();
				}
			}
		}
	}

}
