/**
 * @file ImageTest.java
 * @author loicm
 * @date 20 mai 2018 | 12:15:02
 */

package fr.film;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.element.ElementChar;

class ImageTest {

	private ElementChar e1;
	private ElementChar e2;
	private ElementChar e3;
	private Image e;
	private static int X=0, Y=1, W=3, H=3;
	private static char C1 = 'm';
	private static char C2 = 'd';
	private static char C3 = 'r';
	private static char C0 = 'o';
	
	@BeforeEach
	void init() {
		e1 = new ElementChar(X,Y,C1);
		e2 = new ElementChar(X+1,Y,C2);
		e3 = new ElementChar(X+2,Y,C3);
		e = new Image(W,H,C0,e1,e2,e3);
	}
	
	void testVar(Image e, int w, int h, char bg, char c1, char c2, char c3) {
		assertEquals(e.getWidth(),w);
		assertEquals(e.getHeight(),h);
		assertEquals(e.getBg(),bg);
		assertEquals(((ElementChar)e.getElement(0)).getC(),c1);
		assertEquals(((ElementChar)e.getElement(1)).getC(),c2);
		assertEquals(((ElementChar)e.getElement(2)).getC(),c3);
	}
	
	@Test
	void testConstructor() {
		testVar(e,W,H,C0,C1,C2,C3);
		Image e4 = e.clone();
		testVar(e4,W,H,C0,C1,C2,C3);
	}
	
	@Test
	void changePosElem() {
		e1.move(0, -1);
		e2.move(-1, -1);
		e3.move(-2, -1);
		e = new Image(1,1,C0,e1,e2,e3);
		char[][] img = new char[1][1];
		e.draw(img);
		assertThrows(AssertionError.class, ()->{e.swapElements(5,9);});
		assertEquals(img[0][0],e3.getC());
		e.swapElements(0, 2);
		e.draw(img);
		assertEquals(img[0][0],e1.getC());
		assertThrows(AssertionError.class, ()->{e.setFirst(-5);});
		e.setFirst(1);
		e.draw(img);
		assertEquals(img[0][0],e2.getC());
		assertThrows(AssertionError.class, ()->{e.setLast(10);});
		e.setLast(2);
		e.draw(img);
		assertEquals(img[0][0],e1.getC());
	}
	
	@Test
	void testAddRemove() {
		assertThrows(AssertionError.class, ()->{e.removeElement(10);});
		e.removeElement(2);
		assertEquals(e.getSize(), 2);
		e.removeElement(1);
		assertEquals(e.getSize(), 1);
		e.addElement(e2, e3);
		assertTrue(e.getElement(0).equals(e1));
		assertTrue(e.getElement(1).equals(e2));
		assertTrue(e.getElement(2).equals(e3));
		e.clean();
		assertEquals(e.getSize(),0);
	}
	
	@Test
	void testDraw() {
		final char[][] img1 = new char[2][3];
		assertThrows(DimensionException.class, ()->{e.draw(img1);});
		char[][] img = new char[3][3];
		e.draw(img);
		assertEquals(img[X][Y],e1.getC());
		assertEquals(img[X+1][Y],e2.getC());
		assertEquals(img[X+2][Y],e3.getC());
		for (int i = 0; i < img.length; i++) {
			for (int j = 0; j < img[0].length; j++) {
				if (i<X&&i>X+W&&j<Y&&j>Y+H)
					assertEquals(img[i][j],e.getBg());
			}
		}
		
		e1.move(-2, 0);
		assertThrows(NotInImageException.class, ()->{e.draw(img);});
	}
}
