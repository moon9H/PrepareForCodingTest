import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int fastTime = 0;
        int[] dist = new int[100_001];
        Deque<Integer> dq = new ArrayDeque<>();
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[N] = 0;
        dq.addFirst(N);

        while(!dq.isEmpty()){
            int curPos = dq.poll();
            if (curPos == K){
                fastTime = dist[curPos];
                dq.clear();
                continue;
            }

            if (curPos + 1 < 100_001 && dist[curPos + 1] > dist[curPos] + 1){
                dist[curPos + 1] = dist[curPos] + 1;
                dq.add(curPos + 1);
            }

            if (curPos - 1 >= 0 && dist[curPos - 1] > dist[curPos] + 1) {
                dist[curPos - 1] = dist[curPos] + 1;
                dq.add(curPos - 1);
            }

            if (curPos * 2 < 100_001 && dist[curPos * 2] > dist[curPos]){
                dist[curPos * 2] = dist[curPos];
                dq.addFirst(curPos * 2);
            }
        }

        System.out.println(fastTime);
    }
}