import java.util.Scanner;

public class Main {
	// 문자열 포함 횟수 
	static int sum = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 원본 문자열
		String origin = sc.nextLine();
		// 검색할 타겟 문자열 
		String target = sc.nextLine();
		
		// 재귀함수 호출 
		PrintSum(origin, target);
		
		sc.close();
	}
	
	static void PrintSum(String origin, String target) {
		// 처음 타겟 문자열이 나오는 인덱스 반환
		int idx = origin.indexOf(target);
		
		// 인덱스가 반환되지 않을 때까지 sum 누적, target 삭제 반복 
		if (idx == -1) {
			System.out.print(sum);
			return;
		} else {
			sum ++; 
			origin = origin.substring(idx + target.length());
			PrintSum(origin, target);
		}
	}
}
