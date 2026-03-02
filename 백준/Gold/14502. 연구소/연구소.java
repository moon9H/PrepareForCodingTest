import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int max;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[][] map;
	
	static class Point {
		int r,c;
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		max = 0;
		
		List<Point> safeSpace = new ArrayList<>();
		List<Point> virusSpace = new ArrayList<>();
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
				
				// 안전 영역 추가
				if(map[i][j] == 0) {
					safeSpace.add(new Point(i,j));
				}
				if(map[i][j] == 2) {
					virusSpace.add(new Point(i,j));
				}
			}
		}
		
		// 임의의 3칸에 벽(1)을 세움 -> 바이러스 최종 전염
		// -> 남은 안전영역(0) 크기?
		
		// 벽을 세우는 재귀 -> 벽 위치 3개 결정되면 bfs(조합) 시작 
		
		selectWall(0, 0, safeSpace, virusSpace);

		System.out.println(max);
	}
	
	private static void selectWall(int cnt, int start, List<Point> safeSpace, List<Point> virusSpace) {
		// 벽 3개 다 세움
		if(cnt == 3) {
			bfs(virusSpace);  // 바이러스 퍼트림
			return;
		}
		
		// 이미 벽인 공간 제외하고 벽을 세움
		for(int i=start; i<safeSpace.size(); i++) {
			Point p = safeSpace.get(i);
			map[p.r][p.c] = 1;  // 꺼낸 좌표에 벽 설치
			selectWall(cnt+1, i+1, safeSpace, virusSpace);
			map[p.r][p.c] = 0;  // 벽 다시 철거하고 벽 설치 다시 조사
		}
	}
	
	private static void bfs(List<Point> virusSpace) {
		int[][] copyMap = new int[N][M];
//		copyMap = map.clone();  // 이러면 map의 각 행의 주소만 복사가 됨
		
		// 주소값까지 깊은 복사를 하려면
		for(int i=0; i<N; i++) {
			copyMap[i] = map[i].clone();
		}
		
		Queue<Point> q = new ArrayDeque<>(virusSpace);
		
		while(!q.isEmpty()) {
			Point curr = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				
				// 바이러스 확산
				if(nr>=0 && nr<N && nc>=0 && nc<M && copyMap[nr][nc] == 0) {
					copyMap[nr][nc] = 2;
					q.add(new Point(nr,nc));
				}
			}
		}
		
		// 확산 완료 후, 안전 구역개수 카운트
		isSafe(copyMap);
	}
	
	private static void isSafe(int[][] copyMap) {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copyMap[i][j] == 0) cnt++;
			}
		}
		
		if(cnt > max) {
			max = cnt;
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(copyMap[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
	}
}
