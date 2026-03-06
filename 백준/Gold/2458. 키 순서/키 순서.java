import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		ArrayList<Integer>[] reverseGraph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
			reverseGraph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			reverseGraph[to].add(from);
		}
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			int in = bfs(graph, i, N);
			int out = bfs(reverseGraph, i, N);
			
			if (in + out == N - 1) ++ans;
		}
		
		System.out.println(ans);
	}
	
	static int bfs(ArrayList<Integer>[] graph, int start, int size) {
		int count = 0;
		boolean[] visited = new boolean[size + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		visited[start] = true;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int curNode = queue.poll();
			
			for (Integer integer : graph[curNode]) {
				if (!visited[integer]) {
					++count;
					queue.add(integer);
					visited[integer] = true;
				}
			}
		}
				
		return count;
	}
}