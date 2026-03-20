import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {-1, 0, 1, 0};
	private static final int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Character, Integer> getDir = new HashMap<>();
		getDir.put('U', 0);
		getDir.put('L', 1);
		getDir.put('D', 2);
		getDir.put('R', 3);
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int cRow = Integer.parseInt(st.nextToken()) - 1;
		int cCol = Integer.parseInt(st.nextToken()) - 1;
		char cDir = st.nextToken().charAt(0);
		int d = getDir.get(cDir);
		for (int i = 0; i < T; i++) {
			int nr = cRow + dr[d];
			int nc = cCol + dc[d];
			
			if (nr < 0 || nc >= N || nc < 0 || nc >= N) {
				d = (d + 2) % 4;
				continue;
			}
			
			cRow = nr;
			cCol = nc;
		}
		
		System.out.println((cRow + 1) + " " + (cCol + 1));
	}
}