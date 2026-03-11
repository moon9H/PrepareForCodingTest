import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int xDiff = Math.abs(x1 - x2);
			int yDiff = Math.abs(y1 - y2);
			
			int minMove = 0;
			while(xDiff > 0 && yDiff > 0) {
				--xDiff;
				--yDiff;
				minMove += 2;
			}
			
			if ( (xDiff + yDiff) % 2 == 0) minMove += 2 * (xDiff + yDiff);
			else {
				minMove += 2 * (xDiff + yDiff) - 1;
			}
			
			sb.append('#').append(tc).append(' ').append(minMove).append('\n');
		}
		System.out.println(sb);
	}
}