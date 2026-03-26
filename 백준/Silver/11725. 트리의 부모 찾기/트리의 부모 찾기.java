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
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}
		
		boolean[] visited = new boolean[N + 1];
		int[] parents = new int[N + 1];
		int parentNode = -1;
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.add(new int[] {1, -1});
		visited[1] = true;
		while(!queue.isEmpty()) {
			int[] curNode = queue.poll();
			parents[curNode[0]] = curNode[1];
			
			for (Integer nextNode : graph[curNode[0]]) {
				if (visited[nextNode]) continue;
				visited[nextNode] = true;
				queue.add(new int[] {nextNode, curNode[0]});
			}
		}
		
		for (int i = 2; i <= N; i++) {
			System.out.println(parents[i]);
		}
	}
}