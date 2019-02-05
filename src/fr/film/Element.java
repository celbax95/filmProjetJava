/**
 * @file Element.java
 * @author loicm
 * @date 20 mai 2018 | 12:10:25
 */

package fr.film;

public interface Element {

	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	
	/**
	 * @brief Draw the element in a 2D char array
	 * @param img
	 */
	public void draw(char[][] img);
	/**
	 * @return a copy of the element
	 */
	public Element clone();

	public void move(int x, int y);
	/**
	 * @brief Remove the frame
	 */
	public void deleteFrame();
	/**
	 * @brief Add a frame of the c charcater
	 * @param c
	 */
	public void setFrame(char c);
	public boolean equals(Object o);
	/**
	 * @return true if the element is in (maxWidth;maxHeight)
	 */
	public boolean isIn(int maxWidth, int maxHeight);
}
