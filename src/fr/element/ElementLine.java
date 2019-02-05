/**
 * @file ElementLine.java
 * @author loicm
 * @date 20 mai 2018 | 12:10:56
 */

package fr.element;

public class ElementLine extends ElementSuper {
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private double a; // angle en rad
	private double l; // length
	private char c;
	
	public ElementLine(int x1, int y1, int x2, int y2, char c) {
		super(x1 < x2 ? x1 : x2,y1 < y2 ? y1 : y2,Math.abs(x2-x1)+1,Math.abs(y2-y1)+1);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.c = c;
		
		this.l = calcLength();
		
		this.a = calcAngle();
		setHB();
	}
	public ElementLine(int x, int y, int x2, int y2, String s) {
		this(x,y,x2,y2,s.charAt(0));
	}
	public ElementLine(ElementLine el) {
		this(el.x1,el.y1,el.x2,el.y2,el.c);
		this.a = el.a;
		this.l = el.l;
		this.setFrame(this.frame,this.frameThick);
	}

	public int getX1() {
		return x1;
	}
	public int getY1() {
		return y1;
	}
	public int getX2() {
		return x2;
	}
	public int getY2() {
		return y2;
	}
	public double getA() {
		return Math.toDegrees(a);
	}
	public double getL() {
		return l;
	}
	public char getC() {
		return c;
	}
	/** 
	 * @return the length of the line
	 */
	private double calcLength() {
		return Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
	}
	
	/**
	 * @return the angle a between 0 and 2PI
	 */
	private double normAngle(double a) {
		return (a+Math.PI*2)%(Math.PI*2);
	}
	
	/**
	 * @return the angle of the line
	 */
	private double calcAngle() {
		double ta, tx = x2-x1, ty = y2-y1;
		ta = Math.acos(tx/l);
		if (ty<0)
			ta = Math.PI*2-ta;
		return normAngle(ta);
	}
	
	/**
	 * @brief Calculate and change the position of the second point
	 */
	private void updateP2() {
		x2 = x1+(int)Math.round(l*Math.cos(a));
		y2 = y1+(int)Math.round(l*Math.sin(a));
	}
	
	/**
	 * @brief rotate the line of d degree around P1
	 * @param d in degree
	 */
	public void rotate(int d) {
		a = normAngle(a+Math.toRadians(d));
		updateP2();
		setHB();
	}
	/**
	 * @brief set the angle of the line to d degree
	 * @param d
	 */
	public void setAngle(double d) {
		rotate((int)(d-Math.toDegrees(a)));
	}
	
	/**
	 * @brief Grow or shrink the line by l
	 * @param l
	 */
	public void grow(double l) {
		setLength(this.l + l);
	}
	/**
	 * @brief Set the length of the line to l
	 * @param l
	 */
	public void setLength(double l) {
		this.l = l;
		updateP2();
		setHB();
	}
	
	/**
	 * @brief update the x,y,width,height
	 */
	private void setHB() {
		this.x = x1 < x2 ? x1 : x2;
		this.y = y1 < y2 ? y1 : y2;
		this.width = Math.abs(x2-x1)+1;
		this.height = Math.abs(y2-y1)+1;
	}
	
	public void move(int x, int y) {
		super.move(x, y);
		x1+=x; y1+=y;
		x2+=x; y2+=y;
	}
	
	public void setP2(int x2, int y2) {
		this.x2 = x2;
		this.y2 = y2;
		l = calcLength();
		a = calcAngle();
		setHB();
	}
	
	public void setValue(String s) {
		assert(s.length()>0);
		setValue(s.charAt(0));
	}
	public void setValue(char c) {
		this.c = c;
	}
	
	public String toString() {
		return "Line :\n\tP1 : "+x1+","+y1+"\n\tP2 "+x2+","+y2+"\n\tc : "+c;
	}
	
	@Override
	public boolean equals2(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		ElementLine other = (ElementLine) obj;
		if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a))
			return false;
		if (c != other.c)
			return false;
		if (Double.doubleToLongBits(l) != Double.doubleToLongBits(other.l))
			return false;
		if (x1 != other.x1)
			return false;
		if (x2 != other.x2)
			return false;
		if (y1 != other.y1)
			return false;
		if (y2 != other.y2)
			return false;
		return true;
	}
	
	@Override
	public void draw(char[][] img) {
		if (frame != 0)
			drawFrame(img);
		double tx = x1, ty = y1, ax = Math.cos(a), ay = Math.sin(a);
		if (Math.abs(x2-x1) == Math.abs(y2-y1)) {
			ax = Math.signum(ax);
			ay = Math.signum(ay);
		}
		img[(int)tx][(int)ty] = c;
		while ((int)tx != x2 || (int)ty != y2) {
			if ((int)tx != x2)
				tx += ax;
			if ((int)ty != y2)
				ty += ay;
			img[(int)tx][(int)ty] = c;
		}
	}

	@Override
	public ElementLine clone() {
		return new ElementLine(this);
	}
}
