import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] numbers;         // 순열 저장 배열 (수도코드 numbers[])
    static boolean[] isSelected;  // 사용 여부 배열 (수도코드 isSelected[])
    static StringBuilder sb = new StringBuilder();

    static void perm(int cnt) { // cnt: 현재까지 뽑은 개수
        if (cnt == M) { // 순열 생성 완료
            for (int i = 0; i < M; i++) sb.append(numbers[i]).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (isSelected[i]) continue;   // 이미 쓴 수면 스킵

            numbers[cnt] = i;              // numbers[cnt] <- i
            isSelected[i] = true;          // isSelected[i] <- true

            perm(cnt + 1);                 // perm(cnt+1)

            isSelected[i] = false;         // isSelected[i] <- false (백트래킹)
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[M];
        isSelected = new boolean[N + 1];

        perm(0);

        System.out.print(sb);
    }
}