/**
 * @file ElementCharTest.java
 * @author loicm
 * @date 20 mai 2018 | 12:11:21
 */

package fr.element;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElementCharTest {

	private ElementChar e;
	private static int X=1, Y=1;
	private static char C = 'c';
	private static char C2 = 'o';
	private static String S = "c";
	
	@BeforeEach
	void init() {
		e = new ElementChar(X,Y,C);
	}
	
	void testVar(ElementChar e, int x, int y, int w, int h, char c) {
		assertEquals(e.getX(),x);
		assertEquals(e.getY(),y);
		assertEquals(e.getWidth(),w);
		assertEquals(e.getHeight(),h);
		assertEquals(e.getC(),c);
	}
	
	@Test
	void testConstructor() {
		testVar(e,X,Y,1,1,C);
		ElementChar e2 = new ElementChar(X,Y,S);
		assertEquals(e2.getC(),S.charAt(0));
		e2 = e.clone();
		testVar(e2,e.getX(),e.getY(),e.getWidth(),e.getHeight(),e.getC());
	}
	
	@Test
	void testSetValue() {
		e.setValue(C2);
		assertEquals(e.getC(), C2);
		e.setValue(S);
		assertEquals(e.getC(), S.charAt(0));
	}
	
	@Test
	void testDraw() {
		e.setFrame(C2);
		assertEquals(e.getFrame(),C2);
		char[][] img = new char[3][3];
		e.draw(img);
		assertEquals(img[X][Y],e.getC());
		for (int i = 0; i < img.length; i++) {
			for (int j = 0; j < img[0].length; j++) {
				if (i!=X&&j!=Y)
					assertEquals(img[i][j],e.getFrame());
			}
		}
	}
}
