/**
 * @file ElementMultTest.java
 * @author loicm
 * @date 20 mai 2018 | 12:13:13
 */

package fr.element;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElementMultTest {

	private ElementChar e1;
	private ElementChar e2;
	private ElementChar e3;
	private ElementMult e;
	private static int X=1, Y=1, W=3, H=1;
	private static char C1 = 'm';
	private static char C2 = 'd';
	private static char C3 = 'r';
	private static char C0 = 'o';
	
	@BeforeEach
	void init() {
		e1 = new ElementChar(X,Y,C1);
		e2 = new ElementChar(X+1,Y,C2);
		e3 = new ElementChar(X+2,Y,C3);
		e = new ElementMult(e1,e2,e3);
	}
	
	void testVar(ElementMult e, int x, int y, int w, int h, char c1, char c2, char c3) {
		assertEquals(e.getX(),x);
		assertEquals(e.getY(),y);
		assertEquals(e.getWidth(),w);
		assertEquals(e.getHeight(),h);
		assertEquals(((ElementChar)e.getElement(0)).getC(),c1);
		assertEquals(((ElementChar)e.getElement(1)).getC(),c2);
		assertEquals(((ElementChar)e.getElement(2)).getC(),c3);
	}
	
	@Test
	void testConstructor() {
		testVar(e,X,Y,W,H,C1,C2,C3);
		ElementMult e4 = e.clone();
		testVar(e4,X,Y,W,H,C1,C2,C3);
	}
	
	@Test
	void changePosElem() {
		e1.move(-1, -1);
		e2.move(-2, -1);
		e3.move(-3, -1);
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
	void testMove() {
		e.move(0, 1);
		assertEquals(e1.getY(), 2);
		assertEquals(e2.getY(), 2);
		assertEquals(e3.getY(), 2);
	}
	
	@Test
	void testDraw() {
		e.setFrame(C0);
		assertEquals(e.getFrame(),C0);
		char[][] img = new char[5][3];
		e.draw(img);
		assertEquals(img[X][Y],e1.getC());
		assertEquals(img[X+1][Y],e2.getC());
		assertEquals(img[X+2][Y],e3.getC());
		for (int i = 0; i < img.length; i++) {
			for (int j = 0; j < img[0].length; j++) {
				if (i<X&&i>X+W&&j<Y&&j>Y+H)
					assertEquals(img[i][j],e.getFrame());
			}
		}
	}
}
