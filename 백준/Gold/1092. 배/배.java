import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] crane = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] box = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(crane);
        Arrays.sort(box);

        reverse(crane);
        reverse(box);

        if (box[0] > crane[0]) {
            System.out.println(-1);
            return;
        }

        int[] pos = new int[N];
        boolean[] moved = new boolean[M];

        int movedCount = 0;
        int time = 0;

        while (movedCount < M) {
            for (int i = 0; i < N; i++) {
                while (pos[i] < M) {
                    if (!moved[pos[i]] && crane[i] >= box[pos[i]]) {
                        moved[pos[i]] = true;
                        pos[i]++;
                        movedCount++;
                        break;
                    }
                    pos[i]++;
                }
            }
            time++;
        }

        System.out.println(time);
    }

    private static void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}