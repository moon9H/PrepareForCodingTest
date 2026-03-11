import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static class Node{
		int to;
		double weight;
		public Node(int to, double weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[][] islandArr = new int[N][2]; 
			ArrayList<Node>[] graph = new ArrayList[N];
			boolean[] visited = new boolean[N];
			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < N; i++) {
				islandArr[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				islandArr[i][1] = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine());
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					graph[i].add(new Node(j, Math.sqrt(Math.pow(islandArr[i][0] - islandArr[j][0], 2) 
														+ Math.pow(islandArr[i][1] - islandArr[j][1], 2))));
					graph[j].add(new Node(i, Math.sqrt(Math.pow(islandArr[i][0] - islandArr[j][0], 2) 
							+ Math.pow(islandArr[i][1] - islandArr[j][1], 2))));
				}
			}
			
			PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
				return Double.compare(o1.weight, o2.weight);
			});
			pq.add(new Node(1, 0));
			
			double result = 0;
			int count = 0;
			while(!pq.isEmpty()) {
				Node curNode = pq.poll();
				
				if (visited[curNode.to]) continue;
				
				++count;
				result += curNode.weight * curNode.weight * E;
				visited[curNode.to] = true;
				
				for (Node nextNode : graph[curNode.to]) {
					if (!visited[nextNode.to]) {
						pq.add(new Node(nextNode.to, nextNode.weight));
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(Math.round(result)).append('\n');
		}
		System.out.println(sb);
	}
}