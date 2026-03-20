import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Point implements Comparable<Point>{
		int number;
		int x, y;
		int distance;
		@Override
		public int compareTo(Point o) {
			if (this.distance != o.distance) return this.distance - o.distance;
			return this.number - o.number;
		}
		public Point(int number, int x, int y, int distance) {
			super();
			this.number = number;
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		Point[] pList = new Point[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pList[i] = new Point(i + 1, x, y, Math.abs(x) + Math.abs(y));
		}
		
		Arrays.sort(pList);
		
		for (Point point : pList) {
			sb.append(point.number + "\n");
		}
		System.out.println(sb);
	}
}