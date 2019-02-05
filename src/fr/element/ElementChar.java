/**
 * @file ElementChar.java
 * @author loicm
 * @date 20 mai 2018 | 12:10:14
 */

package fr.element;

public class ElementChar extends ElementSuper {

	private char c;
	
	public ElementChar(int x, int y, char c) {
		super(x,y,1,1);
		this.c = c;
	}
	public ElementChar(int x, int y, String s) {
		this(x,y,s.charAt(0));
	}
	public ElementChar(ElementChar ec) {
		this(ec.x,ec.y,ec.c);
		this.setFrame(this.frame,this.frameThick);
	}

	public char getC() {
		return c;
	}
	
	public void setValue(String s) {
		assert(s.length()>0);
		setValue(s.charAt(0));
	}
	public void setValue(char c) {
		this.c = c;
	}
	
	public String toString() {
		return "Char :\n\t"+c;
	}
	
	@Override
	public boolean equals2(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		ElementChar other = (ElementChar) obj;
		if (c != other.c)
			return false;
		return true;
	}
	
	@Override
	public void draw(char[][] img) {
		if (frame != 0)
			drawFrame(img);
		img[x][y] = c;
	}
	
	@Override
	public ElementChar clone() {
		return new ElementChar(this);
	}
}