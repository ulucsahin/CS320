package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import System.Room;

public abstract class ClickableButton {
	int x,y,x_width,y_width;
	boolean isMouseOnTop = false;
	
	public ClickableButton(int x,int y,int x_width,int y_width){
		this.x = x;
		this.y = y;
		this.x_width = x_width;
		this.y_width = y_width;
	}
	
	abstract void paint(Graphics g);
	
	protected void mouseMoved(int x,int y){
		Rectangle mouseArea = new Rectangle(this.x,this.y,x_width,y_width);
		
		if(mouseArea.contains(x, y)){
			isMouseOnTop = true;
		}
		else{
			isMouseOnTop = false;
		}
	}
	
	protected void mousePressed(int x,int y){
		Rectangle mouseArea = new Rectangle(this.x,this.y,x_width,y_width);
		
		if(mouseArea.contains(x, y)){
			doAction();
		}
	}
	
	abstract void doAction();
	

}

class LoginButton extends ClickableButton{
	
	public LoginButton(int x, int y, int x_width, int y_width) {
		super(x, y, x_width, y_width);
	}

	LoginView loginView;
	private static final String buttonText = "LOGIN";
	
	protected void attachLoginView(LoginView view){
		loginView = view;
	}

	void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, x_width, y_width);
		g.setColor(Color.BLUE);
		g.fillRect(x+2, y+2, x_width-4, y_width-4);
		
		if(isMouseOnTop){
			g.setColor(Color.YELLOW);
			g.fillRect(x+4, y+4, x_width-8, y_width-8);
		}
		else{
			g.setColor(Color.CYAN);
			g.fillRect(x+4, y+4, x_width-8, y_width-8);
		}
		
		g.setFont(new Font("Courier",Font.BOLD,20));
		g.setColor(Color.DARK_GRAY);
		
		g.drawString(buttonText, x+x_width/5, (int)(y+y_width/1.4));
		
	}

	void doAction() {
		loginView.login();
	}
	
}

class RoomButton extends ClickableButton{
	Room room;
	boolean[] reservations;

	public RoomButton(Room room,int x, int y, int x_width, int y_width) {
		super(x, y, x_width, y_width);
		this.room = room;
	}
	
	private void initializeReservations(){
		reservations = new boolean[14];
		
		for(int i = 0;i<reservations.length;i++){
			reservations[i] = false;
		}
	}

	void paint(Graphics g) {	
		g.setColor(new Color(100,100,255));
		g.fillRect(x, y, x_width, y_width);
		g.setColor(Color.white);
		g.fillRect(x+2, y+2, x_width-4, y_width-4);
		
	}

	void doAction() {
	}
}
