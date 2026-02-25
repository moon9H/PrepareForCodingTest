import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] graph = new int[N + 1][N + 1];
		boolean[] visited = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			graph[parent][child] = 1;
			graph[child][parent] = 1;
		}
		
		int relationship = -1;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {start, 0});
		visited[start] = true;
		while(!queue.isEmpty()) {
			int[] curPerson = queue.poll();
			for (int i = 1; i < N + 1; i++) {
				if (graph[curPerson[0]][i] == 1 && !visited[i]) {
					if (i == target) relationship = curPerson[1] + 1;
					queue.add(new int[] {i, curPerson[1] + 1});
					visited[i] = true;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(relationship);
		System.out.println(sb);
	}
}