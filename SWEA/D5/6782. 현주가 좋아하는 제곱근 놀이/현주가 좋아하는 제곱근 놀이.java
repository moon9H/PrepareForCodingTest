import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			long N = Long.parseLong(br.readLine());
			long cnt = 0;
			while (N != 2) {
				double sqN = Math.sqrt(N);
				if (isLong(sqN)) {
					N = (long) sqN;
					cnt++;
				}
				else {
					cnt += ((long)sqN + 1) * ((long)sqN + 1) - N;
					N = ((long)sqN + 1) * ((long)sqN + 1);
				}
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
	
	public static boolean isLong(double num) {
		return num == (long) num;
	}
}