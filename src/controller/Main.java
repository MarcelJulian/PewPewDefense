package controller;

import model.GameMap;
import view.GameFrame;

public class Main {
	private static GameMap map = new GameMap("res/Map/Map_1.txt");
	private int[][] mapCode = map.getMapCode();
	
	public Main() {
//		new GameFrame();
		
	}
	
	public static void main(String[] args) {
		new Main();

	}
	
	public static GameMap getMap(){
		return map;
	}

}
