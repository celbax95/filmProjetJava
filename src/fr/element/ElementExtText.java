/**
 * @file ElementExtText.java
 * @author loicm
 * @date 14 mai 2018 | 11:48:46
 */

package fr.element;

public class ElementExtText extends ElementText {
	
	private int x1, y1, x2, y2;
	
	public ElementExtText(int x, int y, char[][] c, int x1, int y1, int x2, int y2) {
		super(x,y,c);
		assert(x1 <= x2 && y1 <= y2);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.width = x2-x1+1;
		this.height = y2-y1+1;
	}
	public ElementExtText(int x, int y, String s, int x1, int y1, int x2, int y2) {
		this(x,y,stringTo2DChar(s),x1,y1,x2,y2);
	}
	public ElementExtText(ElementExtText et) {
		this(et.x,et.y,et.c,et.x1,et.y1,et.x2,et.y2);
	}
	
	public int getX1() {
		return x1;
	}
	public int getY1() {
		return y1;
	}
	public int getX2() {
		return x2;
	}
	public int getY2() {
		return y2;
	}
	/**
	 * @brief Move the selection of the original text
	 */
	public void setPos(int x1, int y1, int x2, int y2) {
		assert(x1<=x2 && y1<=y2);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public boolean equals2(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementExtText other = (ElementExtText) obj;
		if (x1 != other.x1)
			return false;
		if (x2 != other.x2)
			return false;
		if (y1 != other.y1)
			return false;
		if (y2 != other.y2)
			return false;
		return true;
	}
	@Override
	public void draw(char[][] img) {
		this.draw(img, x1, y1, x2+1, y2+1);
		if (frame != 0)
			drawFrame(img);
	}
	
	@Override
	public ElementExtText clone() {
		return new ElementExtText(this);
	}
}
