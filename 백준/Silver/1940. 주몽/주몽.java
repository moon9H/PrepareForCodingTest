import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine()); // 재료 개수 
        int target = Integer.parseInt(in.readLine()); // 필요한 재료 수 

        // 배열에 재료 넣기 
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 배열 정렬 
        Arrays.sort(arr);
        
        int left = 0; 
        int right = N - 1; 
        int sum = 0;
        int cnt = 0; 
        
        // left < right 가 유지될 때까지 순회 
        while (left < right) {
        	// 두 재료만 사용해야 하므로 두 개 합산 
        	sum = arr[left] + arr[right]; 
        	// target과 합이 동일하면 cnt 올리고 범위 좁히기 
        	if (sum == target) {
        		cnt++;
        		left++;
        		right--;
        	} else if (sum < target) {
        		// 합산 값이 target 보다 작으면 최솟값 올리기 
        		left++;
        	} else {
        		// sum이 target 보다 커지면 최댓값 내리기 
        		right--;
        	}
        }
        System.out.println(cnt);
    }
}
