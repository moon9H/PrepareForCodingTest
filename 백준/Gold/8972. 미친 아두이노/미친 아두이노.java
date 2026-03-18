import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {0,1,1,1,0,0,0,-1,-1,-1};
	static int[] dc = {0,-1,0,1,-1,0,1,-1,0,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 종수 아두이노 위치를 저장한다.
		// 미친 아두이노들의 위치를 List로 저장한다.

		// 종수의 아두이노를 이동한다. 이때 방향배열은 번호에 맞게 관리한다.
		// 이동후 아두이노 List를 순회하며 겹치는곳이 있는지 판단한다. 겹칠경우 게임이 종료된다.
		
		// 미친 아두이노들을 순회하며 이동시킨다.
		// 아두이노 이동중 종수 아두이노와 겹쳐지는지 판단한다. 겹칠경우 게임이 종료된다.
		// 종수 아두이노와 r, c중 하나가 겹치면 일직선 이동한다.
		// 종수 아두이노와 r, c가 둘다 다르면 대각선 이동한다.
		// 이동 완료후 미친 아누이노끼리 겹치는지를 확인한다. 겹치는 아두이노는 제거한다.

        // 저장방식 : 좌표 pos로 저장
        // 이동방식 : 각 항목을 순회하며 이동방향 계산후 좌표 수정
        // 만나는걸 판단 : 배열 순회하며 현재값과 겹치는게 있는지 판단 (O(n^2))
        // 제거 : d로 표시 (이동과 겹침 판단시 활용)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		pos J = new pos(0, 0);      // 종수 아두이노 위치
		List<pos> A = new ArrayList<>(); // 미친 아두이노들의 위치

        // 각 아두이노의 위치를 표시
		for(int i=0;i<R;i++) {
			String[] row = br.readLine().split("");
			for(int j=0;j<C;j++) {
				if(row[j].equals("I")) {
					J.r = i;
					J.c = j;
				} else if(row[j].equals("R")) {
					A.add(new pos(i, j));
				}
			}
		}
		
        // 움직임 명령 목록
		int[] move = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		
        boolean win = true;
		int kraj = 0;
		All: for(int dir : move) {
            // 종수 아두이노 이동 후 이동횟수 추가
			J.r += dr[dir];
			J.c += dc[dir];
			kraj++;

            // 종수 아두이노 이동 후 위치가 같은 미친아두이노가 있는지 확인
			for(pos p : A) {
                if(p.d) continue;
				if(J.r == p.r && J.c == p.c) {
                    win = false;
					break All;
				}
			}

            // 미친 아두이노들 이동
            // 이동하면서 종수 아두이노와 비교
			for(pos p : A) {
                if(p.d) continue;
                int rDif = p.r - J.r;
                int cDif = p.c - J.c; 
                if(p.r == J.r) {
                    // 및아와 종아가 같은 r
                    if(p.c < J.c) {
                        // 및아가 종아보다 왼쪽
                        p.c++;
                    } else {
                        // 및아가 종아보다 오른쪽
                        p.c--;
                    }
                } else if (p.c == J.c) {
                    // 및아와 종아가 같은 c
                    if(p.r < J.r) {
                        // 및아가 종아보다 위쪽
                        p.r++;
                    } else {
                        // 및아가 종아보다 아래쪽
                        p.r--;
                    }
                } else {
                    // 아예 다른 곳 (대각선)
                    if(rDif < 0 && cDif > 0) {
                        // 1사분면
                        p.r++;
                        p.c--;
                    } else if (rDif < 0 && cDif < 0) {
                        // 2사분면
                        p.r++;
                        p.c++;
                    } else if (rDif > 0 && cDif < 0) {
                        // 3사분면
                        p.r--;
                        p.c++;
                    } else {
                        //4사분면
                        p.r--;
                        p.c--;
                    }
                }

                // 이동후 종수 아두이노와 같은 위치이면 종료
                if(p.r == J.r && p.c == J.c) {
                    win = false;
                    break All;
                }
			}

            // 미친 아두이노들 끼리 겹치는게 있는지 확인.
            // 기준을 저장하고 겹치는게 존재하는지 확인한뒤 겹치는걸 모두 제거?

            int[][] isCrash = new int[R][C];

            for(pos p : A) {
                if(p.d) continue;
                isCrash[p.r][p.c]++;
            }

            for(int i = 0;i<R;i++) {
                for(int j = 0;j<C;j++) {
                    if(isCrash[i][j] > 1) {
                        for(pos p : A) {
                            if(p.r == i && p.c == j) p.d = true;
                        }
                    }
                }
            }
		}

        if(!win) {
            // 패배
            System.out.printf("kraj %d", kraj);
        } else {
            char[][] answer = new char[R][C];
            for(int i = 0;i<R;i++) {
                Arrays.fill(answer[i], '.');
            }
            answer[J.r][J.c] = 'I';
            for(pos p : A) {
                if(p.d) continue;
                answer[p.r][p.c] = 'R';
            }
            for(int i = 0;i<R;i++) {
                for(int j = 0;j<C;j++) {
                    System.out.printf("%c", answer[i][j]);
                }
                System.out.println();
            }
        }


	}

	static class pos {
		int r;
		int c;
        boolean d = false;
		pos(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}