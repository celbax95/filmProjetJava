package fr.main;

import java.io.IOException;

import fr.element.ElementChar;
import fr.element.ElementLine;
import fr.film.Film;
import fr.film.Image;

public class Main {
	
	// img[x][y]
	
	public static void main(String[] args) throws IOException {
		Image img;
	
//		ElementChar tt = new ElementChar(14,1,"|");
//		ElementLine tg = new ElementLine(13, 2, 7, 8, "/");
//		ElementLine td = tg.clone();
//		td.rotate(-90); td.move(2, 0); td.setValue("\\");
//		ElementLine p = new ElementLine(8,8,20,8,"=");
//		ElementLine mg = new ElementLine(9,9,9,17,"|");
//		ElementLine md = mg.clone();
//		md.move(10, 0);
//		ElementLine fl = new ElementLine(10,17,18,17,"=");
//		ElementText d = new ElementText(11,13,"xx\nxx\nox\nxx");
//		ElementText w = new ElementText(15,11,"xxx\nxox\nxxx");
//		
//		ElementMult house = new ElementMult(tt,tg,td,p,mg,md,fl,d,w);
//		ElementMult house2 = house.clone();
//		house2.move(house.getWidth()+2, 0);
//		house.move(0-house.getX(), 0);
//		
//		house.setFrame('o');
//		house2.setFrame('x');
		
		//img = new Image(50,50,' ',house,house2);
		
//		ElementLine e = new ElementLine(24,24,24,1,'x');
		img = new Image(50,50,' ');
//		Film f = new Film(50, 50);
//		f.addImage(img);
//		for (int i = 0; i < 180; i++) {
//			f.addImage(img.clone());
//			e.rotate(2);
//		}	
//		f.createFilmWide("yolo");
		
		ElementChar c = new ElementChar(5, 5, "a");
		c.setFrame('o',3);
		img.addElement(c);
		System.out.println(img);
	}
}
