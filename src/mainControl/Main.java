package mainControl;

import java.util.Vector;

import objects.*;
import view.*;

public class Main {
	private static int gold = 150;
	private static GameMap map = new GameMap("res/Map/Map_1.txt");
	private static Vector<TowerAbstract> towers = new Vector<>();
	private static Vector<Projectile> projectiles = new Vector<>();
	private static Vector<MomonParent> momons = new Vector<>();
	
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

	public static void addMomon(MomonParent temp) {
		momons.add(temp);
	}
	
	public static void removeMomon(MomonParent temp) {
		momons.remove(temp);
	}

	public static Vector<TowerAbstract> getTowers() {
		return towers;
	}

	public static Vector<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public static Vector<MomonParent> getMomons() {
		return momons;
	}
	
	public static int getGold() {
		return gold;
	}

	public static void addGold(int x) {
		gold += x;
	}
	
	public static void reduceGold(int x) {
		gold -= x;
	}

}
