import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException{
		//------여기에 솔루션 코드를 작성하세요.------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		@SuppressWarnings("unchecked")
		ArrayList<int[]>[] graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			graph[a].add(new int[] {b, t});
		}
		
		int longTakes = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		int[] distX = new int[N + 1];
		Arrays.fill(distX, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N + 1];
		distX[X] = 0;
		pq.add(new int[] {X, 0});
		
		while(!pq.isEmpty()) {
			int[] curNode = pq.poll();
			
			if (visited[curNode[0]]) continue;
			
			visited[curNode[0]] = true;
			
			for (int[] nextNode : graph[curNode[0]]) {
				int newCost = curNode[1] + nextNode[1];
				
				if (distX[nextNode[0]] > newCost) {
					distX[nextNode[0]] = newCost;
					pq.add(new int[] {nextNode[0], newCost});
				}
			}
		}
		
		
		for (int i = 1; i <= N; i++) {
			if (i == X) continue;
			
			int time = 0;
			int[] dist = new int[N + 1];
			Arrays.fill(visited, false);
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			dist[i] = 0;
			pq.add(new int[] {i, 0});
			
			while(!pq.isEmpty()) {
				int[] curNode = pq.poll();
				
				if (visited[curNode[0]]) continue;
				
				visited[curNode[0]] = true;
				
				for (int[] nextNode : graph[curNode[0]]) {
					int newCost = curNode[1] + nextNode[1];
					
					if (dist[nextNode[0]] > newCost) {
						dist[nextNode[0]] = newCost;
						pq.add(new int[] {nextNode[0], newCost});
					}
				}
			}
			
			time = dist[X] + distX[i];
			
			longTakes = Math.max(time, longTakes);
		}
		
		System.out.println(longTakes);
	}
}