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
		
		ArrayList<int[]>[] graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[to].add(new int[] {from, weight});
			graph[from].add(new int[] {to, weight});
		}
		
		int[] foxDist = new int[N + 1];
		Arrays.fill(foxDist, Integer.MAX_VALUE);
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		foxDist[1] = 0;
		pq.add(new int[] {1, 0});
		while(!pq.isEmpty()) {
			int[] curNode = pq.poll();
			
			if (foxDist[curNode[0]] < curNode[1]) continue;
			
			for (int[] nextNode : graph[curNode[0]]) {
				int newDist = curNode[1] + nextNode[1];
				
				if (foxDist[nextNode[0]] > newDist) {
					foxDist[nextNode[0]] = newDist;
					pq.add(new int[] {nextNode[0], newDist});
				}
			}
		}
		
		double[][] wolfDist = new double[N + 1][2];				// 0 : 빠름, 1 : 느림
		for (double[] ds : wolfDist) {
			Arrays.fill(ds, Double.MAX_VALUE);			
		}
		
		PriorityQueue<double[]> pq2 = new PriorityQueue<>((o1, o2) -> Double.compare(o1[1], o2[1]));
		wolfDist[1][0] = 0;
		pq2.add(new double[] {1, 0, 0});
		
		while(!pq2.isEmpty()) {
			double[] curNode = pq2.poll();
			
			int node = (int) curNode[0];
			double dist = curNode[1];
			int state = (int) curNode[2];						// 0 : 빠름, 1 : 느림
			
			if (wolfDist[node][state] < dist) continue;
			
			for (int[] nextNode : graph[node]) {
				double newDist = dist;
				
				if (state == 0) {
					newDist += nextNode[1] / 2.0;
					if (wolfDist[nextNode[0]][1] > newDist) {
						wolfDist[nextNode[0]][1] = newDist;
						pq2.add(new double[]{nextNode[0], newDist, 1});
					}
				} else {
					newDist += nextNode[1] * 2.0;
					if (wolfDist[nextNode[0]][0] > newDist) {
						wolfDist[nextNode[0]][0] = newDist;
						pq2.add(new double[]{nextNode[0], newDist, 0});
					}
				}
			}
		}
		
		int treeStub = 0;
		
		for (int i = 2; i <= N; i++) {
		    if (foxDist[i] == Integer.MAX_VALUE) continue;
			if (foxDist[i] < wolfDist[i][0] && foxDist[i] < wolfDist[i][1]) treeStub++;
		}
		
		System.out.println(treeStub);
	}
}