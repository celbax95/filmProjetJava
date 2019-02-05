/**
 * @file FilmTest.java
 * @author loicm
 * @date 20 mai 2018 | 16:19:20
 */

package fr.film;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.element.ElementChar;

class FilmTest {

	private ElementChar e1;
	private ElementChar e2;
	private ElementChar e3;
	private ElementChar e4;
	private ElementChar e5;
	private ElementChar e6;
	private Image i1;
	private Image i2;
	private Film f;
	private static int X=0, Y=1, W=5, H=5;
	private static char C1 = 'm';
	private static char C2 = 'd';
	private static char C3 = 'r';
	private static char C0 = 'o';
	private static String fn = "Marine.txt";
	
	@BeforeEach
	void init() {
		e1 = new ElementChar(X,Y,C1);
		e2 = new ElementChar(X+1,Y,C2);
		e3 = new ElementChar(X+2,Y,C3);
		i1 = new Image(W,H,C0,e1,e2,e3);
		e4 = new ElementChar(X+2,Y+1,C1);
		e5 = new ElementChar(X+1,Y+1,C2);
		e6 = new ElementChar(X,Y+1,C3);
		i2 = new Image(W,H,C0,e4,e5,e6);
		f = new Film(W,H,i1,i2);
	}
	
	void testVar(Film f, int w, int h, int t) {
		assertEquals(f.getWidth(), w);
		assertEquals(f.getHeight(), w);
		assertEquals(f.getSize(), t);
	}
	
	@Test
	void testConstructor() {
		testVar(f,W,H,2);
	}
	
	@Test
	void testAddRemove() {
		assertThrows(AssertionError.class, ()->{f.removeImage(3);});
		f.removeImage(0);
		assertEquals(f.getSize(),1);
		f.clear();
		assertEquals(f.getSize(),0);
		f.addImage(i1,i2);
		assertEquals(f.getSize(),2);
	}

	@Test
	void testCreateFilm() throws IOException {
		f.createFilm(fn);
		File fl = new File(fn);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(fl);
		int cpt = 0;
		if (sc.hasNextLine())
			assertEquals(sc.nextLine(),(W+" "+H));
		while (sc.hasNextLine())
			if (sc.nextLine().equals("\\newframe"))
				cpt++;
		assertEquals(cpt,1);
		fl.delete();
	}
	
}
