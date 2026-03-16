import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<int[]>[] graph = new ArrayList[N + 1];
        @SuppressWarnings("unchecked")
        ArrayList<int[]>[] reverseGraph = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[a].add(new int[] {b, t});
            reverseGraph[b].add(new int[] {a, t}); // 역방향
        }

        int[] go = dijkstra(graph, X, N);         // X -> i
        int[] back = dijkstra(reverseGraph, X, N); // i -> X

        int longTakes = 0;
        for (int i = 1; i <= N; i++) {
            longTakes = Math.max(longTakes, go[i] + back[i]);
        }

        System.out.println(longTakes);
    }

    private static int[] dijkstra(ArrayList<int[]>[] graph, int start, int N) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        dist[start] = 0;
        pq.add(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] curNode = pq.poll();
            int cur = curNode[0];
            int cost = curNode[1];

            if (visited[cur]) continue;
            visited[cur] = true;

            for (int[] nextNode : graph[cur]) {
                int next = nextNode[0];
                int nextCost = nextNode[1];

                if (dist[next] > cost + nextCost) {
                    dist[next] = cost + nextCost;
                    pq.add(new int[] {next, dist[next]});
                }
            }
        }

        return dist;
    }
}