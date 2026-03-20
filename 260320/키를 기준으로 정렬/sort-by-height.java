import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class People implements Comparable<People>{
		String name;
		int height;
		int weight;
		
		public People(String name, int height, int weight) {
			super();
			this.name = name;
			this.height = height;
			this.weight = weight;
		}
		
		@Override
		public String toString() {
			return name + " " + height + " " + weight + "\n";
		}

		@Override
		public int compareTo(People o) {
			return Integer.compare(this.height, o.height);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		People[] pList = new People[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pList[i] = new People(name, h, w);
		}
		Arrays.sort(pList);
		
		for (int i = 0; i < n; i++) {
			sb.append(pList[i]);
		}
		System.out.println(sb);
	}
}