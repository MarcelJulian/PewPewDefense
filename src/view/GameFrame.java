package view;

import java.awt.Graphics;

import javax.swing.JFrame;

public class GameFrame {
	JFrame window = new JFrame("Game");		
	Tile map = new Tile("testMap.txt", 32);
	public GameFrame() {
		// TODO Auto-generated constructor stub
		window.setSize(700,800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	public static void main(String[] args) {
//		window.setContentPane(new GamePanel());
//		window.pack();
		new GameFrame();
//		new Game();
//		
	}
	public void paint(Graphics g) {
		map.draw(g);
	}
}
