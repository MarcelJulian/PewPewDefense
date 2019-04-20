package objects;

import java.awt.Point;
import java.util.Vector;

import javax.swing.ImageIcon;

public abstract class TowerAbstract {
	protected int attack;
	protected int speed;
	protected int dir;
	protected int cost;
	protected int level;
	protected Point coor;
	protected ImageIcon img;
	protected Vector<Point> ranges;

	public TowerAbstract(int attack, int speed, int dir, int cost, Point coor, Vector<Point> ranges) {
		this.attack = attack;
		this.speed = speed;
		this.dir = dir;
		this.cost = cost;
		this.coor = coor;
		this.ranges = ranges;
		level = 1;
	}
	
	public abstract void fire();

	public int getAttack() {
		return attack;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDir() {
		return dir;
	}

	public int getCost() {
		return cost;
	}

	public int getLevel() {
		return level;
	}

	public Point getCoor() {
		return coor;
	}

	public ImageIcon getImg() {
		return img;
	}
	
	public Vector<Point> getRanges() {
		return ranges;
	}
	
}
