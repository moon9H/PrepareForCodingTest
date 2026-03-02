import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] degree;
	static List<Integer>[] link;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] adj = new ArrayList[N+1];
		degree = new int[N+1];
		link = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			link[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());  // u가 v보다 빨리 푸는게 좋다
			int v = Integer.parseInt(st.nextToken());
			link[u].add(v);  // v 앞에 u가 있다
			degree[v]++;  // v 앞에 있는 애들 수 증가
		}
		
		System.out.println(topo());
	}
	
	private static String topo() {
//		Queue<Integer> q = new ArrayDeque<Integer>();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();  
		// 우선 큐 써야 쉬운 문제 순, 즉 번호가 작은 순으로 정렬됨
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++) {
			if(degree[i] == 0) {
				pq.offer(i);
			}
		}
		
		while(!pq.isEmpty()) {
			int curr = pq.poll();
			sb.append(curr).append(" ");  // 큐랑 다르게 우선큐일때는 작은 순서 우선이기 때문에 꺼낼때 추가해야함
			// 큐는 fifo -> 꺼낼 때 추가하든, 넣을 때 추가하든 결과는 같음
			
			for(int p : link[curr]) {
				if(--degree[p] == 0) {
					pq.offer(p);
				}
			}
		}
		
		return String.valueOf(sb);
	}
}