package objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import view.GameFrame;

public class GameMap {

	private final int WAVECOUNT = 5;
	private final int MAXWAVEMOMON = 20;
	
	private int[][] mapCode;
	private int[][] pathCode;
	private Vector<Wave> waves = new Vector<Wave>();
	private int[][] waveCode = new int[WAVECOUNT][MAXWAVEMOMON];
	private int curWave = -1;

	public GameMap(String fileName) {
		
		for(int i=0; i<WAVECOUNT; i++)
			for(int j=0; j<MAXWAVEMOMON; j++)
				waveCode[i][j] = 9;
		
		mapCode = new int[GameFrame.TILECOUNTy][GameFrame.TILECOUNTx];
		pathCode = new int[GameFrame.TILECOUNTy][GameFrame.TILECOUNTx];
		readMapAndWave(fileName);
		
	}

	
	public void readMapAndWave(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			String separator = " ";

			for(int i = 0; i < GameFrame.TILECOUNTy; i++) {
				String line = br.readLine();
				if(line != null) {
					String[] token = line.split(separator);
					for(int j = 0; j < GameFrame.TILECOUNTx; j++) {
						mapCode[i][j] = Integer.parseInt(token[j]);
					}
				}
			}
	
			for(int i = 0; i < 5; i++) {				
				String line = br.readLine();
				if(line != null) {
					String[] token = line.split(separator);					
					for(int j = 0; j < token.length; j++) {
						waveCode[i][j] = Integer.parseInt(token[j]);
					}
					
				}
			}
			for(int i = 0; i < GameFrame.TILECOUNTy; i++) {
				String line = br.readLine();
				if(line != null) {
					String[] token = line.split(separator);
					for(int j = 0; j < GameFrame.TILECOUNTx; j++) {
						pathCode[i][j] = Integer.parseInt(token[j]);
					}
				}
			}
			
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initWaves() {
		for(int i=0; i<WAVECOUNT; i++) {
			Wave temp = new Wave();
			for(int j=0; j<MAXWAVEMOMON; j++) {
				switch(waveCode[i][j]) {
				case 9:
					continue;
				case 0:
					temp.addMomon(new MomonSoldier0());
					break;
				case 1:
					temp.addMomon(new MomonSoldier1());
					break;
				case 2:
					temp.addMomon(new MomonSoldier2());
					break;
				case 3:
					temp.addMomon(new MomonTank());
					break;
				}
			}
			waves.add(temp);
		}
	}
	
	public int[][] getMapCode() {
		return mapCode;
	}
	
	public int[][] getPathCode() {
		return pathCode;
	}
	
	public void nextWave() {
		curWave++;
	}
	
	public int getCurWave() {
		return curWave;
	}
	
	public Vector<Wave> getWaves() {
		return waves;
	}

}