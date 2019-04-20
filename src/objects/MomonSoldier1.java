package objects;

import view.Tile;

public class MomonSoldier1 extends MomonParent{
	
	public MomonSoldier1() {
		super();
		hp = 1000;
		speed = 75;
		gold = 10;
		img = Tile.getMomons().get(1);
		
	}
}
