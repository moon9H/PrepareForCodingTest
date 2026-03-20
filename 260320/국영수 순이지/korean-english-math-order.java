import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Score implements Comparable<Score>{
		String name;
		int korean;
		int english;
		int math;
		public Score(String name, int korean, int english, int math) {
			super();
			this.name = name;
			this.korean = korean;
			this.english = english;
			this.math = math;
		}
		
		public String toString() {
			return name + " " + korean + " " + english + " " + math + "\n";
		}

		public int compareTo(Score o) {
			if (this.korean != o.korean) return o.korean - this.korean;
			if (this.english != o.english) return o.english - this.english;
			return o.math - this.math;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		Score[] sList = new Score[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int k = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			sList[i] = new Score(name, k, e, m);
		}
		Arrays.sort(sList);
		
		for (Score score : sList) {
			sb.append(score);
		}
		System.out.println(sb);
	}
}
