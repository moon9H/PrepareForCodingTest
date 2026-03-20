import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {0, -1, 1, 0};
	private static final int[] dc = {-1, 0, 0, 1};
	private static final Map<Character, Integer> dir = new HashMap<>();
	public static void main(String[] args) throws IOException{
		dir.put('W', 0);
		dir.put('S', 1);
		dir.put('N', 2);
		dir.put('E', 3);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int cR = 0, cC = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char direction = st.nextToken().charAt(0);
			int distance = Integer.parseInt(st.nextToken());
			int d = dir.get(direction);
			
			cR += dr[d] * distance;
			cC += dc[d] * distance;
		}
		System.out.println(cC + " " + cR);
	}
}