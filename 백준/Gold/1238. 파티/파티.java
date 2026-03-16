import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 요원의 수
		int M = Integer.parseInt(st.nextToken());	// 도로 수
		int X = Integer.parseInt(st.nextToken());	// 도착 도시 번호
		
		List<Edge>[] graph = new ArrayList[N+1];
		List<Edge>[] graphR = new ArrayList[N+1];
		for(int i = 0;i<N+1;i++) {
			graph[i] = new ArrayList<>();
			graphR[i] = new ArrayList<>();
		}
		
//		그래프 연결
		for(int i = 0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[start].add(new Edge(start, end, weight));
			graphR[end].add(new Edge(end, start, weight));
		}
		
//		도시 X에서 각 도시까지의 최소거리를 구한다. (다익스트라)
//		각 도시에서 X까지의 최소거리를 구한다. (역방향으로 다익스트라?)
//		두 결과를 합쳐 가장 큰 거리를 출력한다.
		
		int[] dijk = new int[N+1];
		Arrays.fill(dijk, Integer.MAX_VALUE);
		dijk[X] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pq.offer(new Edge(0, X, 0));
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(dijk[now.end] != now.weight) continue;
			
			for(Edge e : graph[now.end]) {
				int nextW = now.weight + e.weight;
				if(dijk[e.end] > nextW) {
					dijk[e.end] = nextW;
					pq.offer(new Edge(now.end, e.end, nextW));
				}
			}
		}
		
		int[] dijkr = new int[N+1];
		Arrays.fill(dijkr, Integer.MAX_VALUE);
		dijkr[X] = 0;
		
		PriorityQueue<Edge> pqr = new PriorityQueue<>((a, b) -> a.weight - b.weight);
		pqr.offer(new Edge(0, X, 0));
		
		while(!pqr.isEmpty()) {
			Edge now = pqr.poll();
			
			if(dijkr[now.end] != now.weight) continue;
			
			for(Edge e : graphR[now.end]) {
				int nextW = now.weight + e.weight;
				if(dijkr[e.end] > nextW) {
					dijkr[e.end] = nextW;
					pqr.offer(new Edge(now.end, e.end, nextW));
				}
			}
		}
		
		int max = 0;
		
		for(int i = 1;i<=N;i++) {
			max = Integer.max(max, dijk[i] + dijkr[i]);
		}
		
		System.out.println(max);
		
	}
	
	static class Edge {
		int start;
		int end;
		int weight;
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}
}