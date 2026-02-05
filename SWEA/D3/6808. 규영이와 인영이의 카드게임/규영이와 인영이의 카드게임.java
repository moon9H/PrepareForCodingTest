import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int winCnt, loseCnt;
	static int[] inyoungCardsList;
	static int[] gyuyoungCards;
	static int[] numbers;
	private static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			winCnt = 0;
			loseCnt = 0;
			gyuyoungCards = new int[9];
			isSelected = new boolean[9];
			numbers = new int[9];
			for (int i = 0; i < 9; i++) {
				gyuyoungCards[i] = Integer.parseInt(st.nextToken());
			}
			inyoungCardsList = new int[9];
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
			permutation(0);
			sb.append("#").append(testCase).append(" ").
			append(loseCnt).append(" ").append(winCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void permutation(int cnt) {
		if (cnt == 9) {
			playGame(numbers, gyuyoungCards);
			return;
		}
		
		for (int i = 0; i < 9; i++) {			
			if (isSelected[i]) continue;
			
			isSelected[i] = true;
			numbers[cnt] = inyoungCardsList[i];
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}
	
	public static void playGame(int[] gyuyoung, int[] inyoung) {
		int inyoungScore = 0, gyuyoungScore = 0;
		for (int i = 0; i < 9; i++) {
			if (gyuyoung[i] > inyoung[i]) {
				gyuyoungScore += gyuyoung[i] + inyoung[i];
			} else if (gyuyoung[i] < inyoung[i]) {
				inyoungScore += gyuyoung[i] + inyoung[i];
			}
		}
		if (gyuyoungScore > inyoungScore) winCnt++;
		else if (gyuyoungScore < inyoungScore) loseCnt++;
	}
}