/**
 * @file Image.java
 * @author loicm
 * @date 13 mai 2018 | 13:01:10
 */

package fr.film;

import java.util.LinkedList;

public class Image {

	private int width, height;
	private char bg;
	private LinkedList<Element> tE;
	
	public Image(int width, int height, char bg) {
		tE = new LinkedList<Element>();
		this.width = width;
		this.height = height;
		this.bg = bg;
	}
	public Image(int width, int height, char bg, Element...e) {
		this(width,height,bg);
		addElement(e);
	}
	public Image(Image img) {
		this(img.width, img.height, img.bg);
		for (Element e : img.tE) {
			tE.add(e.clone());
		}
	}
	
	public char getBg() {
		return bg;
	}
	
	public int getSize() {
		return tE.size();
	}
	
	public Element getElement(int ind) {
		return tE.get(ind);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	/**
	 * @brief Remove all elements
	 */
	public void clean() {
		tE = new LinkedList<Element>();
	}
	
	/**
	 * @brief Swap to elements
	 * @param ind1
	 * @param ind1
	 */
	public void swapElements(int ind1, int ind2) {
		assert(ind1 < tE.size() && ind2 < tE.size()
				&& ind1 >= 0 && ind2 >= 0);
		Element e = tE.get(ind1);
		tE.set(ind1, tE.get(ind2));
		tE.set(ind2, e);
	}
	
	/**
	 * @brief Move the element at the ind place to the first place
	 * @param ind
	 */
	public void setFirst(int ind) {
		assert(ind < tE.size() && ind >= 0);
		if (!tE.getLast().equals(tE.get(ind))) {
			tE.addLast(tE.get(ind));
			tE.remove(ind);			
		}
	}
	/**
	 * @brief Move the element at the ind place to the last place
	 * @param ind
	 */
	public void setLast(int ind) {
		assert(ind < tE.size() && ind >= 0);
		if (!tE.getFirst().equals(tE.get(ind))) {
			tE.addFirst(tE.get(ind));			
			tE.remove(ind+1);
		}
	}
	
	public void addElement(Element e) {
		if (!e.isIn(width,height))
			throw new NotInImageException(e.getX(),e.getY(),
					e.getWidth(),e.getHeight(),width,height);
		tE.add(e);
	}
	public void addElement(Element...e) {
		for (int i = 0; i < e.length; i++) {
			addElement(e[i]);
		}
	}
	
	public void removeElement(int ind) {
		assert(ind < tE.size() && ind >= 0);
		tE.remove(ind);
	}
	public void removeElement(Element e) {
		assert(tE.contains(e));
		tE.remove(e);
	}
	
	public void draw(char[][] img) {
		int t = tE.size();
		try {
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
					img[i][j] = bg;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DimensionException(width,height,img.length,img[0].length);
		}
		for (int i = 0; i < t; i++) {
			if (tE.get(i).isIn(width, height))
				tE.get(i).draw(img);
			else
				throw new NotInImageException(i,width,height); 
		}
	}
	public String toString() {
		char[][] c = new char[width][height];
		draw(c);
		String s = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				s += c[j][i];
			}
			if (i+1<height)
				s+="\n";
		}
		return s;
	}
	
	public Image clone() {
		return new Image(this);
	}
	
	/**
	 * @see toString
	 * @brief Add the background character between each character
	 */
	public String toStringWide() {
		char[][] c = new char[width][height];
		draw(c);
		String s = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				s += bg+""+c[j][i]+""+bg;
			}
			if (i+1<height)
				s+="\n";
		}
		return s;
	}
}
