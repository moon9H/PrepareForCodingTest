import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 나무 그루터기 개수
        int M = Integer.parseInt(st.nextToken());   // 오솔길 개수
        
        List<Edge>[] graph = new ArrayList[N+1];
        for(int i = 0;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken()) * 2;
            graph[start].add(new Edge(end, weight));
            graph[end].add(new Edge(start, weight));
        }

        long[] minDistRabbit = new long[N+1];
        Arrays.fill(minDistRabbit, Long.MAX_VALUE);
        minDistRabbit[1] = 0;

        PriorityQueue<Edge> pqr = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
        pqr.offer(new Edge(1, 0));

        while(!pqr.isEmpty()) {
            Edge now = pqr.poll();

            if(minDistRabbit[now.end] != now.weight) continue;

            for(Edge e : graph[now.end]) {
                long nextW = now.weight + e.weight;
                if(minDistRabbit[e.end] > nextW) {
                    minDistRabbit[e.end] = nextW;
                    pqr.offer(new Edge(e.end, nextW));
                }
            }
        }

        long[] oddWolf = new long[N+1];
        long[] evenWolf = new long[N+1];
        Arrays.fill(oddWolf, Long.MAX_VALUE);
        Arrays.fill(evenWolf, Long.MAX_VALUE);
        // oddWolf[1] = 0;
        evenWolf[1] = 0;

        PriorityQueue<Edge> pqw = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
        pqw.offer(new Edge(1, 0, 0));

        while(!pqw.isEmpty()) {
            Edge now = pqw.poll();

            if(now.depth % 2 == 0) {
                if(evenWolf[now.end] != now.weight) continue;
            } else {
                if(oddWolf[now.end] != now.weight) continue;
            }

            for(Edge e : graph[now.end]) {
                // 현재 꺼낸 값의 depth를 통해 가중치를 계산한다.
                // 계산된 가중치를 토대로 다익스트라를 수행한다.
                long nextW;
                if(now.depth % 2 == 0) {
                    // 짝수일때
                    nextW = now.weight + e.weight / 2;
                    if(oddWolf[e.end] > nextW) {
                        oddWolf[e.end] = nextW;
                        pqw.offer(new Edge(e.end, nextW, now.depth + 1));
                    }
                } else {
                    // 홀수일때
                    nextW = now.weight + e.weight * 2;
                    if(evenWolf[e.end] > nextW) {
                        evenWolf[e.end] = nextW;
                        pqw.offer(new Edge(e.end, nextW, now.depth + 1));
                    }
                }
            }
        }

        long[] minDistWolf = new long[N+1];


        for(int i = 1;i<=N;i++) {
            minDistWolf[i] = Math.min(evenWolf[i], oddWolf[i]);
        }

        int answer = 0;
        for(int i = 1;i<=N;i++) {
            if(minDistRabbit[i] < minDistWolf[i]) answer++;
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