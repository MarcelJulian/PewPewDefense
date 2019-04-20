package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mainControl.Main;
import objects.TowerGun;
import objects.TowerMissile;


@SuppressWarnings("serial")
public class StatsPanel extends JPanel{
	private final int PANELWIDTH = 326;
	private final int PANELHEIGHT = GameFrame.GAMEHEIGHT + GameFrame.BORDERSIZE * 2;

	private JLabel wave = new JLabel("Wave 1");
	private ImageIcon imgTowerMissile = Tile.getIcon().get(17);
	private ImageIcon imgTowerGun = Tile.getIcon().get(18);
	private ImageIcon imgStart = Tile.getIcon().get(15);
	private ImageIcon imgExit = Tile.getIcon().get(16);
	private JButton towerMissile = new JButton("", imgTowerMissile);
	private JButton towerGun = new JButton("", imgTowerGun);
	private JButton start = new JButton("", imgStart);
	private JButton exit = new JButton("", imgExit);
	private JLabel missile = new JLabel();
	private JLabel gun = new JLabel();
	
	Thread th = new Thread(new Runnable() {
		
		@Override
		public void run() {
			while(true) {
				
				String label = new String("Wave " + (Main.getMap().getCurWave()+1));
				if(GamePanel.getLose()) label = new String("You Lose.");
				else if(Main.getMap().getCurWave() == 4 && Main.getMomons().isEmpty()) {
					label = new String("You Won!");
				}
				wave.setText(label);

				
				repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	});
	
	public StatsPanel() {
		setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
		setLayout(null);

		wave.setBounds(120, 20, 150, 50);
		wave.setFont(new Font("Arial",Font.BOLD, 24));
		
		towerMissile.setBounds(18,PANELHEIGHT/2-180, 128, 128);
		towerGun.setBounds(18,PANELHEIGHT/2-32, 128, 128);
		
		start.setBounds(80,PANELHEIGHT-200,150,50);
		exit.setBounds(80,PANELHEIGHT-130,150,50);
		
		start.setBorderPainted(false);
		start.setBackground(Color.YELLOW);
		
		exit.setBorderPainted(false);
		exit.setBackground(Color.YELLOW);
		
		missile.setText("<html>Rocket Launcher"
				+ "<br>Damage      : " + TowerMissile.getInitattack()
				+ "<br>Radius      : 3 tiles, straight"
				+ "<br>Cost        : " + TowerMissile.getInitcost() 
				+ "</html>");
		missile.setBounds(150, PANELHEIGHT/2-207, 140	, 128);
		
		gun.setText("<html>Gatling Gun"
				+ "<br>Damage      : " + TowerGun.getInitattack()
				+ "<br>Radius      : 12 tiles, surround"
				+ "<br>Cost        : " + TowerGun.getInitcost()
				+ "</html>");
		gun.setBounds(150, PANELHEIGHT/2-57, 140	, 128);
		
		towerMissile.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Main.getGold() - TowerMissile.getInitcost() >= 0) {
					GamePanel.setTowerHover(0);				
				}
			}
		});
		
		towerGun.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Main.getGold() - TowerGun.getInitcost() >= 0) {
					GamePanel.setTowerHover(2);				
				}			
			}
		});
		
		start.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(GamePanel.getWaveStatus() == 0)
					GamePanel.setWaveStatus(1);
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		add(gun);
		add(missile);
		add(towerMissile);
		add(towerGun);
		add(wave);
		add(start);
		add(exit);
		setVisible(true);
		repaint();
		
		th.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintBorders(g);
		g.setColor(Color.CYAN);
		g.drawRect(20,85, 285, 75);
		g.fillRect(20, 85, 285, 75);
		paintGoldPanel(g);
	}
	public void paintGoldPanel(Graphics g) {
		int start = 105;
		int end = 90;
		Image imge = Tile.getIcon().get(10).getImage();
		Integer gold = Main.getGold();
		int[] goldS = new int[6];
		int div = 100000;
		for(int i=0; i<5; i++) {
			goldS[i] = gold / div % 10 ;
			div /= 10;
		}
		
		goldS[5] = gold % 10;
		
		g.drawImage(imge, 10, 90, 64, 64, null);
		for(int i=0; i< 6; i++){
			if(gold > 999999) {
				Image img = Tile.getIcon().get(9).getImage();
				g.drawImage(img, start+2,end ,64, 64, null);
				start += 30;
			}else if(gold == 0) {
				Image img = Tile.getIcon().get(0).getImage();
				g.drawImage(img, start+2,end ,64, 64, null);
				start +=30;								
			}
			else {
				Image img = Tile.getIcon().get(goldS[i]).getImage();
				g.drawImage(img, start+2,end ,64, 64, null);
				start += 30;								
			}
			
		}
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
