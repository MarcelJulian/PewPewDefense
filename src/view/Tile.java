	package view;

	import java.util.ArrayList;

	import javax.swing.ImageIcon;

public class Tile {
	private static ArrayList <ImageIcon> bullets = new ArrayList<>();
	private static ArrayList <ImageIcon> borders = new ArrayList<>();
	private static ArrayList <ImageIcon> deco = new ArrayList<ImageIcon>();
	private static ArrayList <ImageIcon> deco_red = new ArrayList<>();
	private static ArrayList <ImageIcon> icon = new ArrayList<>();
	private static ArrayList <ImageIcon> momon = new ArrayList<>();
	private static ArrayList <ImageIcon> tiles = new ArrayList<>();
	private static ArrayList <ImageIcon> tiles_red = new ArrayList<>(); 
	private static ArrayList <ImageIcon> tower = new ArrayList<>();
	private static ArrayList <ImageIcon> tower_h = new ArrayList<>();
	 
	 public static void init() {
		 bullets.add(new ImageIcon("res/Images/bullet_1.png"));
		  bullets.add(new ImageIcon("res/Images/bullet_2.png"));
		  bullets.add(new ImageIcon("res/Images/bullet_3.png"));
		  bullets.add(new ImageIcon("res/Images/bullet_4.png"));
		  bullets.add(new ImageIcon("res/Images/bullet_5.png"));
		  bullets.add(new ImageIcon("res/Images/bullet_6.png"));
		  bullets.add(new ImageIcon("res/Images/bullet_7.png"));
		  bullets.add(new ImageIcon("res/Images/bullet_8.png"));
		  bullets.add(new ImageIcon("res/Images/bullet_9.png"));
		  bullets.add(new ImageIcon("res/Images/bullet_10.png"));
		  borders.add(new ImageIcon("res/Images/border_0.png"));
		  borders.add(new ImageIcon("res/Images/border_1.png"));
		  borders.add(new ImageIcon("res/Images/border_2.png"));
		  deco.add(new ImageIcon("res/Images/bush_1.png"));
		  deco.add(new ImageIcon("res/Images/bush_2.png"));
		  deco.add(new ImageIcon("res/Images/bush_3.png"));
		  deco.add(new ImageIcon("res/Images/bush_4.png"));
		  deco.add(new ImageIcon("res/Images/bush_5.png"));
		  deco.add(new ImageIcon("res/Images/stone_1.png"));
		  deco.add(new ImageIcon("res/Images/stone_2.png"));
		  deco.add(new ImageIcon("res/Images/stone_3.png"));
		  deco_red.add(new ImageIcon("res/Images/bush_1_red.png"));
		  deco_red.add(new ImageIcon("res/Images/bush_2_red.png"));  
		  deco_red.add(new ImageIcon("res/Images/bush_3_red.png"));
		  deco_red.add(new ImageIcon("res/Images/bush_4_red.png"));
		  deco_red.add(new ImageIcon("res/Images/bush_5_red.png"));
		  deco_red.add(new ImageIcon("res/Images/stone_1_red.png"));
		  deco_red.add(new ImageIcon("res/Images/stone_2_red.png")); 
		  deco_red.add(new ImageIcon("res/Images/stone_3_red.png"));
		  icon.add(new ImageIcon("res/Images/icon_0.png"));
		  icon.add(new ImageIcon("res/Images/icon_1.png"));
		  icon.add(new ImageIcon("res/Images/icon_2.png"));
		  icon.add(new ImageIcon("res/Images/icon_3.png"));
		  icon.add(new ImageIcon("res/Images/icon_4.png"));
		  icon.add(new ImageIcon("res/Images/icon_5.png"));
		  icon.add(new ImageIcon("res/Images/icon_6.png"));
		  icon.add(new ImageIcon("res/Images/icon_7.png"));
		  icon.add(new ImageIcon("res/Images/icon_8.png"));
		  icon.add(new ImageIcon("res/Images/icon_9.png"));
		  icon.add(new ImageIcon("res/Images/icon_dollar.png"));
		  icon.add(new ImageIcon("res/Images/icon_percent.png"));
		  icon.add(new ImageIcon("res/Images/icon_plus.png"));
		  icon.add(new ImageIcon("res/Images/icon_titik.png"));
		  icon.add(new ImageIcon("res/Images/icon_titikdua.png"));
		  momon.add(new ImageIcon("res/Images/momon_1.png"));
		  momon.add(new ImageIcon("res/Images/momon_2.png"));
		  momon.add(new ImageIcon("res/Images/momon_3.png"));
		  momon.add(new ImageIcon("res/Images/momon_4.png"));
		  momon.add(new ImageIcon("res/Images/momon_5.png"));
		  momon.add(new ImageIcon("res/Images/momon_6.png"));
		  momon.add(new ImageIcon("res/Images/momon_7.png"));
		  momon.add(new ImageIcon("res/Images/momon_8.png"));
		  momon.add(new ImageIcon("res/Images/momon_9.png"));
		  momon.add(new ImageIcon("res/Images/momon_10.png"));
		  momon.add(new ImageIcon("res/Images/momon_11.png"));
		  momon.add(new ImageIcon("res/Images/momon_12.png"));
		  
		  tiles.add(new ImageIcon("res/Images/tile_0.png"));
		  tiles.add(new ImageIcon("res/Images/tile_1.png"));
		  tiles.add(new ImageIcon("res/Images/tile_2.png"));
		  tiles.add(new ImageIcon("res/Images/tile_3.png"));
		  tiles.add(new ImageIcon("res/Images/tile_4.png"));
		  tiles.add(new ImageIcon("res/Images/tile_5.png"));
		  tiles.add(new ImageIcon("res/Images/tile_6.png"));
		  tiles.add(new ImageIcon("res/Images/tile_7.png"));
		  tiles.add(new ImageIcon("res/Images/tile_8.png"));
		  tiles.add(new ImageIcon("res/Images/tile_9.png"));
		  tiles.add(new ImageIcon("res/Images/tile_10.png"));
		  tiles.add(new ImageIcon("res/Images/tile_11.png"));
		  tiles.add(new ImageIcon("res/Images/tile_12.png"));
		  tiles.add(new ImageIcon("res/Images/tile_13.png"));
		  tiles.add(new ImageIcon("res/Images/tile_build.png"));
		  tiles_red.add(new ImageIcon("res/Images/tile_0_red.png"));
		  tiles_red.add(new ImageIcon("res/Images/tile_1_red.png"));
		  tiles_red.add(new ImageIcon("res/Images/tile_2_red.png"));
		  tiles_red.add(new ImageIcon("res/Images/tile_3_red.png"));
		  tiles_red.add(new ImageIcon("res/Images/tile_4_red.png"));
		  tiles_red.add(new ImageIcon("res/Images/tile_5_red.png"));
		  tiles_red.add(new ImageIcon("res/Images/tile_6_red.png"));
		  tiles_red.add(new ImageIcon("res/Images/tile_7_red.png"));
		  tiles_red.add(new ImageIcon("res/Images/tile_8_red.png"));
		  tiles_red.add(new ImageIcon("res/Images/tile_9_red.png"));
		  tower.add(new ImageIcon("res/Images/tower_1.png"));
		  tower.add(new ImageIcon("res/Images/tower_2.png"));
		  tower.add(new ImageIcon("res/Images/tower_3.png"));
		  tower.add(new ImageIcon("res/Images/tower_4.png"));
		  tower_h.add(new ImageIcon("res/Images/tower_1_h.png"));
		  tower_h.add(new ImageIcon("res/Images/tower_2_h.png"));
		  tower_h.add(new ImageIcon("res/Images/tower_3_h.png"));
		  tower_h.add(new ImageIcon("res/Images/tower_4_h.png"));
	 }

	public static ArrayList<ImageIcon> getBullets() {
		return bullets;
	}
	
	public static ArrayList<ImageIcon> getBorders() {
		return borders;
	}

	public static ArrayList<ImageIcon> getDeco() {
		return deco;
	}

	public static ArrayList<ImageIcon> getDeco_red() {
		return deco_red;
	}

	public static ArrayList<ImageIcon> getIcon() {
		return icon;
	}

	public static ArrayList<ImageIcon> getMomon() {
		return momon;
	}

	public static ArrayList<ImageIcon> getTiles() {
		return tiles;
	}

	public static ArrayList<ImageIcon> getTiles_red() {
		return tiles_red;
	}

	public static ArrayList<ImageIcon> getTower() {
		return tower;
	}
	 
}
