package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import controller.Main;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	public GamePanel() {
		
	
	}
		
	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, GameFrame.WIDTH, GameFrame.HEIGHT);
		
	}
	
	public void paintMap(Graphics g) {
		int[][] mapCode = new int[GameFrame.HEIGHT][GameFrame.WIDTH];
		mapCode = Main.getMap().getMapCode();
		for(int i=0; i<GameFrame.HEIGHT; i++) {
			for(int j=0; j<GameFrame.WIDTH; j++) {
				
			}
		}
	}
}
