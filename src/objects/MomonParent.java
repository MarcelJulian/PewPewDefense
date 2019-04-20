package objects;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import view.GameFrame;
import view.GamePanel;

public abstract class MomonParent {
	protected int dir = 0;
	protected int hp;
	protected int speed;
	protected int gold;
	protected Point coor;
	protected Rectangle hitBox;
	protected ImageIcon img;
	
	public MomonParent() {
		coor = new Point(GameFrame.BORDERSIZE, 7*GameFrame.TILESIZE + GameFrame.BORDERSIZE);
		setHitBox();
		
	}
	
	public Rectangle getHitBox() {
		return hitBox;
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
	
	public void setDir(int[][] pathCode) {
		
		Point temp;
		
		if(dir == 0) temp = new Point(GamePanel.getSimplifiedCoor(coor.x + GameFrame.TILESIZE-1),
				GamePanel.getSimplifiedCoor(coor.y + GameFrame.TILESIZE-1));
		else temp = new Point(GamePanel.getSimplifiedCoor(coor.x),
								GamePanel.getSimplifiedCoor(coor.y));
		dir = pathCode[temp.y][temp.x];
	}
	
	public void reduceHP(int dmg) {
		hp -= dmg;
	}
	
	public boolean isDead() {
		if(hp < 0 ) return true;
		return false;
	}
	
	public void setHitBox() {
		hitBox = new Rectangle(coor.x + 9, coor.y + 9, 30, 30);
	}
	
	public int getDir() {
		return dir;
	}

	public int getHp() {
		return hp;
	}

	public int getSpeed() {
		return speed;
	}

	public int getGold() {
		return gold;
	}

	public Point getCoor() {
		return coor;
	}

	public ImageIcon getImg() {
		return img;
	}

	
}
