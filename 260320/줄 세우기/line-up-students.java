import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Student implements Comparable<Student>{
		int number;
		int height;
		int weight;
		public Student(int number, int height, int weight) {
			super();
			this.number = number;
			this.height = height;
			this.weight = weight;
		}
		
		public String toString() {
			return height + " " + weight + " " + number + "\n";
		}

		public int compareTo(Student o) {
			if (this.height != o.height) return o.height - this.height;
			if (this.weight != o.weight) return o.weight - this.weight;
			return this.number - o.number;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; 
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Student[] stdList = new Student[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			stdList[i] = new Student(i + 1, h, w);
		}
		
		Arrays.sort(stdList);
		
		for (Student student : stdList) {
			sb.append(student);
		}
		
		System.out.println(sb);
	}
}
