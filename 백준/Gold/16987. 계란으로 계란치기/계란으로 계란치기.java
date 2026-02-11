import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, total = 0;
	private static int[] strength, weight;
	private static boolean[] isBroken;

	public static void main(String[] args) throws IOException {
		// 왼쪽 계란부터 선택하며 다른 계란 중에 하나와 부딪혀서 내구도 감소시킴
		// 치고 나서 선택은 바로 오른쪽 계란
		// 가장 오른쪽 계란을 선택하고 위 과정을 거친 뒤 프로그램 종료
		// => 최적의 선택이 되려면 ? => 일단 재귀로 완탐 해보자..
		// 입력: 계란 개수 \n 내구도 무게
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		strength = new int[N];
		weight = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			strength[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}
//		System.out.println(strength[0]+ " " + weight[0]);

		// 계란의 파손 여부 저장
		isBroken = new boolean[N];

		// 재귀 함수
		dfs(0, isBroken);

		// 출력
		System.out.println(total);
	}

	public static void dfs(int idx, boolean[] isBroken) {
		if (idx == N) {
			int count = 0;

			for (int i = 0; i < N; i++) {
				if (strength[i] <= 0)
					count++;
			}

			total = Math.max(count, total);
			return;
		}

		// 깨진 계란 개수가 n-1이면 종료
//		int n = 0;
//		for (int j = 0; j < N; j++) {
//			if (isBroken[j])
//				n++;
//		}

		if (strength[idx] <= 0 || allOthersBroken(idx)) {
			dfs(idx + 1, isBroken);
			return;
		}

		// 현재 계란에서 몇 번째 계란을 선택할지에 따라 경우가 나뉨
		for (int i = 0; i < N; i++) {
			if (i == idx || strength[i] <= 0)
				continue;

			strength[idx] -= weight[i];
			strength[i] -= weight[idx];

			dfs(idx + 1, isBroken);

			strength[idx] += weight[i];
			strength[i] += weight[idx];
		}
	}
	
	private static boolean allOthersBroken(int currentIdx) {
	    for (int i = 0; i < N; i++) {
	        if (i != currentIdx && strength[i] > 0) return false;
	    }
	    return true;
	}
}
