package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import mainControl.Main;
import objects.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private final int PANELWIDTH = GameFrame.GAMEWIDTH + GameFrame.BORDERSIZE * 2;
	private final int PANELHEIGHT = GameFrame.GAMEHEIGHT + GameFrame.BORDERSIZE * 2;

	private ImageIcon img;
	private Dimension maxSize = new Dimension(GameFrame.GAMEWIDTH + GameFrame.BORDERSIZE * 2,
			GameFrame.GAMEHEIGHT + GameFrame.BORDERSIZE * 2);

	private Point hover = null;
	private static int towerHover = -1;
	private int towerDir = 0; // 0 = UP, 1 = RIGHT, 2 = DOWN, 3 = LEFT
	private Vector<Point> towerRanges = new Vector<Point>();

	private final Rectangle GAMEAREA = new Rectangle(GameFrame.BORDERSIZE, GameFrame.BORDERSIZE, GameFrame.GAMEWIDTH,
			GameFrame.GAMEHEIGHT);
	GameMap gMap = Main.getMap();
	private int[][] mapCode;
	private int[][] pathCode;
	
	private static int waveStatus = 0;
	private Vector<MomonParent> momons = new Vector<MomonParent>();

	private static boolean lose;
	
	private MouseMotionListener mouseMotionLister = new MouseMotionListener() {

		@Override
		public void mouseMoved(MouseEvent e) {
			Point p = e.getPoint();
			if (GAMEAREA.contains(p)) {
				p.x = (p.x - GameFrame.BORDERSIZE) / GameFrame.TILESIZE;
				p.y = (p.y - GameFrame.BORDERSIZE) / GameFrame.TILESIZE;
				hover = (Point) p.clone();;
			} else
				hover = null;
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {

		}
	};

	private MouseWheelListener mouseWheelListener = new MouseWheelListener() {

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (hover != null && towerHover != -1) {
				towerDir++;
				towerDir %= 4;
			}
			repaint();

		}
	};

	private MouseListener mouseListener = new MouseListener() {
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Point p = e.getPoint();
			if (GAMEAREA.contains(p)) {
				p.x = (p.x - GameFrame.BORDERSIZE) / GameFrame.TILESIZE;
				p.y = (p.y - GameFrame.BORDERSIZE) / GameFrame.TILESIZE;

				if (mapCode[p.y][p.x] == 0) {
					if (towerHover == 0) {
						Main.addTower(new TowerMissile(towerDir, p, towerRanges));
						mapCode[p.y][p.x] = 31;	
						Main.reduceGold(TowerMissile.getInitcost());
					} else if (towerHover == 2) {
						Main.addTower(new TowerGun(towerDir, p, towerRanges));
						mapCode[p.y][p.x] = 33;
						Main.reduceGold(TowerGun.getInitcost());
					}
				}
				towerHover = -1;
			}

			repaint();
		}
	};

	private Thread th = new Thread(new Runnable() {

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			int counter = 0;
			while (true) {
				Vector<Projectile> toBeRemoved = new Vector<Projectile>();
				for (Projectile p : Main.getProjectiles()) {
					if(p instanceof ProjectileMissile) {
						boolean isOutOfRange;
						if (counter % p.getSpeed() == 0) {
							((ProjectileMissile) p).move();
							isOutOfRange = ((ProjectileMissile) p).isOutOfRange();
							if (isOutOfRange)
								toBeRemoved.add(p);
							
							for(MomonParent m : Main.getMomons()) {
								if(m.getHitBox().intersects(((ProjectileMissile) p).getHitBox())) {
									toBeRemoved.add(p);
									m.reduceHP(p.getAttack());
									if(m.isDead()) {
										Main.addGold(m.getGold());
										Main.removeMomon(m);
									}
									break;
								}
							}
							
						}
					}else if(p instanceof ProjectileGun && counter % 150 == 0) {
						toBeRemoved.add(p);
					}
				}
				Main.getProjectiles().removeAll(toBeRemoved);
				
				for (TowerAbstract t : Main.getTowers()) {
					for (MomonParent m : Main.getMomons()) {										
						Point temp = new Point(GamePanel.getSimplifiedCoor(m.getCoor().x),
								GamePanel.getSimplifiedCoor(m.getCoor().y));
						
						if(t.getRanges().contains(temp)) {
							if(counter % t.getSpeed() != 0) break;							
							if (t instanceof TowerGun) {
								int tempDir = t.getRanges().indexOf(temp);
								((TowerGun) t).setGunDir(tempDir);
								m.reduceHP(t.getAttack());
								if(m.isDead()) {
									Main.addGold(m.getGold());
									Main.removeMomon(m);									
								}
							}							
							t.fire();
							break;
						}
					}								
				}
				
				for (MomonParent m : Main.getMomons()) {
					if (counter % m.getSpeed() == 0) {
						m.setDir(pathCode);
						m.move();
						if(m.getCoor().x >= 1008) {
							lose = true;
						}
					}
				}
				
				if(waveStatus == 1) {
					if(momons.isEmpty() && Main.getMomons().isEmpty()) {
						if(gMap.getCurWave() != 4) {
							gMap.nextWave();						
							momons = (Vector<MomonParent>) gMap.getWaves().get(gMap.getCurWave()).getMomons().clone();
						}
					}else {
						if(momons.isEmpty()) {
							waveStatus = 0;							
						}else if(counter % 5000 == 0) {
							MomonParent tempMomon = momons.get(0);
							Main.addMomon(tempMomon);
							momons.remove(tempMomon);
							
						}
					}
				}
				
				repaint();
				counter += 25;
				counter %= 3000;
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	});

	public GamePanel() {
		mapCode = new int[GameFrame.TILECOUNTy][GameFrame.TILECOUNTx];		
		mapCode = gMap.getMapCode();
		gMap.initWaves();
		pathCode = gMap.getPathCode();

		setPreferredSize(maxSize);
//		setBackground(Color.BLACK);
		addMouseMotionListener(mouseMotionLister);
		addMouseWheelListener(mouseWheelListener);
		addMouseListener(mouseListener);
		repaint();

		th.start();

	}

	public static int getTileCoor(int a) {
		return a * GameFrame.TILESIZE + GameFrame.BORDERSIZE;
	}
	
	public static int getSimplifiedCoor(int a) {
		return (a - GameFrame.BORDERSIZE) / GameFrame.TILESIZE;
	}

	public static int checkOutOfBound(int a) {
		return a < 0 ? 0 : a;
	}
	
	public static void setTowerHover(int temp) {
		towerHover = temp;
	}
	
	public static int getWaveStatus() {
		return waveStatus;
	} 
	
	public static void setWaveStatus(int waveStatus) {
		GamePanel.waveStatus = waveStatus;
	}
	
	public static boolean getLose() {
		return lose;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, GameFrame.GAMEWIDTH, GameFrame.GAMEHEIGHT);
		paintMap(g);
		paintBorders(g);
		paintTowers(g);
		paintProjectiles(g);
		paintMomons(g);
		paintHover(g);
	}

	public void paintBorders(Graphics g) {

		// Horizontal
		img = Tile.getBorders().get(2);
		for (int i = 1; i <= GameFrame.TILECOUNTx * 3; i++) {
			int srcx = i * GameFrame.BORDERSIZE;

			g.drawImage(img.getImage(), srcx, 0, srcx + GameFrame.BORDERSIZE, GameFrame.BORDERSIZE, 24, 24, 40, 40,
					null);
			g.drawImage(img.getImage(), srcx, PANELHEIGHT - GameFrame.BORDERSIZE, srcx + GameFrame.BORDERSIZE,
					PANELHEIGHT, 24, 24, 40, 40, null);
			g.drawImage(img.getImage(), srcx, PANELHEIGHT, srcx + GameFrame.BORDERSIZE, PANELHEIGHT + 1, 0, 0, 16, 1,
					null);
		}

		// Vertical
		img = Tile.getBorders().get(1);
		for (int i = 1; i <= GameFrame.TILECOUNTy * 3; i++) {
			int srcy = i * GameFrame.BORDERSIZE;

			g.drawImage(img.getImage(), 0, srcy, GameFrame.BORDERSIZE, srcy + GameFrame.BORDERSIZE, 24, 24, 40, 40,
					null);
			g.drawImage(img.getImage(), PANELWIDTH - GameFrame.BORDERSIZE, srcy, PANELWIDTH,
					srcy + GameFrame.BORDERSIZE, 24, 24, 40, 40, null);
		}

		// Corners
		img = Tile.getBorders().get(0);
		g.drawImage(img.getImage(), 0, 0, GameFrame.BORDERSIZE, GameFrame.BORDERSIZE, 24, 24, 40, 40, null);
		g.drawImage(img.getImage(), PANELWIDTH - GameFrame.BORDERSIZE, 0, PANELWIDTH, GameFrame.BORDERSIZE, 24, 24, 40,
				40, null);
		g.drawImage(img.getImage(), 0, PANELHEIGHT - GameFrame.BORDERSIZE, GameFrame.BORDERSIZE, PANELHEIGHT + 1, 24,
				24, 41, 41, null);
		g.drawImage(img.getImage(), PANELWIDTH - GameFrame.BORDERSIZE, PANELHEIGHT - GameFrame.BORDERSIZE, PANELWIDTH,
				PANELHEIGHT + 1, 24, 24, 41, 41, null);

	}

	public void paintMap(Graphics g) {

		for (int i = 0; i < GameFrame.TILECOUNTy; i++) {
			for (int j = 0; j < GameFrame.TILECOUNTx; j++) {
				if (mapCode[i][j] < 20) {
					img = Tile.getTiles().get(mapCode[i][j]);
				} else if (mapCode[i][j] < 30) {
					img = Tile.getDeco().get(mapCode[i][j] - 21);
				} else if (mapCode[i][j] < 40) {
					img = Tile.getTiles().get(0);
				}

				int srcx = getTileCoor(j);
				int srcy = getTileCoor(i);

				g.drawImage(img.getImage(), srcx, srcy, getTileCoor(j + 1), getTileCoor(i + 1), 0, 0, 64, 64, null);
//				g.drawLine(srcx, srcy, srcx + GameFrame.TILESIZE, srcy);
//				g.drawLine(srcx, srcy, srcx, srcy + GameFrame.TILESIZE);
			}
		}

	}

	public void paintHover(Graphics g) {
		if (hover != null && towerHover != -1) {
			Graphics2D g2d = (Graphics2D) g;

			img = Tile.getTower_h().get(towerHover);
			int srcx = getTileCoor(hover.x);
			int srcy = getTileCoor(hover.y);

			if (mapCode[hover.y][hover.x] != 0)
				img = Tile.getTower_h().get(towerHover + 8);

			AffineTransform old = g2d.getTransform();
			AffineTransform a = AffineTransform.getRotateInstance(Math.toRadians(towerDir * 90),
					srcx + GameFrame.TILESIZE / 2, srcy + GameFrame.TILESIZE / 2);
			g2d.setTransform(a);
			g2d.drawImage(img.getImage(), srcx, srcy, getTileCoor(hover.x + 1), getTileCoor(hover.y + 1), 0, 0, 64, 64,
					null);

			g2d.setTransform(old);
			paintTowerRange(g);

		}
	}

	private void paintTowerRange(Graphics g) {
		towerRanges = new Vector<Point>();
		if (towerHover == 0) {
			for (int i = 1; i <= 5; i++) {
				int idxX = hover.x;
				int idxY = hover.y;

				switch (towerDir) {
				case 0:
					idxY -= i;
					break;
				case 1:
					idxX += i;
					break;
				case 2:
					idxY += i;
					break;
				case 3:
					idxX -= i;
					break;
				}

				img = null;
				if (idxX < 0 || idxX >= 21 || idxY < 0 || idxY >= 14)
					continue;

				if (mapCode[idxY][idxX] < 20) {
					img = Tile.getTiles_red().get(mapCode[idxY][idxX]);
				} else if (mapCode[idxY][idxX] < 30) {
					img = Tile.getDeco_red().get(mapCode[idxY][idxX] - 21);
				} else if (mapCode[idxY][idxX] < 40) {
					img = Tile.getTower_h().get(mapCode[idxY][idxX] - 31 + 4);
				}

				g.drawImage(img.getImage(), getTileCoor(idxX), getTileCoor(idxY), getTileCoor(idxX + 1),
						getTileCoor(idxY + 1), 0, 0, 64, 64, null);

				towerRanges.add(new Point(idxX, idxY));
			}

		} else if (towerHover == 2) {
			int idxX = hover.x;
			int idxY = hover.y;

			for (int i = 0; i <= 3; i++) {
				for (int j = -1; j <= 1; j++) {
					switch (i) {
					case 0:
						idxX = hover.x + j;
						idxY = hover.y - 2;
						break;
					case 1:
						idxX = hover.x + 2;
						idxY = hover.y + j;
						break;
					case 2:
						idxX = hover.x - j;
						idxY = hover.y + 2;
						break;
					case 3:
						idxX = hover.x - 2;
						idxY = hover.y - j;
						break;
					}

					img = null;
					if (idxX < 0 || idxX >= 21 || idxY < 0 || idxY >= 14)
						continue;
					if (mapCode[idxY][idxX] < 20) {
						img = Tile.getTiles_red().get(mapCode[idxY][idxX]);
					} else if (mapCode[idxY][idxX] < 30) {
						img = Tile.getDeco_red().get(mapCode[idxY][idxX] - 21);
					} else if (mapCode[idxY][idxX] < 40) {
						img = Tile.getTower_h().get(mapCode[idxY][idxX] - 31 + 4);
					}
					g.drawImage(img.getImage(), getTileCoor(idxX), getTileCoor(idxY), getTileCoor(idxX + 1),
							getTileCoor(idxY + 1), 0, 0, 64, 64, null);

					towerRanges.add(new Point(idxX, idxY));
				}

			}
		}
	}

	private void paintTowers(Graphics g) {
		for (TowerAbstract t : Main.getTowers()) {
			int srcX = getTileCoor(t.getCoor().x);
			int srcY = getTileCoor(t.getCoor().y);

			Graphics2D g2d = (Graphics2D) g;

			AffineTransform old = g2d.getTransform();
			AffineTransform a = AffineTransform.getRotateInstance(Math.toRadians(t.getDir() * 90),
					srcX + GameFrame.TILESIZE / 2, srcY + GameFrame.TILESIZE / 2);
			g2d.setTransform(a);
			g2d.drawImage(t.getImg().getImage(), srcX, srcY, srcX + GameFrame.TILESIZE, srcY + GameFrame.TILESIZE, 0, 0,
					64, 64, null);

			if (t instanceof TowerGun) {
				int gunDir = ((TowerGun) t).getGunDir();

				AffineTransform b = AffineTransform.getRotateInstance(Math.toRadians((gunDir - 1) * 30),
						srcX + GameFrame.TILESIZE / 2, srcY + GameFrame.TILESIZE / 2);
				g2d.setTransform(b);
				g2d.drawImage(Tile.getTower().get(5).getImage(), srcX, srcY, srcX + GameFrame.TILESIZE,
						srcY + GameFrame.TILESIZE, 0, 0, 64, 64, null);
			}

			g2d.setTransform(old);

		}
	}

	private void paintProjectiles(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform old = g2d.getTransform();
		AffineTransform a = null;
		
		int srcX = 0, srcY = 0, rotX = 0, rotY = 0;

		for (Projectile p : Main.getProjectiles()) {

			if (p instanceof ProjectileMissile) {
				srcX = p.getCoor().x;
				srcY = p.getCoor().y;

				rotX = srcX;
				switch (p.getDir()) {
				case 1:
					rotX += 8;
				case 2:
					rotX += 8;
				case 3:
					rotX += 8;
				case 0:
					rotX += 8;
				}

				a = AffineTransform.getRotateInstance(
								Math.toRadians(p.getDir() * 90), 
								rotX, srcY + 32);
			} else if (p instanceof ProjectileGun) {
				srcX = p.getCoor().x;
				srcY = p.getCoor().y;
				
				rotX = srcX + GameFrame.TILESIZE/2;
				rotY = srcY + GameFrame.TILESIZE/2 + 32;
				a = AffineTransform.getRotateInstance(
						Math.toRadians((((ProjectileGun) p).getGunDir()-1) * 30), 
						rotX, rotY);
			}

			g2d.setTransform(a);

			g2d.drawImage(p.getImg().getImage(), srcX, srcY, srcX + GameFrame.TILESIZE, srcY + GameFrame.TILESIZE, 0, 0,
					64, 64, null);

			g2d.setTransform(old);
			
//			if(p instanceof ProjectileMissile)
//				g.drawRect(((ProjectileMissile)p).getHitBox().x, ((ProjectileMissile)p).getHitBox().y, 
//					((ProjectileMissile)p).getHitBox().width, ((ProjectileMissile)p).getHitBox().height);

		}
	}
	
	private void paintMomons(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform old = g2d.getTransform();
		AffineTransform a = null;

		for (MomonParent m : Main.getMomons()) {
			int srcX = m.getCoor().x; 
			int srcY = m.getCoor().y;
				a = AffineTransform.getRotateInstance(
								Math.toRadians((m.getDir()-1) * 90), 
								srcX + 24, srcY + 24);

			g2d.setTransform(a);

			g2d.drawImage(m.getImg().getImage(), srcX, srcY, srcX + GameFrame.TILESIZE, srcY + GameFrame.TILESIZE, 0, 0,
					64, 64, null);

			g2d.setTransform(old);
//			g.drawRect(m.getHitBox().x, m.getHitBox().y, m.getHitBox().width, m.getHitBox().height);
		}
	}
}
