import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static long[] holes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        holes = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            holes[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(holes);

        long left = 1;
        long right = holes[N - 1] - holes[0] + 1;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canCover(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean canCover(long length) {
        int count = 0;
        int idx = 0;

        while (idx < N) {
            count++;

            long start = holes[idx];
            long end = start + length - 1;

            while (idx < N && holes[idx] <= end) {
                idx++;
            }

            if (count > K) {
                return false;
            }
        }

        return true;
    }
}