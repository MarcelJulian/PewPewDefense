package objects;

import view.Tile;

public class MomonSoldier2 extends MomonParent{
	
	public MomonSoldier2() {
		super();
		hp = 250;
		speed = 25;
		gold = 10;
		img = Tile.getMomons().get(2);
		
	}
}
