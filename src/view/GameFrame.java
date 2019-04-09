package view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	
	public final static int TILESIZE = 48;
	public final static int TILECOUNTx = 21;
	public final static int TILECOUNTy = 14;
	public final static int WIDTH = TILESIZE * TILECOUNTx;
	public final static int HEIGHT = TILESIZE * TILECOUNTy;
	
	public GameFrame(){
		setTitle("Pew Pew Defense");
		setSize(WIDTH, HEIGHT);
		add(new GamePanel());
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
