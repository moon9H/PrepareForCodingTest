import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int val, idx;
        Pair(int val, int idx) { this.val = val; this.idx = idx; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Pair[] arr = new Pair[N];
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(v, i);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.val != b.val) return Integer.compare(a.val, b.val);
            return Integer.compare(a.idx, b.idx); // 같은 값이면 원래 인덱스 작은 게 먼저
        });

        int[] P = new int[N];
        for (int pos = 0; pos < N; pos++) {
            P[arr[pos].idx] = pos;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (i > 0) sb.append(' ');
            sb.append(P[i]);
        }
        System.out.println(sb);
    }
}