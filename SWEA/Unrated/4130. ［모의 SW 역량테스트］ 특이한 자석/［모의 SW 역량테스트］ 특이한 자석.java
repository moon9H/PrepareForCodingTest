import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	static LinkedList<Integer>[] wheels;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력 처리
			int K = Integer.parseInt(br.readLine());
			wheels = new LinkedList[4];
			for (int i = 0; i < 4; i++) {
				wheels[i] = new LinkedList<Integer>();
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					wheels[i].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int spinWheel = Integer.parseInt(st.nextToken()) - 1;
				int spinDir = Integer.parseInt(st.nextToken());
				
				int[] wheelsSpinDir = new int[4];
				wheelsSpinDir[spinWheel] = spinDir;
				
				// 오른쪽 전파
				for (int j = spinWheel; j < 3; j++) {
					if (wheels[j].get(2) != wheels[j + 1].get(6)) {
						wheelsSpinDir[j + 1] = -wheelsSpinDir[j];
					} else break;
				}
				
				// 왼쪽 전파
				for (int j = spinWheel; j > 0; j--) {
					if (wheels[j].get(6) != wheels[j - 1].get(2)) {
						wheelsSpinDir[j - 1] = -wheelsSpinDir[j];
					} else break;
				}
				
				for (int j = 0; j < 4; j++) {
					rotate(j, wheelsSpinDir[j]);
				}
				
			}
			
			int calcScore = 0;
			for (int i = 0; i < 4; i++)
				calcScore += wheels[i].peek() * (1 << i);
			sb.append("#").append(tc).append(" ").append(calcScore).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void rotate(int idx, int dir) {
		if (dir == 1) { // 시계 방향
            wheels[idx].addFirst(wheels[idx].removeLast());
        } else if (dir == -1) { // 반시계 방향
            wheels[idx].addLast(wheels[idx].removeFirst());
        }
	}
}