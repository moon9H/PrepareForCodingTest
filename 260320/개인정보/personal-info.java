import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Person{
		String name;
		int height;
		double weight;
		public Person(String name, int height, double weight) {
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
		
		Person[] pList = new Person[5];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int h = Integer.parseInt(st.nextToken());
			double w = Double.parseDouble(st.nextToken());
			
			pList[i] = new Person(name, h, w);
		}
		
		Arrays.sort(pList, (o1, o2) -> o1.name.compareTo(o2.name));
		sb.append("name\n");
		for (Person person : pList) {
			sb.append(person);
		}
		Arrays.sort(pList, (o1, o2) -> Integer.compare(o2.height, o1.height));
		sb.append("\nheight\n");
		for (Person person : pList) {
			sb.append(person);
		}
		System.out.println(sb);
	}
}