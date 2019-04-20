package objects;

import java.util.Vector;

public class Wave {
	private Vector<MomonParent> momons = new Vector<MomonParent>();
	
	public void addMomon(MomonParent momon) {
		momons.add(momon);
	}

	public Vector<MomonParent> getMomons() {
		return momons;
	}
	
	
}
