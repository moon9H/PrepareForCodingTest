import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	private static int N, M, islandCount;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[][] bridge;
	private static int[] parent;
	private static ArrayList<int[]> edges;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		islandCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j, ++islandCount);
				}
			}
		}
		
		bridge = new int[islandCount + 1][islandCount + 1];
		for (int i = 1; i <= islandCount; i++) {
			Arrays.fill(bridge[i], Integer.MAX_VALUE);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					findBridge(i, j);
				}
			}
		}
		
		edges = new ArrayList<>();
		for (int i = 1; i <= islandCount; i++) {
			for (int j = i + 1; j <= islandCount; j++) {
				if (bridge[i][j] != Integer.MAX_VALUE) {
					edges.add(new int[] {i, j, bridge[i][j]});
				}
			}
		}
		
		Collections.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		parent = new int[islandCount + 1];
		for (int i = 1; i <= islandCount; i++) {
			parent[i] = i;
		}
		
		int totalLength = 0;
		int edgeCount = 0;
		
		for (int[] edge : edges) {
			int a = edge[0];
			int b = edge[1];
			int cost = edge[2];
			
			if (find(a) != find(b)) {
				union(a, b);
				totalLength += cost;
				edgeCount++;
			}
		}
		
		if (edgeCount == islandCount - 1) {
			System.out.println(totalLength);
		} else {
			System.out.println(-1);
		}
	}
	
	static void bfs(int sr, int sc, int num) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		map[sr][sc] = num;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (visited[nr][nc] || map[nr][nc] == 0) continue;
				
				visited[nr][nc] = true;
				map[nr][nc] = num;
				queue.add(new int[] {nr, nc});
			}
		}
	}
	
	static void findBridge(int r, int c) {
		int startIsland = map[r][c];
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			int length = 0;
			
			while (true) {
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) break;
				if (map[nr][nc] == startIsland) break;
				
				if (map[nr][nc] == 0) {
					length++;
					nr += dr[d];
					nc += dc[d];
					continue;
				}
				
				if (length >= 2) {
					int endIsland = map[nr][nc];
					bridge[startIsland][endIsland] = Math.min(bridge[startIsland][endIsland], length);
					bridge[endIsland][startIsland] = Math.min(bridge[endIsland][startIsland], length);
				}
				break;
			}
		}
	}
	
	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA != rootB) {
			parent[rootB] = rootA;
		}
	}
}