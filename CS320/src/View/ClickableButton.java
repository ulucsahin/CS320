package View;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class ClickableButton {
	private int x,y,x_width,y_width;
	
	abstract void paint(Graphics g);
	
	protected void mouseMoved(int x,int y){
		
	}
	
	protected void mousePressed(int x,int y){
		Rectangle mouseArea = new Rectangle(x,y,x_width,y_width);
		
		if(mouseArea.contains(x, y)){
			doAction();
		}
	}
	
	abstract void doAction();
	

}
