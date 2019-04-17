package objects;

import java.awt.Point;

import javax.swing.ImageIcon;

public class ProjectileGun extends Projectile{
	
	private int gunDir;

	public ProjectileGun(int attack, int speed, int dir, int gunDir, Point coor, ImageIcon img) {
		super(attack, speed, dir, coor, img);
		this.gunDir = gunDir;
	}
	
	public int getGunDir() {
		return gunDir;
	}
}
