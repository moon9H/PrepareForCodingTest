import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int C, M, N,  maxSumA, maxSumB;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력 처리
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			maxSumA = maxSumB = 0;
			int ans = 0;
			int[][] hive = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					hive[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				int[] workerA = new int[M];
				for (int j = 0; j < N - M + 1; j++) {
					// 일꾼 A 배열 준비
					for (int idx = 0; idx < M; idx++) {
						workerA[idx] = hive[i][j + idx];
					}
					maxSumA = 0;
					combination(workerA, 0, 0, 0, 'A');
					for (int k = i; k < N; k++) {
						int[] workerB = new int[M];
						for (int h = (k == i) ? j + M : 0; h < N - M + 1; h++) {
							if (h + M - 1 >= N) continue;
							
							for (int idx = 0; idx < M; idx++) {
								workerB[idx] = hive[k][h + idx];
							}
							
							maxSumB = 0;
							combination(workerB, 0, 0, 0, 'B');
							
							ans = Math.max(ans, maxSumA + maxSumB);
						}
						
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	
	public static void combination(int[] honeyLine, int cnt, int honeySum, int profitSum, char human){
        // 칼로리 초과면 볼 필요 없음
        if (honeySum > C) return;
        
        if (human == 'A') {
        	maxSumA = Math.max(maxSumA, profitSum);
        } else {
        	maxSumB = Math.max(maxSumB, profitSum);
        }
        
        // 끝까지 갔다면 종료
        if (cnt == M) return;

        // cnt번째 재료 선택하는 경우
        combination(honeyLine, cnt + 1,
                honeySum + honeyLine[cnt],
                profitSum + honeyLine[cnt] * honeyLine[cnt], human);

        // cnt번째 재료 선택하지 않는 경우
        combination(honeyLine, cnt + 1, honeySum, profitSum, human);
    }
	
}