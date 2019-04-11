package view;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StatsPanel extends JPanel{
	private final int PANELWIDTH = 326;
	private final int PANELHEIGHT = GameFrame.GAMEHEIGHT + GameFrame.BORDERSIZE * 2;
	
	public StatsPanel() {
		setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
//		setBackground(Color.BLUE);
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintBorders(g);
	}
	
	public void paintBorders(Graphics g) {
		ImageIcon img = null;
		
		//Horizontal
		img = Tile.getBorders().get(2);
		for(int i=1; i <= (PANELWIDTH-GameFrame.BORDERSIZE*2)/GameFrame.BORDERSIZE + 1; i++) {
			int srcx = i * GameFrame.BORDERSIZE;

			g.drawImage(img.getImage(), srcx, 0, 
						srcx + GameFrame.BORDERSIZE, GameFrame.BORDERSIZE,
						24, 24, 40, 40, null);
			g.drawImage(img.getImage(), srcx, PANELHEIGHT - GameFrame.BORDERSIZE, 
						srcx + GameFrame.BORDERSIZE, PANELHEIGHT,
						24, 24, 40, 40, null);
			g.drawImage(img.getImage(), srcx, PANELHEIGHT, 
					srcx + GameFrame.BORDERSIZE, PANELHEIGHT + 1,
					0, 0, 16, 1, null);
		}
		
		//Vertical
		img = Tile.getBorders().get(1);
		for(int i=1; i <= GameFrame.TILECOUNTy*3; i++) {
			int srcy = i * GameFrame.BORDERSIZE;

			g.drawImage(img.getImage(), 0, srcy, 
						GameFrame.BORDERSIZE, srcy+GameFrame.BORDERSIZE,
						24, 24, 40, 40, null);
			g.drawImage(img.getImage(), PANELWIDTH-GameFrame.BORDERSIZE, srcy,
						PANELWIDTH, srcy + GameFrame.BORDERSIZE,
						24, 24, 40, 40, null);
		}
		
		//Corners
		img = Tile.getBorders().get(0);
		g.drawImage(img.getImage(), 0, 0, GameFrame.BORDERSIZE, GameFrame.BORDERSIZE,
									24, 24, 40, 40, null);
		g.drawImage(img.getImage(), PANELWIDTH - GameFrame.BORDERSIZE, 0, 
									PANELWIDTH, 
									GameFrame.BORDERSIZE,
									24, 24, 40, 40, null);
		g.drawImage(img.getImage(), 0, PANELHEIGHT - GameFrame.BORDERSIZE, 
									GameFrame.BORDERSIZE, PANELHEIGHT + 1,
									24, 24, 41, 41, null);
		g.drawImage(img.getImage(), PANELWIDTH - GameFrame.BORDERSIZE, 
									PANELHEIGHT - GameFrame.BORDERSIZE, 
									PANELWIDTH, PANELHEIGHT + 1,
									24, 24, 41, 41, null);
			
	}
	
}
