import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static String dfsByPreOrder(String[] word, int current, int size) {
		if (current > size) {
			return "";
		}
		String left = dfsByPreOrder(word, current * 2, size);
		String mid = word[current];
		String right = dfsByPreOrder(word, current * 2 + 1, size);
		
		return left + mid + right;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			String[] wordTree = new String[N + 1];
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				wordTree[i] = st.nextToken();
				while(st.hasMoreTokens()) {
					st.nextToken();
				}	
			}
			sb.append("#").append(testCase).append(" ").append(dfsByPreOrder(wordTree, 1, N)).append("\n");
		}
		System.out.println(sb.toString());
	}
}