package View;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class AbstractView {
	ArrayList<ClickableButton> buttons = new ArrayList<ClickableButton>();
	
	public void paint(Graphics g){
		for(ClickableButton b : buttons){
			b.paint(g);
		}
		
	}

	public void mouseMoved(int x,int y){
		for(ClickableButton b : buttons){
			b.mouseMoved(x, y);
		}
	}
	
	public void mousePressed(int x,int y){
		for(ClickableButton b : buttons){
			b.mousePressed(x, y);
		}
	}
	
}
