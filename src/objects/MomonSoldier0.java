package objects;

import view.Tile;

public class MomonSoldier0 extends MomonParent{
	
	public MomonSoldier0() {
		super();
		hp = 400;
		speed = 90;
		gold = 5;
		img = Tile.getMomons().get(0);
	}
}
