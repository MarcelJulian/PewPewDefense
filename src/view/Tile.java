package view;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;

public class Tile {
	private int y;
	private int x;
	
	private int tileSize;
	private int [][] map;
	
	private int mapWidth;
	private int mapHeight;
	
	public Tile(String s, int tileSize) {
		this.tileSize = tileSize;
		try {
			BufferedReader br = new BufferedReader(new FileReader(s));
			mapWidth = Integer.parseInt(br.readLine());
			mapHeight = Integer.parseInt(br.readLine());
			map = new int[mapWidth][mapHeight];
			
			String delimiters = " ";
			
			for(int row = 0; row < mapHeight; row++ ) {
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for(int col = 0; col < mapWidth; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public void update() {
		
	}
	public void draw(Graphics g) {
		System.out.println("ASD");
		for(int row = 0; row < mapHeight; row++) {
			for(int col = 0; col < mapWidth; col++) {
				int rc = map [row][col];
				if(rc == 0) {
					g.setColor(Color.BLACK);
				}
				if(rc == 1) {
					g.setColor(Color.WHITE);
				}
				g.fillRect(x +col*tileSize, y + row*tileSize, tileSize, tileSize);
			}
			System.out.println();
		}
	}
}
