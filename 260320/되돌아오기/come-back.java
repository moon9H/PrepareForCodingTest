import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dx = {-1, 0, 0, 1};
	private static final int[] dy = {0, -1, 1, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Map<Character, Integer> getDir = new HashMap<>();
		getDir.put('W', 0);
		getDir.put('S', 1);
		getDir.put('N', 2);
		getDir.put('E', 3);
		int N = Integer.parseInt(br.readLine());
		int t = 0;
		int cY = 0, cX = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char dir = st.nextToken().charAt(0);
			int distance = Integer.parseInt(st.nextToken());
			int d = getDir.get(dir);
			
			for (int time = 0; time < distance; time++) {
				cX += dx[d];
				cY += dy[d];
				
				if (cX == 0 && cY == 0) {
					System.out.println(t + 1);
					return;
				}
				t++;
			}
		}
		System.out.println(-1);
	}
}