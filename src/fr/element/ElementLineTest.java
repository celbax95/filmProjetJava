/**
 * @file ElementLineTest.java
 * @author loicm
 * @date 20 mai 2018 | 12:12:48
 */

package fr.element;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElementLineTest {

	private ElementLine e;
	private static int X1=5, Y1=5;
	private static int X2=5, Y2=1;
	private static int A = 360-90;
	private static int L = 4;
	private static char C = 'c';
	private static char C2 = 'o';
	private static String S = "c";
	
	@BeforeEach
	void init() {
		e = new ElementLine(X1,Y1,X2,Y2,C);
	}
	
	void testVar(ElementLine e, int x1, int y1, int x2, int y2,
			int w, int h, double a, double l, char c) {
		assertEquals(e.getX1(),x1);
		assertEquals(e.getY1(),y1);
		assertEquals(e.getX2(),x2);
		assertEquals(e.getY2(),y2);
		assertEquals(e.getX(),x1<x2?x1:x2);
		assertEquals(e.getY(),y1<y2?y1:y2);
		assertEquals(e.getHeight(),Math.abs(y2-y1)+1);
		assertEquals(e.getWidth(),Math.abs(x2-x1)+1);
		assertEquals(e.getA(),a,0.1);
		assertEquals(e.getL(),l,0.1);
		assertEquals(e.getC(),c);
	}
	
	@Test
	void testConstructor() {
		testVar(e,X1,Y1,X2,Y2,0,5,A,L,C);
		ElementLine e2 = new ElementLine(X1,Y1,X2,Y2,S);
		assertEquals(e2.getC(),S.charAt(0));
		e2 = e.clone();
		testVar(e,X1,Y1,X2,Y2,0,5,A,L,S.charAt(0));
	}
	
	@Test
	void testSetValue() {
		e.setValue(C2);
		assertEquals(e.getC(), C2);
		e.setValue(S);
		assertEquals(e.getC(), S.charAt(0));
	}
	
	@Test
	void testRotate() {
		e.rotate(90);
		testVar(e,X1,Y1,9,Y1,5,0,0d,L,C);
		e.setAngle(90);
		testVar(e,X1,Y1,X1,9,0,5,90,L,C);
	}
	
	@Test
	void testGrow() {
		e.grow(-2);
		testVar(e,X1,Y1,X2,Y2+2,0,2,A,2,C);
		e.setLength(4);
		testVar(e,X1,Y1,X2,Y2,0,5,A,L,C);
	}
	
	@Test
	void testDraw() {
		e.setFrame(C2);
		assertEquals(e.getFrame(),C2);
		char[][] img = new char[10][10];
		e.draw(img);
		assertEquals(img[X1][Y1],e.getC());
		assertEquals(img[X2][Y2],e.getC());
		assertEquals(img[e.getX()-1][e.getY()-1],e.getFrame());
		assertEquals(img[e.getX()+e.getWidth()][e.getY()+e.getWidth()],e.getFrame());
	}
}
