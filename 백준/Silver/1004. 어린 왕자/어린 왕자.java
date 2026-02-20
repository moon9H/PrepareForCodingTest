import java.io.*;
import java.util.*;

public class Main {
    static boolean inside(int x, int y, int cx, int cy, int r) {
        long dx = x - cx;
        long dy = y - cy;
        long dist2 = dx * dx + dy * dy;
        long r2 = 1L * r * r;
        return dist2 < r2; // 원 "안" (경계는 제외)
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int n = Integer.parseInt(br.readLine().trim());
            int ans = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int cx = Integer.parseInt(st.nextToken());
                int cy = Integer.parseInt(st.nextToken());
                int r  = Integer.parseInt(st.nextToken());

                boolean a = inside(x1, y1, cx, cy, r);
                boolean b = inside(x2, y2, cx, cy, r);

                if (a ^ b) ans++; // 서로 다르면 경계 1번 통과
            }

            sb.append(ans).append('\n');
        }

        System.out.print(sb.toString());
    }
}