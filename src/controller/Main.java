package controller;

import model.GameMap;
import view.GameFrame;
import view.Tile;

public class Main {
	private static GameMap map = new GameMap("res/Map/Map_1.txt");
	
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

}
