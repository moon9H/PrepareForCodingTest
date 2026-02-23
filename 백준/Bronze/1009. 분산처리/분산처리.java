import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            int x = (int)(a % 10);

            int ans;
            // 주기 1인 애들
            if (x == 0) ans = 10;          // 0의 거듭제곱은 일의 자리 0 -> 10번
            else if (x == 1) ans = 1;
            else if (x == 5) ans = 5;
            else if (x == 6) ans = 6;
            else {
                int e = (int)(b % 4);
                if (e == 0) e = 4;

                int p = 1;
                for (int i = 0; i < e; i++) p = (p * x) % 10;

                ans = (p == 0) ? 10 : p;
            }

            sb.append(ans).append('\n');
        }

        System.out.print(sb);
    }
}