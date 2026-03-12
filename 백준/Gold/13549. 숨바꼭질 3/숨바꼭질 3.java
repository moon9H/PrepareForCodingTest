import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 수빈이의 위치
        int K = Integer.parseInt(st.nextToken());   // 동생의 위치

        int[] dist = new int[100001];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<pos> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.add(new pos(N, 0));
        dist[N] = 0;

        while(!pq.isEmpty()) {
            pos now = pq.poll();
            if(now.idx == K) break;
            if(dist[now.idx] != now.dist) continue;

            // x+1로 걷기
            int nextIdx = now.idx + 1;
            if(nextIdx <= K) {
                if(dist[nextIdx] > dist[now.idx] + 1) {
                    dist[nextIdx] = dist[now.idx] + 1;
                    pq.add(new pos(nextIdx, dist[now.idx] + 1));
                }
            }

            // x-1로 걷기
            int prevIdx = now.idx - 1;
            if(prevIdx >= 0) {
                if(dist[prevIdx] > dist[now.idx] + 1) {
                    dist[prevIdx] = dist[now.idx] + 1;
                    pq.add(new pos(prevIdx, dist[now.idx] + 1));
                }
            }

            // 2*x로 순간이동
            int warpIdx = now.idx * 2;
            if(warpIdx <= 100000) {
                if(dist[warpIdx] > dist[now.idx]) {
                    dist[warpIdx] = dist[now.idx];
                    pq.add(new pos(warpIdx, dist[now.idx]));
                }
            }
        }

        System.out.println(dist[K]);
    }

    static class pos {
        int idx;
        int dist;
        pos(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }
}
