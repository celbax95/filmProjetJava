/**
 * @file ElementExtTextTest.java
 * @author loicm
 * @date 20 mai 2018 | 12:12:33
 */

package fr.element;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElementExtTextTest {

	private ElementExtText e;
	private static int X=1, Y=1;
	private static String S = "123\n456\n789";
	private static String S2 = "789\n456\n123";
	private static char[][] C = {{'1','4','7'},{'2','5','8'},{'3','6','9'}};
	private static char C2 = 'o';
	private static int W = 3;
	private static int H = 1;
	
	@BeforeEach
	void init() {
		e = new ElementExtText(X,Y,C,0,1,2,1);
	}
	
	void testVar(ElementExtText e, int x, int y, int w, int h, char[][] c) {
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
		assertEquals(e.getX1(),0);
		assertEquals(e.getY1(),1);
		assertEquals(e.getX2(),2);
		assertEquals(e.getY2(),1);
		ElementExtText e2 = new ElementExtText(X,Y,S,0,1,2,1);
		testVar(e2,X,Y,W,H,C);
		e2 = e.clone();
		testVar(e2,X,Y,W,H,C);
		assertThrows(AssertionError.class, ()->{e = new ElementExtText(X,Y,S,2,2,1,1);});
	}
	
	@Test
	void testSetValue() {
		e.setValue(S2);
		testVar(e,X,Y,W,H,ElementExtText.stringTo2DChar(S2));
	}
	
	@Test
	void testSetPos() {
		assertThrows(AssertionError.class, ()->{e.setPos(2,2,1,1);});
		e.setPos(1, 1, 2, 2);
		assertEquals(e.getX1(),1);
		assertEquals(e.getY1(),1);
		assertEquals(e.getX2(),2);
		assertEquals(e.getY2(),2);
	}
	
	@Test
	void testDraw() {
		e.setFrame(C2);
		assertEquals(e.getFrame(),C2);
		char[][] img = new char[5][3];
		e.draw(img);
		assertEquals(img[X][Y],e.getText()[e.getX1()][e.getY1()]);
		assertEquals(img[X+W-1][Y+H-1],e.getText()[e.getX2()][e.getY2()]);
		for (int i = 0; i < img.length; i++) {
			for (int j = 0; j < img[0].length; j++) {
				if (i<X&&i>X+W&&j<Y&&j>Y+H)
					assertEquals(img[i][j],e.getFrame());
			}
		}
	}
}