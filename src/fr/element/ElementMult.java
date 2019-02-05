/**
 * @file ElementMult.java
 * @author loicm
 * @date 20 mai 2018 | 12:11:06
 */

package fr.element;

import java.util.LinkedList;

public class ElementMult extends ElementSuper {

	LinkedList<ElementSuper> tE;
	
	public ElementMult() {
		super(0, 0, 0, 0);
		tE = new LinkedList<ElementSuper>();
	}
	public ElementMult(ElementSuper...e) {
		this();
		addElement(e);
	}
	public ElementMult(ElementMult em) {
		this();
		int t = em.tE.size();
		for (int i = 0; i < t; i++) {
			addElement(em.getElement(i).clone());
		}
		this.setFrame(this.frame,this.frameThick);
	}
	
	/**
	 * @brief remove all elements
	 */
	public void clean() {
		tE = new LinkedList<ElementSuper>();
	}
	
	/**
	 * @brief Update the x,y,width,height
	 */
	private void setHB() {
		if (tE.size() == 0)
			return;
		int t = tE.size();
		ElementSuper e = tE.get(0);
		x = e.x;
		y = e.y;
		width = e.width;
		height = e.height;
		if (t == 1)
			return;
		for (int i = 1; i < t; i++) {
			e = tE.get(i);
			if (e.x < x)
				x = e.x;
			if (e.y < y)
				y = e.y;
			if (e.x+e.width > x+width)
				width = e.x+e.width-x;
			if (e.y+e.height > y+height)
				height = e.y+e.height-y;
		}
	}
	
	public String toString() {
		String s = "Mult :\n";
		int t = tE.size();
		for (int i = 0; i < t; i++) {
			s += "["+i+"]" + "-"+tE.get(i).toString()+"\n";
		}
		return s;
	}
	public String toString(int ind) {
		assert(ind < tE.size() && ind >= 0);
		return tE.get(ind).toString();
	}

	public void move(int x, int y) {
		super.move(x, y);
		for (ElementSuper e : tE) {
			e.move(x, y);
		}
	}
	
	public ElementSuper getElement(int ind) {
		assert(ind < tE.size() && ind >= 0);
		return tE.get(ind);
	}
	
	/**
	 * @brief Swap to elements
	 * @param ind1
	 * @param ind1
	 */
	public void swapElements(int ind1, int ind2) {
		assert(ind1 < tE.size() && ind2 < tE.size()
				&& ind1 >= 0 && ind2 >= 0);
		ElementSuper e = tE.get(ind1);
		tE.set(ind1, tE.get(ind2));
		tE.set(ind2, e);
	}
	
	/**
	 * @brief Move the element at the ind place to the first place
	 * @param ind
	 */
	public void setFirst(int ind) {
		assert(ind < tE.size() && ind >= 0);
		if (!tE.getLast().equals(tE.get(ind))) {
			tE.addLast(tE.get(ind));
			tE.remove(ind);			
		}
	}
	/**
	 * @brief Move the element at the ind place to the last place
	 * @param ind
	 */
	public void setLast(int ind) {
		assert(ind < tE.size() && ind >= 0);
		if (!tE.getFirst().equals(tE.get(ind))) {
			tE.addFirst(tE.get(ind));			
			tE.remove(ind+1);
		}
	}
	
	public void addElement(ElementSuper e) {
		tE.add(e);
		setHB();
	}
	public void addElement(ElementSuper...e) {
		for (int i = 0; i < e.length; i++) {
			addElement(e[i]);
		}
	}
	
	public void removeElement(int ind) {
		assert(ind < tE.size() && ind >= 0);
		tE.remove(ind);
		setHB();
	}
	public void removeElement(ElementSuper e) {
		assert(tE.contains(e));
		tE.remove(e);
		setHB();
	}
	
	public int getSize() {
		return tE.size();
	}
	
	@Override
	public boolean equals2(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		ElementMult other = (ElementMult) obj;
		if (tE == null) {
			if (other.tE != null)
				return false;
		} else if (!tE.equals(other.tE))
			return false;
		return true;
	}
	@Override
	public void draw(char[][] img) {
		if (frame != 0)
			drawFrame(img);
		int t = tE.size();
		for (int i = 0; i < t; i++) {
			tE.get(i).draw(img);
		}
	}
	@Override
	public ElementMult clone() {
		return new ElementMult(this);
	}
}
