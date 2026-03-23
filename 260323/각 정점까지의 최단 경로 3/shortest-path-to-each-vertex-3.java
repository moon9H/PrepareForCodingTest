import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());			
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new int[] {to, w});
		}
		
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<int[]> pq = new PriorityQueue<>( (o1, o2) -> o1[1] - o2[1]);
		dist[1] = 0;
		pq.add(new int[] {1, 0});
		
		while(!pq.isEmpty()) {
			int[] curNode = pq.poll();
			
			for (int[] nextNode : graph.get(curNode[0])) {
				int newDist = curNode[1] + nextNode[1];
				
				if (dist[nextNode[0]] > newDist) {
					dist[nextNode[0]] = newDist;
					pq.add(new int[] {nextNode[0], newDist});
				}
			}
		}
		for (int i = 2; i <= N; i++) {
			if (dist[i] == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(dist[i]);
		}
	}
}