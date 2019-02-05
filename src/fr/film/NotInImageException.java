/**
 * @file NotInImageException.java
 * @author loicm
 * @date 14 mai 2018 | 12:04:40
 */

package fr.film;

public class NotInImageException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NotInImageException(int el, int width, int height) {
		super("The element "+el+" is not in the image's limits ("+width+"/"+height+")");
	}
	public NotInImageException(int x, int y, int w, int h, int width, int height) {
		super("The element (("+x+";"+y+");("+(x+w-1)+";"+(y+h-1)+")) is not in the image's limits ("+width+"/"+height+")");
	}
}
