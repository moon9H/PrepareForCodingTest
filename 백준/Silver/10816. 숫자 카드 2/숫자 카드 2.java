import java.io.*;
import java.util.*;

public class Main {
    static int[] base;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        base = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) base[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(base);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());

            int count = upperBound(target) - lowerBound(target);
            sb.append(count).append(" ");
        }

        System.out.println(sb);
    }

    static int lowerBound(int target) {
        int low = 0, high = base.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (base[mid] >= target) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    static int upperBound(int target) {
        int low = 0, high = base.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (base[mid] > target) high = mid;
            else low = mid + 1;
        }
        return low;
    }
}
