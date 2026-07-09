import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            // 각 테스트 케이스 첫 줄에 테스트 케이스 번호가 들어와서 스킵용
            br.readLine();
            st = new StringTokenizer(br.readLine());

            // 사이클 관리를 위한 변수
            int minusCnt = 1;
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 0; i < 8; i++) {
                dq.offer(Integer.valueOf(st.nextToken()));
            }
            while(dq.getLast() != 0){
                int head = dq.poll();
                if (head - minusCnt >= 0){
                    dq.offer(head - minusCnt++);
                } else {
                    dq.offer(0);
                }

                if (minusCnt == 6) minusCnt = 1;
            }

            sb.append('#').append(tc).append(' ');
            for (Integer i : dq) {
                sb.append(i).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}