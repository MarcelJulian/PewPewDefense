package objects;

import view.Tile;

public class MomonTank extends MomonParent{
	
	public MomonTank() {
		super();
		hp = 2500;
		speed = 120;
		gold = 25;
		img = Tile.getMomons().get(3);
		
	}
}
