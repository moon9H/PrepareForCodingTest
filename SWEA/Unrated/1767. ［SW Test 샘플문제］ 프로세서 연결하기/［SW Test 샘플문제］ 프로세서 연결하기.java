import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	private static int T, N, maxCores, minWire = Integer.MAX_VALUE;
	private static List<int[]> coreList;
	private static int[][] arr;

	// 코어에 전선을 가장 많이 연결하는 경우
	// 전선의 길이가 가장 작을 때 전선 길이의 합
	// 가장자리 4개 열에 위치한 코어는 전선이 연결된 상태임

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			coreList = new ArrayList<>(); // 1. 매 케이스마다 초기화
			maxCores = 0;
			minWire = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());

					// 2. 가장자리가 아닌 코어만 리스트에 추가
					if (arr[i][j] == 1) {
						if (i == 0 || i == N - 1 || j == 0 || j == N - 1)
							continue;
						coreList.add(new int[] { i, j });
					}
				}
			}

			// 3. 0번 인덱스 코어부터, 0개 연결, 0 길이로 시작
			dfs(0, 0, 0);

			System.out.println("#" + test_case + " " + minWire);
		}
	}

	// 방향 배열 (상, 하, 좌, 우)
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static void dfs(int idx, int coreCnt, int wireLen) {
		if (idx == coreList.size()) {
			if (coreCnt > maxCores) {
				maxCores = coreCnt;
				minWire = wireLen;
			} else if (coreCnt == maxCores) {
				minWire = Math.min(minWire, wireLen);
			}
			return;
		}

		int r = coreList.get(idx)[0];
		int c = coreList.get(idx)[1];

		// 4방향 시도
		for (int d = 0; d < 4; d++) {
			int count = 0;
			int nr = r;
			int nc = c;

			// 1. 해당 방향으로 끝까지 갈 수 있는지 확인
			while (true) {
				nr += dr[d];
				nc += dc[d];

				// 경계에 도달 (성공)
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					break;

				// 가는 길에 다른 코어(1)나 전선(2)이 있음 (실패)
				if (arr[nr][nc] != 0) {
					count = 0;
					break;
				}
				count++;
			}

			// 2. 놓을 수 있다면 전선 깔고 다음 코어로 이동
			if (count > 0) {
				fill(r, c, d, 2); // 전선 표시 (2)
				dfs(idx + 1, coreCnt + 1, wireLen + count);
				fill(r, c, d, 0); // 백트래킹: 전선 제거 (0)
			}
		}

		// 3. 해당 코어를 연결하지 않고 그냥 넘어가는 경우 (중요!)
		dfs(idx + 1, coreCnt, wireLen);
	}

	// 전선을 깔거나 지우는 헬퍼 함수
	static void fill(int r, int c, int d, int val) {
		int nr = r;
		int nc = c;
		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				break;
			arr[nr][nc] = val;
		}
	}
}
