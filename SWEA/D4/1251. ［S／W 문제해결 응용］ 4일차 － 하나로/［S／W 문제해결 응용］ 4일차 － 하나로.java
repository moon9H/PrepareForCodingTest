import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	private static int N;
	private static int[] parents;
	static class Edge{
		int row, col;
		double distance;
		public Edge(int row, int col, double distance) {
			super();
			this.row = row;
			this.col = col;
			this.distance = distance;
		}
		@Override
		public String toString() {
			return "Edge [row=" + row + ", col=" + col + ", distance=" + distance + "]";
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			makeSet();
			int[][] islandArr = new int[N][2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				islandArr[i][0] = Integer.parseInt(st.nextToken()); 
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				islandArr[i][1] = Integer.parseInt(st.nextToken()); 
			}
			
			double E = Double.parseDouble(br.readLine());
			ArrayList<Edge> edgeGraph = new ArrayList<>();
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					edgeGraph.add(new Edge(i, j, Math.sqrt(Math.pow(islandArr[i][0] - islandArr[j][0], 2) + 
												 			Math.pow(islandArr[i][1] - islandArr[j][1], 2))));
				}
			}
			edgeGraph.sort((o1, o2) -> {
				return Double.compare(o1.distance, o2.distance);
			});
			
			double result = 0;
			int count = 0;
			
			for (Edge edge : edgeGraph) {
				if (union(edge.row, edge.col)) {
					++count;
					result += Math.pow(edge.distance, 2) * E;
					if (count == N - 1) break;
				}
			}
			sb.append('#').append(tc).append(' ').append(Math.round(result)).append('\n');
		}
		System.out.println(sb);
	}
	
	static void makeSet() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if (parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
}