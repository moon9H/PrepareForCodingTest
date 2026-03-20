import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Person{
		String name;
		int height;
		int weight;
		public Person(String name, int height, int weight) {
			super();
			this.name = name;
			this.height = height;
			this.weight = weight;
		}
		public String toString() {
			return name + " " + height + " " + weight + "\n";
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		Person[] pList = new Person[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			pList[i] = new Person(name, h, w);
		}
		
		Arrays.sort(pList, (o1, o2) -> {
			if (o1.height != o2.height) return o1.height - o2.height;
			return o2.weight - o1.weight;
		});
		
		for (Person person : pList) {
			sb.append(person);
		}
		System.out.println(sb);
	}
}