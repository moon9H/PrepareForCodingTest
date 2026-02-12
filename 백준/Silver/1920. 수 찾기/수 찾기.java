import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 수 찾기 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N개의 정수 배열 
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // *** 이진 탐색 전 정렬!
        Arrays.sort(arr);

        // M개의 포함 여부 판단 정수 배열 
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            
            // 이진 탐색 함수 호출
            sb.append(binarySearch(arr, target)).append("\n");
        }
        
        System.out.print(sb);
    }

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return 1; // 찾았을 때
            } else if (arr[mid] > target) {
                // 중간값이 찾으려는 값보다 크면 왼쪽 절반 탐색
                high = mid - 1;
            } else {
                // 중간값이 찾으려는 값보다 작으면 오른쪽 절반 탐색
                low = mid + 1;
            }
        }

        return 0; // 끝까지 못 찾았을 때
    }
}
