package objects;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import view.GameFrame;

public class ProjectileMissile extends Projectile{
	
	private Point maxCoor;
	private Rectangle hitBox;
	
	public ProjectileMissile(int attack, int speed, int dir, Point coor, Point maxCoor, ImageIcon img) {
		super(attack, speed, dir, coor, img);
		this.maxCoor = maxCoor;
		setHitBox();
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
		setHitBox();
		
	}

	public boolean isOutOfRange() {
		Point temp = new Point(coor.x, coor.y);
		temp.x = (temp.x - GameFrame.BORDERSIZE) / GameFrame.TILESIZE;
		temp.y = (temp.y - GameFrame.BORDERSIZE) / GameFrame.TILESIZE;
		if(temp.equals(maxCoor))
			return true;
		return false;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	public void setHitBox() {
		int srcX = coor.x;
		int srcY = coor.y;
		switch(dir) {
		case 0:
			srcX += 6;
			srcY += 8;
			break;
		case 1:
			srcX += 20;
			srcY += 8;
			break;
		case 2:
			srcX += 6;
			srcY += 20;
			break;
		case 3:
			srcX -= 8;
			srcY += 6;
			break;
		}
		hitBox = new Rectangle(srcX, srcY, 36, 36);
	}
}
