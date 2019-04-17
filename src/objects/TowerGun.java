package objects;

import java.awt.Point;
import java.util.Vector;

import javax.swing.ImageIcon;

import mainControl.Main;
import view.GameFrame;
import view.GamePanel;
import view.Tile;

public class TowerGun extends TowerAbstract{
	private final static int INITATTACK = 30;
	private final static int INITSPEED = 200; 
	private final static int INITCOST = 75;
	private final static int INITPROJSPEED = 0;
	private final ImageIcon projImg = Tile.getProjectiles().get(2);
	private int gunDir = 1;
	
	public TowerGun(int dir, Point coor, Vector<Point> ranges) {
		super(INITATTACK, INITSPEED, dir, INITCOST, coor, ranges);
		img = Tile.getTower().get(4);
	}

	@Override
	public void fire() {
		Point tempCoor = new Point(GamePanel.getTileCoor(coor.x), 
									GamePanel.getTileCoor(coor.y) - 32);
		Main.addProjectile(
				new ProjectileGun(attack, INITPROJSPEED, dir, gunDir, tempCoor, projImg));
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		
	}

	public void setGunDir(int gunDir) {
		this.gunDir = gunDir;
	}
	
	public int getGunDir() {
		return gunDir;
	}

}
