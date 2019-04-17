package objects;

import java.io.BufferedReader;
import java.io.FileReader;

import view.GameFrame;

public class GameMap {

	private int[][] mapCode;


	public GameMap(String fileName) {

		mapCode = new int[GameFrame.TILECOUNTy][GameFrame.TILECOUNTx];

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
			
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int[][] getMapCode() {
		return mapCode;
	}

}