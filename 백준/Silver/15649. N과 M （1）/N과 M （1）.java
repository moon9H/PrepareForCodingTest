import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static void perm(int cnt, int N, int M, int[] numbers, boolean[] isSelected, StringBuilder sb) { // cnt: 현재까지 뽑은 개수
        if (cnt == M) { // 순열 생성 완료
            for (int i = 0; i < M; i++) sb.append(numbers[i]).append(' ');
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (isSelected[i]) continue;   // 이미 쓴 수면 스킵

            numbers[cnt] = i;              // numbers[cnt] <- i
            isSelected[i] = true;          // isSelected[i] <- true

            perm(cnt + 1, N, M, numbers, isSelected, sb);                 // perm(cnt+1)

            isSelected[i] = false;         // isSelected[i] <- false (백트래킹)
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] numbers = new int[M];
        boolean[] isSelected = new boolean[N + 1];

        perm(0, N, M, numbers, isSelected, sb);

        System.out.print(sb);
    }
}