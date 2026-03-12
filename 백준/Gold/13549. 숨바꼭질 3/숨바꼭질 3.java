import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dist = new int[100001];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		if (N >= K) {
			System.out.println(N - K);
			return;
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>( (o1, o2) -> {
			return Integer.compare(o1[1], o2[1]);
		});
		dist[N] = 0;
		pq.add(new int[] {N, 0});
		
		while (!pq.isEmpty()) {
			int[] curPos = pq.poll();
            
            if (curPos[1] > dist[curPos[0]]) continue;
            
			if (curPos[0] == K) {
				System.out.println(curPos[1]);
				return;
			}
			
			if (curPos[0] - 1 >= 0) {
				int newCost = curPos[1] + 1;
				if (dist[curPos[0] - 1] > newCost) {
					dist[curPos[0] - 1] = newCost;
					pq.add(new int[] {curPos[0] - 1, newCost});
				}
			}
			
			if (curPos[0] + 1 <= 100000) {
				int newCost = curPos[1] + 1;
				if (dist[curPos[0] + 1] > newCost) {
					dist[curPos[0] + 1] = newCost;
					pq.add(new int[] {curPos[0] + 1, newCost});
				}
			}
			
			if (curPos[0] * 2 <= 100000) {
				int newCost = curPos[1];
				if (dist[curPos[0] * 2] > newCost) {
					dist[curPos[0] * 2] = newCost;
					pq.add(new int[] {curPos[0] * 2, newCost});
				}
			}
		}
		
		System.out.println(Arrays.toString(dist));
	}
}