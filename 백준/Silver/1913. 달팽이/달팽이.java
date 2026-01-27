import java.util.Scanner;
import java.io.FileInputStream;

public class Main
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int needToFind = sc.nextInt();
		
		int[][] arr = new int[N][N];
		int idx = 1;
		int curRow = N / 2, curCol = N / 2;
		int rangeIdx = 1;
		
		quit : while (idx < N * N) {
			int upRightRange = 2 * rangeIdx - 1;
			int downLeftRange = 2 * rangeIdx;
			for (int i = 0; i < upRightRange; i++) {
				arr[curRow - i][curCol] = idx++;
				if (curRow - i == 0 && curCol == 0) {
					break quit;
				}
			}
			curRow -= upRightRange;
			for (int i = 0; i < upRightRange; i++) {
				arr[curRow][curCol + i] = idx++;
			}
			curCol += upRightRange;
			for (int i = 0; i < downLeftRange; i++) {
				arr[curRow + i][curCol] = idx++;
			}
			curRow += downLeftRange;
			for (int i = 0; i < downLeftRange; i++) {
				arr[curRow][curCol - i] = idx++;
			}
			curCol -= downLeftRange;
			
			rangeIdx++;
		}
				
		for (int[] a : arr) {
			for (int b : a) {
				System.out.print(b + " ");
			}
			System.out.println();
		}
		
		quit : for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == needToFind) {
					System.out.println((i + 1) + " " + (j + 1));
					break quit;
				}
			}
		}
		
	}
}