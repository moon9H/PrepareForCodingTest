import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int winCnt, loseCnt;
	static int[] inyoungCardsList;
	static int[] gyuyoungCards;
	private static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			winCnt = loseCnt = 0;
			
			gyuyoungCards = new int[9];
			inyoungCardsList = new int[9];
			
			isSelected = new boolean[9];
			
			for (int i = 0; i < 9; i++) {
				gyuyoungCards[i] = Integer.parseInt(st.nextToken());
			}
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				boolean inGyu = false;
				for (int j = 0; j < 9; j++) {
					if (gyuyoungCards[j] == i) {
						inGyu = true;
						break;
					}
				}
				if (!inGyu)
					inyoungCardsList[idx++] = i;
			}
			
			permutation(0, 0, 0);
			sb.append("#").append(testCase).append(" ").
			append(winCnt).append(" ").append(loseCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void permutation(int cnt, int gyuScore, int inScore) {
		if (cnt == 9) {
			if (gyuScore > inScore) winCnt++;
			else loseCnt++;
			return;
		}
		
		for (int i = 0; i < 9; i++) {			
			if (isSelected[i]) continue;
			
			isSelected[i] = true;
			int roundScore =  inyoungCardsList[i] + gyuyoungCards[cnt];
			if (gyuyoungCards[cnt] > inyoungCardsList[i])
				permutation(cnt + 1, gyuScore + roundScore, inScore);
			else
				permutation(cnt + 1, gyuScore, inScore + roundScore);
			
			isSelected[i] = false;
		}
	}
}