import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			ArrayList<Integer>[] graph = new ArrayList[N + 1];
			ArrayList<Integer>[] reverseGraph = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<>();
				reverseGraph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
				graph[A].add(B);
				reverseGraph[B].add(A);
			}			
			
			int ans = 0;
			
			for (int i = 1; i <= N; i++) {
				int out = bfs(i, graph, N);
				int in = bfs(i, reverseGraph, N);
				
				if (out + in == N - 1) ans++;
			}
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	static int bfs(int start, ArrayList<Integer>[] graph, int size) {
		int cnt = 0;
		boolean[] visited = new boolean[size + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		visited[start] = true;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int curNode = queue.poll();
			
			for (Integer integer : graph[curNode]) {
				if (!visited[integer]) {
					++cnt;
					visited[integer] = true;
					queue.add(integer);
				}
			}
		}
		
		return cnt;
	}
}