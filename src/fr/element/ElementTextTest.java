/**
 * @file ElementTextTest.java
 * @author loicm
 * @date 20 mai 2018 | 12:14:00
 */

package fr.element;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElementTextTest {

	private ElementText e;
	private static int X=1, Y=1;
	private static String S = "123\n456\n789";
	private static String S2 = "789\n456\n123";
	private static char[][] C = {{'1','4','7'},{'2','5','8'},{'3','6','9'}};
	private static char C2 = 'o';
	private static int W = 3;
	private static int H = 3;
	
	@BeforeEach
	void init() {
		e = new ElementText(X,Y,C);
	}
	
	void testVar(ElementText e, int x, int y, int w, int h, char[][] c) {
		assertEquals(e.getX(),x);
		assertEquals(e.getY(),y);
		assertEquals(e.getWidth(),w);
		assertEquals(e.getHeight(),h);
		char[][] t = e.getText();
		assertEquals(t.length,c.length);
		assertEquals(t[0].length,c[0].length);
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				assertEquals(t[i][j],c[i][j]);
			}
		}
	}
	
	@Test
	void testConstructor() {
		testVar(e,X,Y,W,H,C);
		ElementText e2 = new ElementText(X,Y,S);
		testVar(e2,X,Y,W,H,C);
		e2 = e.clone();
		testVar(e2,X,Y,W,H,C);
	}
	
	@Test
	void testSetValue() {
		e.setValue(S2);
		testVar(e,X,Y,W,H,ElementText.stringTo2DChar(S2));
	}
	
	@Test
	void testDraw() {
		e.setFrame(C2);
		assertEquals(e.getFrame(),C2);
		char[][] img = new char[5][5];
		e.draw(img);
		assertEquals(img[X][Y],e.getText()[0][0]);
		for (int i = 0; i < img.length; i++) {
			for (int j = 0; j < img[0].length; j++) {
				if (i<X&&i>X+W&&j<Y&&j>Y+H)
					assertEquals(img[i][j],e.getFrame());
			}
		}
	}
}
