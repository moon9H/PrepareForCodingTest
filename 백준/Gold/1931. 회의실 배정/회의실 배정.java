import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] schedules = new int[N][2];								// [0] -> start / [1] -> end
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			schedules[i][0] = Integer.parseInt(st.nextToken());
			schedules[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(schedules, (o1, o2) -> {
			if (o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
			 return Integer.compare(o1[1], o2[1]);
		});
		
		int maxSchedule = 1;
		int endTime = schedules[0][1];
		for (int i = 1; i < N; i++) {
			if (schedules[i][0] < endTime) continue;
			endTime = schedules[i][1];
			maxSchedule++;
		}
		
		System.out.println(maxSchedule);
	}
}