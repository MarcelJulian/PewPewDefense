package mainControl;

import java.util.Vector;

import objects.*;
import view.*;

public class Main {
	private static GameMap map = new GameMap("res/Map/Map_1.txt");
	private static Vector<TowerAbstract> towers = new Vector<>();
	private static Vector<Projectile> projectiles = new Vector<>();
	
	public Main() {
		Tile.init();
		new GameFrame();
		
	}
	
	public static void main(String[] args) {		
		new Main();

	}
	
	public static GameMap getMap(){
		return map;
	}
	
	public static void addTower(TowerAbstract temp) {
		towers.add(temp);
	}
	
	public static void addProjectile(Projectile temp) {
		projectiles.add(temp);
	}

	public static Vector<TowerAbstract> getTowers() {
		return towers;
	}

	public static Vector<Projectile> getProjectiles() {
		return projectiles;
	}
	

}
