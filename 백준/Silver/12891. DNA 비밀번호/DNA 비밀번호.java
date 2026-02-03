import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		String tempPassword = br.readLine();
		st = new StringTokenizer(br.readLine());
		int[] ACGT = new int[4];
		for (int i = 0; i < 4; i++) {
			ACGT[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		int [] containDNA = new int[4];
		for (int j = 0; j < P; j++) {
			switch(tempPassword.charAt(j)) {
			case 'A' :
				containDNA[0]++;
				break;
			case 'C' :
				containDNA[1]++;
				break;
			case 'G' :
				containDNA[2]++;
				break;
			case 'T' :
				containDNA[3]++;
				break;
			default :
				break;
			}
		}
		boolean isPossible = true;
		for (int j = 0; j < 4; j++) {
			if (containDNA[j] < ACGT[j]) {
				isPossible = false;
			}
		}
		if (isPossible) result++;
		for (int i = 1; i < S - P + 1; i++) {
			switch(tempPassword.charAt(i - 1)) {
				case 'A' :
					containDNA[0]--;
					break;
				case 'C' :
					containDNA[1]--;
					break;
				case 'G' :
					containDNA[2]--;
					break;
				case 'T' :
					containDNA[3]--;
					break;
				default :
					break;
			}
			switch(tempPassword.charAt(i + P - 1)) {
				case 'A' :
					containDNA[0]++;
					break;
				case 'C' :
					containDNA[1]++;
					break;
				case 'G' :
					containDNA[2]++;
					break;
				case 'T' :
					containDNA[3]++;
					break;
				default :
					break;
			}
			isPossible = true;
			for (int j = 0; j < 4; j++) {
				if (containDNA[j] < ACGT[j]) {
					isPossible = false;
				}
			}
			if (isPossible) result++;
		}
			
		System.out.println(result);
	}
}