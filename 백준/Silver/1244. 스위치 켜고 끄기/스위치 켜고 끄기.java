import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] switches = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }
        int studentNum = Integer.parseInt(br.readLine());
        int[][] command = new int[studentNum][2];
        for (int std = 0; std < studentNum; std++) {
            st = new StringTokenizer(br.readLine());
            command[std][0] = Integer.parseInt(st.nextToken());
            command[std][1] = Integer.parseInt(st.nextToken());
        }

        for (int[] std : command) {
            int num = std[1];
            if (std[0] == 1) {
                for (int i = num; i <= N; i += num) {
                    switches[i] = (switches[i] == 0) ? 1 : 0;
                }
            } else if (std[0] == 2) {
                switches[num] = (switches[num] == 0) ? 1 : 0;
                int idx = 1;
                while (num - idx >= 1 && num + idx <= N) {
                    if (switches[num - idx] == switches[num + idx]) {
                        switches[num - idx] = (switches[num - idx] == 0) ? 1 : 0;
                        switches[num + idx] = (switches[num + idx] == 0) ? 1 : 0;
                    } else break;
                    idx++;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(switches[i] + " ");
            if (i % 20 == 0) System.out.println();
        }
    }
}