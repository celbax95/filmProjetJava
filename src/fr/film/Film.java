/**
 * @file Film.java
 * @author loicm
 * @date 13 mai 2018 | 13:01:02
 */

package fr.film;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Film {

	private int width, height;
	
	private ArrayList<Image> tI;
	
	public Film(int width, int height) {
		tI = new ArrayList<Image>();
		this.width = width;
		this.height = height;
	}
	public Film(int width, int height, Image...img) {
		this(width, height);
		for (int i = 0; i < img.length; i++) {
			addImage(img[i]);
		}
	}
	
	public int getSize() {
		return tI.size();
	}
	
	public Image getImage(int ind) {
		assert(ind >= 0 && ind < tI.size());
		return tI.get(ind);
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public ArrayList<Image> gettI() {
		return tI;
	}
	public void clear() {
		tI = new ArrayList<Image>();
	}
	
	public void addImage(Image img) {
		if (img.getWidth() != width || img.getHeight() != height)
			throw new DimensionException(img.getWidth(),img.getHeight(),width,height);
		tI.add(img);
	}
	public void addImage(Image...img) {
		for (int i = 0; i < img.length; i++) {
			addImage(img[i]);
		}
	}
	
	public void removeImage(int ind) {
		assert(ind >= 0 && ind < tI.size());
		tI.remove(ind);
	}
	
	public String toString() {
		String s = width+" "+height+"\n";
		int t = tI.size();
		for (int i = 0; i < t; i++) {
			s += tI.get(i).toString();
			if (i+1<t)
				s += "\n\\newframe\n";
		}
		return s;
	}
	
	/**
	 * @see toStringWide() of Image
	 */
	public String toStringWide() {
		String s = (width*3)+" "+height+"\n";
		int t = tI.size();
		for (int i = 0; i < t; i++) {
			s += tI.get(i).toStringWide();
			if (i+1<t)
				s += "\n\\newframe\n";
		}
		return s;
	}
	
	/**
	 * @brief Create a readable by cm-player file named fileName
	 * @param fileName
	 * @throws IOException
	 */
	public void createFilm(String fileName) throws IOException {
		if (!fileName.endsWith(".txt"))
			fileName+=".txt";
		PrintWriter out = new PrintWriter(fileName, "UTF-8");
		out.print(toString());
		out.close();
	}
	
	/**
	 * @brief Create a readable by cm-player file named fileName using toStringWide()
	 * @param fileName
	 * @see toStringWide()
	 * @throws IOException
	 */
	public void createFilmWide(String fileName) throws IOException {
		if (!fileName.endsWith(".txt"))
			fileName+=".txt";
		PrintWriter out = new PrintWriter(fileName, "UTF-8");
		out.print(toStringWide());
		out.close();
	}
}
