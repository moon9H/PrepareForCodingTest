import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int R, C, Jrow, Jcol, cnt;
	public static char[][] map;
	public static int[] Jmove;
	public static List<int[]> list;
	public static int[] dx = {1, 1, 1, 0, 0, 0, -1, -1, -1};
	public static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		list = new ArrayList<>();
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 'I') {
					Jrow = i;
					Jcol = j;
				} 
				if(map[i][j] == 'R') {
					list.add(new int[] {i, j});
				}
			}
		}
		char[] tmp = br.readLine().toCharArray();
		Jmove = new int[tmp.length];
		for(int i = 0; i < tmp.length; i++) {
			Jmove[i] = (tmp[i]-'0') - 1;
		}
		if(move()) {
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					System.out.print(map[i][j]);					
				}
				System.out.println();
			}
		} else {
			System.out.println("kraj " + cnt);
		}
		
	}
	
	// 종수와 아두이노의 거리 계산
	private static int getDistance(int Ar, int Ac) {
		return Math.abs(Jrow - Ar) + Math.abs(Jcol - Ac);
	}
	
	// 종수 이동
	public static boolean move() {
		for(int i = 0; i < Jmove.length; i++) {
			int nr = Jrow+dx[Jmove[i]];
			int nc = Jcol+dy[Jmove[i]];
			cnt++;
			for(int[] r : list) {
			    if(r[0] == nr && r[1] == nc) return false;
			}
			Jrow = nr;
			Jcol = nc;
			List<int[]> dupli = new ArrayList<>();
			int[][] Copymap = new int[R][C];
			
			// 아두이노 이동
			for(int k = 0; k < list.size(); k++) {
				int min = Integer.MAX_VALUE;
				int curR = list.get(k)[0];
				int curC = list.get(k)[1];
				int minR = 0;
				int minC = 0;
				for(int j = 0; j < 9; j++) {
					int Ar = curR + dx[j];
					int Ac = curC + dy[j];
					if(Ar < 0 || Ar >= R || Ac < 0 || Ac >= C) continue;
					// 최소방향 탐색
					int tmp = getDistance(Ar, Ac);
					// 최소방향이면 minR, minC 갱신
					if(min > tmp) {
						min = tmp;
						minR = Ar;
						minC = Ac;
					}
				}
				// 최소거리에 copymap +1 추가
				Copymap[minR][minC]++;
			}
			for(int j = 0; j < R; j++) {
				for(int k = 0; k < C; k++) {
					if(Copymap[j][k] >= 1) {
						if(j == Jrow && k == Jcol) return false;
					}
					if(Copymap[j][k] == 1) {
						dupli.add(new int[] {j, k});
					}
				}
			}
			list = dupli;
			
			// 다시 초기화
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++){
					map[r][c] = '.';
				}
			}
			map[nr][nc] = 'I';
			for(int[] r : list) {
				map[r[0]][r[1]] = 'R';
			}
		}
		return true;
	}
}
