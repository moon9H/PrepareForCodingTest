import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());   // 정점의 개수 
        int E = Integer.parseInt(br.readLine());   // 간선의 개수

        Edge[] edges = new Edge[E];

        for(int i = 0;i<E;i++) {
            StringTokenizer now = new StringTokenizer(br.readLine());
            int nowStart = Integer.parseInt(now.nextToken());
            int nowEnd = Integer.parseInt(now.nextToken());
            int nowWeight = Integer.parseInt(now.nextToken());
            Edge edge = new Edge(nowStart, nowEnd, nowWeight);
            edges[i] = edge;
        }

        // 크루스칼을 위한 정렬
        Arrays.sort(edges, (a, b) -> a.weight - b.weight);

        // makeSet
        parent = new int[V+1];
        for(int i = 0;i<=V;i++) {
            parent[i] = i;
        }

        int totalEdges = 0;
        long totalWeight = 0;
        for(Edge e : edges) {
            // union 연산 루프 생성 여부 판단을 함께 수행
            boolean isLoop = union(e.start, e.end);
            // 루프가 생기지 않을 경우에만 간선수와 가중치 더하기
            if(!isLoop) {
                totalEdges++;
                totalWeight += e.weight;
            }
            // 지금까지 더해진 간선수가 노드수-1일 경우 중단
            if(totalEdges == V - 1) break;
        }

        System.out.println(totalWeight);
    }

    // 유니온 파인드 구현

    static boolean union (int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return true;
        parent[rootB] = rootA;
        return false;
    }

    static int find (int a) {
        if(parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    static class Edge {
        int start;
        int end;
        int weight;
        public Edge() {}
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}