import java.util.*;
import java.io.*;

public class Main {
    // 최대 65000개 경우의수
    // 재귀로 각 cctv 방향을 선택하여 연산
    static int sizeR;
    static int sizeC;
    static int[][] board;
    static List<cctv> tvList = new ArrayList<>();
    static int totalZero = 0;
    static int totalcctv = 0;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static int max = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sizeR = Integer.parseInt(st.nextToken());
        sizeC = Integer.parseInt(st.nextToken());

        board = new int[sizeR][sizeC];
        for(int i = 0;i<sizeR;i++){
            StringTokenizer row = new StringTokenizer(br.readLine());
            for(int j = 0;j<sizeC;j++) {
                board[i][j] = Integer.parseInt(row.nextToken());
                if(board[i][j] == 0) totalZero++;
                if(board[i][j] > 0 && board[i][j] < 6) {
                    tvList.add(new cctv(i, j, board[i][j]));
                    totalcctv++;
                }
            }
        }
        dfs(0);
        System.out.println(totalZero - max);
    }
    
    static void dfs (int depth) {
        if(depth == totalcctv) {
            // depth가 cctv 개수만큼 채워졌을때 simulation을 수행한다.
            simulation();
            return;
        }

        cctv now = tvList.get(depth);
        int loop = loopCount(now.num);
        for(int i = 0;i<loop;i++) {
            now.dir = new ArrayList<>(); // 현재 경우의 수용으로 초기화
            switch(now.num) {
                case 1 :
                    now.dir.add(i);
                    break;
                case 2 :
                    now.dir.add(i);
                    now.dir.add(changeDir(i + 1));
                    break;
                case 3 :
                    now.dir.add(i);
                    now.dir.add(changeDir(i));
                    break;
                case 4 :
                    now.dir.add(i);
                    now.dir.add(changeDir(i));
                    now.dir.add(changeDir(i+1));
                    break;
                case 5 :
                    now.dir.add(i);
                    now.dir.add(changeDir(i));
                    now.dir.add(changeDir(i+1));
                    now.dir.add(changeDir(i+2));
            }
            dfs(depth + 1);
        }
        // cctv 목록에서 cctv 한개를 가져온다.
        // 해당 cctv 종류에 따라 반복문으로 각 방향에 맞춰 경우의수를 만든다.
        // 반복문 내부에서 dfs 재귀

    }

    static void simulation () {
        // 현 cctv 방향에 맞춰 시뮬레이션을 수행한다.
        // 0을 한번 지울때마다 zero변수를 증가시켜 마지막에 totalZero에서 빼면 사각지대를 구할 수 있다.
        int[][] visited = new int[sizeR][sizeC];
        int total = 0;
        for(cctv c : tvList) {
            for(int i : c.dir) {
                int nowR = c.r;
                int nowC = c.c;
                while(true) {
                    // System.out.printf("nowR: %d, nowC: %d, i: %d%n", nowR, nowC, i);
                    int nextR = nowR + dr[i];
                    int nextC = nowC + dc[i];
                    // System.out.printf("nextR: %d, nextC: %d%n", nextR, nextC);
                    if(nextR < 0 || nextC < 0 || nextR >= sizeR || nextC >= sizeC) break;
                    if(board[nextR][nextC] == 6) break;
                    // 현재칸이 visited가 0이고, board값이 0일경우에만 카운트 증가
                    if(visited[nextR][nextC] == 0 && board[nextR][nextC] == 0) {
                        visited[nextR][nextC] = 1;
                        total++;
                    }
                    nowR = nextR;
                    nowC = nextC;
                }
            }
        }
        max = Integer.max(total, max);
    }

    static int changeDir (int dir) {
        return (dir + 1) % 4;
    }

    static int loopCount (int num) {
        switch(num) {
            case 1 :
            case 3 :
            case 4 :
                return 4;
            case 2 : 
                return 2;
            case 5 :
            default:
                return 1;
        }
    }

    static class cctv {
        int r;
        int c;
        int num;
        List<Integer> dir = new ArrayList<>();
        public cctv(){}
        public cctv(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }
}
