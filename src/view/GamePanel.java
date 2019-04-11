package view;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.Main;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	private final int PANELWIDTH = GameFrame.GAMEWIDTH + GameFrame.BORDERSIZE * 2;
	private final int PANELHEIGHT = GameFrame.GAMEHEIGHT + GameFrame.BORDERSIZE * 2;
	
	private Dimension maxSize = new Dimension(
			GameFrame.GAMEWIDTH + GameFrame.BORDERSIZE * 2, 
			GameFrame.GAMEHEIGHT + GameFrame.BORDERSIZE * 2);
	
	public GamePanel() {
		setPreferredSize(maxSize);
//		setBackground(Color.BLACK);
		repaint();
	
	}
		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, GameFrame.GAMEWIDTH, GameFrame.GAMEHEIGHT);
		paintMap(g);
		paintBorders(g);
//		
	}
	
	public void paintBorders(Graphics g) {
		ImageIcon img = null;
		
		//Horizontal
		img = Tile.getBorders().get(2);
		for(int i=1; i <= GameFrame.TILECOUNTx*3; i++) {
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
			g.drawImage(img.getImage(), PANELWIDTH - GameFrame.BORDERSIZE, srcy,
						PANELWIDTH, srcy + GameFrame.BORDERSIZE,
						24, 24, 40, 40, null);
		}
		
		//Corners
		img = Tile.getBorders().get(0);
		g.drawImage(img.getImage(), 0, 0, GameFrame.BORDERSIZE, GameFrame.BORDERSIZE,
									24, 24, 40, 40, null);
		g.drawImage(img.getImage(), PANELWIDTH - GameFrame.BORDERSIZE, 0, 
									PANELWIDTH, GameFrame.BORDERSIZE,
									24, 24, 40, 40, null);
		g.drawImage(img.getImage(), 0, PANELHEIGHT - GameFrame.BORDERSIZE, 
									GameFrame.BORDERSIZE, PANELHEIGHT + 1,
									24, 24, 41, 41, null);
		g.drawImage(img.getImage(), PANELWIDTH - GameFrame.BORDERSIZE, 
									PANELHEIGHT - GameFrame.BORDERSIZE, 
									PANELWIDTH, PANELHEIGHT + 1,
									24, 24, 41, 41, null);
		
		
	}
	
	public void paintMap(Graphics g) {
		int[][] mapCode = new int[GameFrame.TILECOUNTy][GameFrame.TILECOUNTx];
		mapCode = Main.getMap().getMapCode();
		ImageIcon img = null;
		
		for(int i=0; i<GameFrame.TILECOUNTy; i++) {
			for(int j=0; j<GameFrame.TILECOUNTx; j++) {

				if(mapCode[i][j] < 20) {
					img = Tile.getTiles().get(mapCode[i][j]);					
				}else if(mapCode[i][j] < 30) {
					img = Tile.getDeco().get(mapCode[i][j]-21);
					
				}
				
				int srcx = j*GameFrame.TILESIZE;
				int srcy = i*GameFrame.TILESIZE;
				if(j==0) srcx = 0;
				if(i==0) srcy = 0;
				srcx += GameFrame.BORDERSIZE;
				srcy += GameFrame.BORDERSIZE;
				g.drawImage(img.getImage(), srcx, srcy, 
						(j+1)*GameFrame.TILESIZE + GameFrame.BORDERSIZE, 
						(i+1)*GameFrame.TILESIZE + GameFrame.BORDERSIZE,
						0, 0, 64, 64, null);
			}
		}
		
	}
}
