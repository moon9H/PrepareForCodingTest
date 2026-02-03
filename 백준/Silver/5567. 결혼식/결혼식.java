import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int bfs(LinkedList<Integer>[] adj, int N) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		
		queue.add(new int[] {1, 0});
		visited[1] = true;
		
		int count = 0;
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int now = current[0];
			int depth = current[1];
			
			if (depth < 2) {
				for (int next : adj[now]) {
					if (!visited[next]) {
						visited[next] = true;
						queue.add(new int[] {next, depth + 1});
						count++;
					}
				}
			}
		}
		return count;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());		// 학생 수
		int M = Integer.parseInt(br.readLine());		// 관계 수
		
		LinkedList<Integer>[] adj = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}
		
		System.out.println(bfs(adj, N));
	}
}