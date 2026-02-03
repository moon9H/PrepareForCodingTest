import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		int T = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			LinkedList<Integer> list = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			int commandNum = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < commandNum; i++) {
				String cmd = st.nextToken();
				if (cmd.equals("I")) {
					int insertPos = Integer.parseInt(st.nextToken());
					int insertCnt = Integer.parseInt(st.nextToken());
					for (int j = 0; j < insertCnt; j++) {
						list.add(insertPos + j, Integer.parseInt(st.nextToken()));
					}
				} else if (cmd.equals("D")) {
					int deletePos = Integer.parseInt(st.nextToken());
					int deleteNum = Integer.parseInt(st.nextToken());
					for (int j = 0; j < deleteNum; j++) {
						list.remove(deletePos);
					}
				}
			}
			sb.append("#").append(testCase);
			for (int i = 0; i < 10; i++) {
				sb.append(" ").append(list.get(i));
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}