package objects;

import java.awt.Point;

import javax.swing.ImageIcon;

import view.GameFrame;

public class ProjectileMissile extends Projectile{
	
	private Point maxCoor;
	
	public ProjectileMissile(int attack, int speed, int dir, Point coor, Point maxCoor, ImageIcon img) {
		super(attack, speed, dir, coor, img);
		this.maxCoor = maxCoor;
	}
	
	public void move() {
		switch(dir) {
		case 0:
			coor.y -= 2;
			break;
		case 1:
			coor.x += 2;
			break;
		case 2:
			coor.y += 2;
			break;
		case 3:
			coor.x -= 2;
			break;
		}
		
	}

	public boolean isOutOfRange() {
		Point temp = new Point(coor.x, coor.y);
		temp.x = (temp.x - GameFrame.BORDERSIZE) / GameFrame.TILESIZE;
		temp.y = (temp.y - GameFrame.BORDERSIZE) / GameFrame.TILESIZE;
		if(temp.equals(maxCoor))
			return true;
		return false;
	}
}
