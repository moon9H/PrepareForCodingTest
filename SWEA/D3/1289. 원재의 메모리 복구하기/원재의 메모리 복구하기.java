import java.util.Scanner;

class Solution{
	public static void main(String args[]) throws Exception{
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++){
			String originState = sc.next();
			int[] origin = new int[originState.length()];
			int[] initialized = new int[originState.length()];
			int fixedCnt = 0;
			for (int i = 0; i < originState.length(); i++) {
				origin[i] = originState.charAt(i) - 48;
			}
			
			for (int i = 0; i < origin.length; i++) {
				if (origin[i] != initialized[i]) {
					for (int j = i; j < origin.length; j++) {
						initialized[j] = origin[i];
					}
					fixedCnt++;
				}
			}
			System.out.println("#" + test_case + " " + fixedCnt);
		}
	}
}