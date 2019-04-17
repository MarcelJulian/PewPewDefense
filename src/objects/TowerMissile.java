package objects;

import java.awt.Point;
import java.util.Vector;

import javax.swing.ImageIcon;

import mainControl.Main;
import view.*;

public class TowerMissile extends TowerAbstract{
	private final static int INITATTACK = 100;
	private final static int INITSPEED = 2000; //2 sec
	private final static int INITCOST = 50;
	private final static int INITPROJSPEED = 25;
	private final ImageIcon projImg = Tile.getProjectiles().get(0);
	
	public TowerMissile(int dir, Point coor, Vector<Point> ranges) {
		super(INITATTACK, INITSPEED, dir, INITCOST, coor, ranges);
		img = Tile.getTower().get(0);
	}

	@Override
	public void fire() {
		Point tempCoor = null;
		Point maxCoor = null;
		
		switch(dir) {
		case 0:
			tempCoor = new Point(GamePanel.getTileCoor(coor.x), 
							GamePanel.getTileCoor(coor.y) - 24);
			maxCoor = new Point(coor.x, GamePanel.checkOutOfBound(coor.y-6));
			break;
		case 1:
			tempCoor = new Point(GamePanel.getTileCoor(coor.x) + 8, 
							GamePanel.getTileCoor(coor.y));
			maxCoor = new Point(GamePanel.checkOutOfBound(coor.x+5), coor.y);
			break;
		case 2:
			tempCoor = new Point(GamePanel.getTileCoor(coor.x), 
							GamePanel.getTileCoor(coor.y) + 8);
			maxCoor = new Point(coor.x, GamePanel.checkOutOfBound(coor.y+5));
			break;
		case 3:
			tempCoor = new Point(GamePanel.getTileCoor(coor.x) - 8, 
							GamePanel.getTileCoor(coor.y));
			maxCoor = new Point(GamePanel.checkOutOfBound(coor.x-6), coor.y);
			break;
		}
		Main.addProjectile(new ProjectileMissile(attack, INITPROJSPEED, dir, tempCoor, maxCoor, projImg));
		
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		
	}


}
