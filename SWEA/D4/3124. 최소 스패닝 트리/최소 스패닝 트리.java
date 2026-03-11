import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			boolean[] visited = new boolean[V + 1];
			int[] minEdge = new int[V + 1];
			ArrayList<int[]>[] graph = new ArrayList[V + 1];
			for (int i = 0; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph[a].add(new int[] {b, c});
				graph[b].add(new int[] {a, c});
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
				return Integer.compare(o1[1], o2[1]);
			});
			pq.add(new int[] {V, 0});
			long result = 0;
			int count = 0;
			
			while(!pq.isEmpty()) {
				int[] curNode = pq.poll();
				
				if (visited[curNode[0]]) continue;
				
				visited[curNode[0]] = true;
				result += curNode[1];
				++count;
				
				for (int[] nextNode : graph[curNode[0]]) {
					if (!visited[nextNode[0]]) {
						pq.add(new int[] {nextNode[0], nextNode[1]});
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(count == V ? result : -1).append('\n');
		}
		System.out.println(sb);
	}
}