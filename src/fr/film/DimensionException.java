/**
 * @file DimensionError.java
 * @author loicm
 * @date 14 mai 2018 | 12:04:48
 */

package fr.film;

public class DimensionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DimensionException(int w1, int h1, int w2, int h2) {
		super("The image ("+w1+";"+h1+") do not respect the requiered dimensions for this film ("+w2+";"+h2+")");
	}
}
