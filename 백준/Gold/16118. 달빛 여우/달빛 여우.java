import java.io.*;
import java.util.*;

public class Main {

    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken()) * 2;

            graph[start].add(new Edge(end, weight));
            graph[end].add(new Edge(start, weight));
        }

        long[] minDistRabbit = new long[N + 1];
        Arrays.fill(minDistRabbit, INF);
        minDistRabbit[1] = 0;

        PriorityQueue<Edge> pqr = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
        pqr.offer(new Edge(1, 0));

        while (!pqr.isEmpty()) {
            Edge now = pqr.poll();

            if (minDistRabbit[now.end] != now.weight) continue;

            for (Edge e : graph[now.end]) {
                long nextW = now.weight + e.weight;
                if (minDistRabbit[e.end] > nextW) {
                    minDistRabbit[e.end] = nextW;
                    pqr.offer(new Edge(e.end, nextW));
                }
            }
        }

        long[] oddWolf = new long[N + 1];
        long[] evenWolf = new long[N + 1];
        Arrays.fill(oddWolf, INF);
        Arrays.fill(evenWolf, INF);

        evenWolf[1] = 0;

        PriorityQueue<Edge> pqw = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
        pqw.offer(new Edge(1, 0, 0));

        while (!pqw.isEmpty()) {
            Edge now = pqw.poll();

            if (now.depth % 2 == 0) {
                if (evenWolf[now.end] != now.weight) continue;
            } else {
                if (oddWolf[now.end] != now.weight) continue;
            }

            for (Edge e : graph[now.end]) {
                long nextW;
                if (now.depth % 2 == 0) {
                    nextW = now.weight + e.weight / 2;
                    if (oddWolf[e.end] > nextW) {
                        oddWolf[e.end] = nextW;
                        pqw.offer(new Edge(e.end, nextW, now.depth + 1));
                    }
                } else {
                    nextW = now.weight + e.weight * 2;
                    if (evenWolf[e.end] > nextW) {
                        evenWolf[e.end] = nextW;
                        pqw.offer(new Edge(e.end, nextW, now.depth + 1));
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 2; i <= N; i++) {
            long minDistWolf = Math.min(evenWolf[i], oddWolf[i]);
            if (minDistRabbit[i] < minDistWolf) answer++;
        }

        System.out.println(answer);
    }

    static class Edge {
        int end;
        long weight;
        int depth;

        Edge(int end, long weight) {
            this.end = end;
            this.weight = weight;
        }

        Edge(int end, long weight, int depth) {
            this.end = end;
            this.weight = weight;
            this.depth = depth;
        }
    }
}