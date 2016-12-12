package View;

import java.awt.Graphics;

public interface AbstractView {
	public void paint(Graphics g);

	public void mouseMoved(int x,int y);
	public void mousePressed(int x,int y);
}
