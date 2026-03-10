import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());   // 정점의 개수 
        int E = Integer.parseInt(st.nextToken());   // 간선의 개수

        Edge[] edges = new Edge[E];
        // 1. edges 배열에 간선 정보(가중치)를 저장한다.
        // 2. edges 배열을 weight 기준으로 정렬한다.
        // 3. unionFind에 필요한 parent 배열을 만든다.
        // 4. edges 배열에서 하나씩 순차적으로 꺼내며 union을 수행한다.
        //    이때 이미 같은 그룹인 경우 false를 리턴하고 union을 수행한 경우 true를 리턴한다.
        //    true를 받을 경우 간선수를 추가하고 최종 가중치를 더한다.
        // 5. 연결된 간선의 수가 V-1인경우 최종 가중치를 출력한다.

        for(int i = 0;i<E;i++) {
            StringTokenizer now = new StringTokenizer(br.readLine());
            int nowStart = Integer.parseInt(now.nextToken());
            int nowEnd = Integer.parseInt(now.nextToken());
            int nowWeight = Integer.parseInt(now.nextToken());
            Edge edge = new Edge(nowStart, nowEnd, nowWeight);
            edges[i] = edge;
        }

        Arrays.sort(edges, (a, b) -> a.weight - b.weight);

        parent = new int[V+1];
        for(int i = 0;i<=V;i++) {
            parent[i] = i;
        }

        int totalEdges = 0;
        int totalWeight = 0;
        for(Edge e : edges) {
            boolean isLoop = union(e.start, e.end);
            if(!isLoop) {
                totalEdges++;
                totalWeight += e.weight;
            }
            if(totalEdges == V - 1) break;
        }

        System.out.println(totalWeight);
    }

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