/**
 * @file ElementSuper.java
 * @author loicm
 * @date 20 mai 2018 | 12:10:33
 */

package fr.element;

import fr.film.Element;

public abstract class ElementSuper implements Element {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected char frame;
	protected int frameThick;
	
	protected ElementSuper(int x, int y, int width, int height, char frame) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.frame = frame;
	}
	
	protected ElementSuper(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.frame = 0;
	}
	
	public abstract void draw(char[][] img);
	public abstract ElementSuper clone();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementSuper other = (ElementSuper) obj;
		if (frame != other.frame)
			return false;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (!equals2(obj))
			return false;
		return true;
	}
	
	public abstract boolean equals2(Object o);
	
	public void move(int x, int y) {
		this.x+=x;
		this.y+=y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public char getFrame() {
		return frame;
	}
	
	public void deleteFrame() {
		frame = 0;
	}
	public void setFrame(char c, int frameThick) {
		frame = c;
		this.frameThick = frameThick;
	}
	public void setFrame(char c) {
		setFrame(c,1);
	}

	/**
	 * @brief Draw the frame of the element in a 2D char array
	 * @param img
	 */
	protected void drawFrame(char[][] img) {
		assert(frame != 0);
		for (int i = x-frameThick; i <= x+width+frameThick-1; i++) {
			for (int j = 0; j < frameThick; j++) {
				if (y-1-j >= 0 && i < img.length && i >= 0)
					img[i][y-1-j] = frame;
				if (y+height+j < img[0].length && i < img.length && i >= 0)
					img[i][y+height+j] = frame;				
			}
		}
		for (int j = 0; j < frameThick; j++) {
			for (int i = y-1>=0?y-1:y; i <= y+height; i++) {
				if (x-1-j >= 0 && i < img[0].length && i >= 0)
					img[x-1-j][i] = frame;
				if (x+width+j < img.length && i < img[0].length && i >= 0)
					img[x+width+j][i] = frame;
			}
		}
	}
	
	public boolean isIn(int maxWidth, int maxHeight) {
		return (x+width <= maxWidth && x >= 0
				&& y+height <= maxHeight && y >= 0);
	}
	
}
