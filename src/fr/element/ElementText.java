/**
 * @file ElementText.java
 * @author loicm
 * @date 20 mai 2018 | 12:11:11
 */

package fr.element;

import java.util.Arrays;

public class ElementText extends ElementSuper {

	protected char[][] c;
	
	public ElementText(int x, int y, char[][] c) {
		super(x, y, c.length, c[0].length);
		this.c = c;
	}
	public ElementText(int x, int y, String s) {
		this(x,y,stringTo2DChar(s));
	}
	public ElementText(ElementText et) {
		this(et.x,et.y,et.c);
		this.setFrame(this.frame,this.frameThick);
	}

	public char[][] getText() {
		return c;
	}
	
	public void setValue(String s) {
		assert(s.length()>0);
		setValue(stringTo2DChar(s));
	}
	public void setValue(char[][] c) {
		this.c = c;
	}
	
	/**
	 * @param s
	 * @return the convert the String s into a 2D char array
	 */
	public static char[][] stringTo2DChar(String s) {
		assert(s.length() > 0);
		int w = 0,h = 0;
		String[] as = s.split("\n");
		h = as.length;
		for (int i = 0; i < h; i++)
			if (as[i].length() > w)
				w = as[i].length();
		char[][] tc = new char[w][h];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++) {
				if (j < as[i].length())
					tc[j][i] = as[i].charAt(j);
				else
					tc[j][i] = ' ';
			}
		return tc;
	}
	
	public String toString() {
		String s = "Text :\n\t";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				s+=c[j][i];
			}
		}
		return s;
	}
	
	@Override
	public boolean equals2(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		ElementText other = (ElementText) obj;
		if (!Arrays.deepEquals(c, other.c))
			return false;
		return true;
	}
	protected void draw(char[][] img, int x1, int y1, int x2, int y2) {
		if (frame != 0)
			drawFrame(img);
		for (int ty = y1, y3 = 0; ty < y2; ty++,y3++) {
			for (int tx = x1, x3 = 0; tx < x2; tx++,x3++) {
				img[x+x3][y+y3] = c[tx][ty];
			}
		}
	}
	
	@Override
	public void draw(char[][] img) {
		draw(img,0,0,width,height);
	}
	
	@Override
	public ElementText clone() {
		return new ElementText(this);
	}

}
