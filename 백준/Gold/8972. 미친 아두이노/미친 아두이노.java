import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {0,1,1,1,0,0,0,-1,-1,-1};
	static int[] dc = {0,-1,0,1,-1,0,1,-1,0,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		pos J = new pos(0, 0);      // 종수 아두이노 위치
		List<pos> A = new ArrayList<>(); // 미친 아두이노들의 위치

		for(int i=0;i<R;i++) {
			String row = br.readLine();
			for(int j=0;j<C;j++) {
                char ch = row.charAt(j);
				if(ch == 'I') {
					J.r = i;
					J.c = j;
				} else if(ch == 'R') {
					A.add(new pos(i, j));
				}
			}
		}
		
        String move = br.readLine();
		int kraj = 0;

		for(int m = 0;m<move.length();m++) {
            // 종수 아두이노 이동
            int dir = move.charAt(m) - '0';
			J.r += dr[dir];
			J.c += dc[dir];
			kraj++;

			for(pos p : A) {    // 종수 아두이노 이동후 충돌 판정
				if(J.r == p.r && J.c == p.c) {
                    System.out.printf("kraj %d", kraj);
					return;
				}
			}

            int[][] isCrash = new int[R][C];    // 특정 위치에 몇개의 미친 아두이노가 있는지 카운트

			for(pos p : A) {    // 미친 아두이노 이동
                p.r -= Integer.compare(p.r - J.r, 0);
                p.c -= Integer.compare(p.c - J.c, 0);

                // 각 아두이노 이동후 개별적으로 충돌 판정
                if(p.r == J.r && p.c == J.c) {
                    System.out.printf("kraj %d", kraj);
                    return;
                }

                isCrash[p.r][p.c]++;
			}

            List<pos> newList = new ArrayList<>();
            for(pos p : A) {
                if(isCrash[p.r][p.c] == 1) newList.add(p);
            }

            A = newList;
		}

        char[][] answer = new char[R][C];
        for(int i = 0;i<R;i++) {
            Arrays.fill(answer[i], '.');
        }
        answer[J.r][J.c] = 'I';
        for(pos p : A) {
            answer[p.r][p.c] = 'R';
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<R;i++) {
            for(int j = 0;j<C;j++) {
                sb.append(answer[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
	}

	static class pos {
		int r;
		int c;
		pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}