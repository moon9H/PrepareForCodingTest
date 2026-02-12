import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, B; // 테스트케이스, 사람수, 목표 높이
	private static int[] arr; // 키 배열
	private static int res, gap = 0; // 최소값을 담을 변수, 두 값의 차이
	// 키의 합을 모두 더해서 목표 높이보다 큰 최소 값까지 작은 거부터 빼면 되지 않을까... => 안 됨 
	// 한 명의 키가 조건에 맞는 최적의 해가 될 수 있으므로 부분 조합으로 풀어야 함 ㅜㅜㅠ 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			arr = new int[N];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			res = Integer.MAX_VALUE;
			findMinValue(0, 0);
			gap = res - B;

			System.out.println("#" + test_case + " " + gap);
		}
	}

	public static void findMinValue(int idx, int sum) {
		// [가지치기] 이미 현재 최솟값(res)보다 합이 커졌다면 더 더해볼 필요가 없음
        if (sum >= res) return;

        // 목표 높이 B를 넘었다면 최솟값 갱신
        if (sum >= B) {
            res = Math.min(res, sum);
            // B와 딱 맞는 값을 만났을 때만 리턴할 수 있을까?
            // 이미 sum이 B보다 크거나 같음이 충족되었다면 해당 경우에서 이후의 과정은 필요가 없음!
            return; 
        }

        // 모든 직원을 다 확인한 경우
        if (idx == N) return;

        
        // 1. 현재 직원을 포함하는 경우
        findMinValue(idx + 1, sum + arr[idx]);
        
        // 2. 현재 직원을 포함하지 않는 경우
        findMinValue(idx + 1, sum);
    }
}
