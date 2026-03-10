import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M, K;
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	static class cluster{
		int row, col, num, dir;
		boolean alive;
		public cluster(int row, int col, int num, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.num = num;
			this.dir = dir;
			this.alive = true;
		}		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ArrayList<cluster> cList = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				cList.add(new cluster(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1));
			}
			
			while(M > 0) {	
				// 1. 미생물 위치 업데이트
				for (cluster c : cList) {
					if (!c.alive) continue;
					int nr = c.row + dr[c.dir];
					int nc = c.col + dc[c.dir];
					if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
						c.num /= 2;
						if (c.num == 0) {
							c.alive = false;
							continue;
						}
						switch(c.dir) {
						case 0 :
							c.dir = 1;
							break;
						case 1 :
							c.dir = 0;
							break;
						case 2 :
							c.dir = 3;
							break;
						case 3 :
							c.dir = 2;
							break;
						}
					}
					
					c.row = nr;
					c.col = nc;
				}
				
				// 2. 미생물 충돌 처리
				for (int i = 0; i < cList.size() - 1; i++) {
					cluster standard = cList.get(i);
					if (!standard.alive) continue;
					
					int sum = standard.num;
					int maxNum = standard.num;
					int dir = standard.dir;
					boolean collision = false;
					
					for (int j = i + 1; j < cList.size(); j++) {
						cluster target = cList.get(j);
						if (!target.alive) continue;
						
						if (standard.row == target.row && standard.col == target.col) {
							collision = true;
							sum += target.num;
							if (target.num > maxNum) {
								maxNum = target.num;
								dir = target.dir;
							}
							target.alive = false;
						}
					}
					
					if (collision) {
						standard.num = sum;
						standard.dir = dir;
					}
				}
				--M;
			}
			int ans = 0;
			for (cluster c : cList) {
				if (c.alive) ans += c.num;
			}
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}