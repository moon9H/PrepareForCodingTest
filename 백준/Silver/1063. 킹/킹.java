import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int kingR, kingC, stoneR, stoneC;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		String king = st.nextToken();
		String stone = st.nextToken();
		int N = Integer.parseInt(st.nextToken());
		
		kingR = 8 - (king.charAt(1) - '0');
		kingC = king.charAt(0) - 'A';
		
		stoneR = 8 - (stone.charAt(1) - '0');
		stoneC = stone.charAt(0) - 'A';
		
		for (int i = 0; i < N; i++) {
			String cmd = br.readLine();
			move(cmd);
		}
		
		sb.append((char)(kingC + 'A')).append(8 - kingR).append('\n');
		sb.append((char)(stoneC + 'A')).append(8 - stoneR);
		
		System.out.print(sb);
	}
	
	static void move(String cmd) {
		int nr = kingR;
		int nc = kingC;
		
		if (cmd.equals("R")) nc++;
		else if (cmd.equals("L")) nc--;
		else if (cmd.equals("B")) nr++;
		else if (cmd.equals("T")) nr--;
		else if (cmd.equals("RT")) {
			nr--;
			nc++;
		}
		else if (cmd.equals("LT")) {
			nr--;
			nc--;
		}
		else if (cmd.equals("RB")) {
			nr++;
			nc++;
		}
		else if (cmd.equals("LB")) {
			nr++;
			nc--;
		}
		
		if (!isInRange(nr, nc)) return;
		
		// 킹이 돌이 있는 칸으로 이동하려는 경우
		if (nr == stoneR && nc == stoneC) {
			int sr = stoneR + (nr - kingR);
			int sc = stoneC + (nc - kingC);
			
			if (!isInRange(sr, sc)) return;
			
			stoneR = sr;
			stoneC = sc;
		}
		
		kingR = nr;
		kingC = nc;
	}
	
	static boolean isInRange(int r, int c) {
		return r >= 0 && r < 8 && c >= 0 && c < 8;
	}
}