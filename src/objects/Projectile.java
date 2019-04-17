package objects;

import java.awt.Point;

import javax.swing.ImageIcon;

import view.GameFrame;

public class Projectile {
	protected int attack;
	protected int speed;
	protected int dir;
	protected Point coor;
	protected ImageIcon img;	
	
	public Projectile(int attack, int speed, int dir, Point coor, ImageIcon img) {
		this.attack = attack;
		this.speed = speed;
		this.dir = dir;
		this.coor = coor;
		this.img = img;
	}
	
	public int getAttack() {
		return attack;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDir() {
		return dir;
	}

	public Point getCoor() {
		return coor;
	}

	public ImageIcon getImg() {
		return img;
	}
}
