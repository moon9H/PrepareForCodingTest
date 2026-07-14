import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = 10;
        for (int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine());
            List<Integer> crypto = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()) crypto.add(Integer.parseInt(st.nextToken()));

            int cmdLen = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < cmdLen; i++) {
                // 삽입 명령 구현
               if (st.nextToken().equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                   for (int j = 0; j < y; j++) {
                       crypto.add(x + j, Integer.parseInt(st.nextToken()));
                   }
               }

               // 삭제 명령 구현
               else {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());

                   for (int j = 0; j < y; j++) {
                       crypto.remove(x);
                   }
               }
            }

            sb.append('#').append(tc).append(' ');
            for (int i = 0; i < 10; i++) {
                sb.append(crypto.get(i)).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}