import java.io.*;
import java.util.*;

public class Main {
	static int[] parent;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 노드(학교)수
		int M = Integer.parseInt(st.nextToken());	// 간선(도로)수

		String[] genderInput = br.readLine().split(" ");
		char[] gender = new char[N+1];
		for(int i = 1;i<=N;i++) {
			gender[i] = genderInput[i-1].charAt(0);
		}
		
		List<Edge> edges = new ArrayList<>();
		
		for(int i = 0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			// 양방향 그래프 연결
			edges.add(new Edge(start, end, weight));
			edges.add(new Edge(end, start, weight));
		}

		edges.sort((a, b) -> a.weight - b.weight);

		// makeSet
		parent = new int[N+1];
		for(int i = 0;i<=N;i++) {
			parent[i] = i;
		}
		int totalWeight = 0;
		int totalEdges = 0;
		
		for(int i = 0;i<edges.size();i++) {
			int nowStart = edges.get(i).start;
			int nowEnd = edges.get(i).end;
			int nowWeight = edges.get(i).weight;

			if(gender[nowStart] == gender[nowEnd] || !union(nowStart, nowEnd)) continue;
			union(nowStart, nowEnd);
			totalWeight += nowWeight;
			totalEdges++;

			if(totalEdges == N-1) break;
		}

		if(totalEdges == N-1) {
			System.out.println(totalWeight);
		} else {
			System.out.println(-1);
		}
		

	}

	static int findSet(int a) {
		if(parent[a] != a) {
			parent[a] = findSet(parent[a]);
		}
		return parent[a];
	}

	static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if(rootA == rootB) return false;
		parent[rootA] = rootB;
		return true;
	}

	static class Edge {
		int start;
		int end;
		int weight;
		Edge(int start, int end, int weight){
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}
}