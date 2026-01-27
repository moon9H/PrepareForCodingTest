import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		int N;
		for(int test_case = 1; test_case <= T; test_case++)
		{	
			N = sc.nextInt();
			int[][] arr = new int[N][N];
			int idx = 1;
			arr[0][0] = idx;
			int curRow = 0, curCol = 0;
			boolean goRight = true, goDown = false, goLeft = false, goUp = false;
			while(idx < N*N) {
				if (goRight) {
					while(curCol < N - 1 && arr[curRow][curCol + 1] == 0) {
						arr[curRow][curCol + 1] = ++idx;
						curCol++;
					}
					goRight = false;
					goDown = true;
				}else if (goDown) {
					while(curRow < N - 1 && arr[curRow + 1][curCol] == 0) {
						arr[curRow + 1][curCol] = ++idx;
						curRow++;
					}
					goDown = false;
					goLeft = true;
				}else if (goLeft) {
					while(curCol > 0 && arr[curRow][curCol - 1] == 0) {
						arr[curRow][curCol - 1] = ++idx;
						curCol--;
					}
					goLeft = false;
					goUp = true;
				}else if (goUp) {
					while(curRow > 0 && arr[curRow - 1][curCol] == 0) {
						arr[curRow - 1][curCol] = ++idx;
						curRow--;
					}
					goDown = false;
					goRight = true;
				}
			}
			
			System.out.printf("#%d \n", test_case);
			for (int[] a : arr) {
				for (int b : a) {
					System.out.print(b + " ");
				}
				System.out.println();
			}
		}
	}
}