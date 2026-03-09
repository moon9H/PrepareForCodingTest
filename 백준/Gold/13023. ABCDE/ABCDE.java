import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int ans;
	private static ArrayList<Integer>[] graph;
	private static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		ans = 0;
		for (int i = 0; i < N - 1; i++) {
			visited = new boolean[N];
			dfs(i, 1);
			if (ans == 1) break;
		}
		System.out.println(ans);
	}
	
	static void dfs(int startNode, int length) {
		if (length == 5) {
			ans = 1;
			return;
		}
		visited[startNode] = true;
		for (Integer i : graph[startNode]) {
			if (!visited[i]) {
				dfs(i, length + 1);
				if (ans == 1) return;
			}
		}
		visited[startNode] = false;
	}
}