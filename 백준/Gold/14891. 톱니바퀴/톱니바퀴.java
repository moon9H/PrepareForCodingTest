import java.io.*;
import java.util.*;

public class Main {
    static LinkedList<Integer>[] gears = new LinkedList[5];
    static Deque<Gear> isRotate = new ArrayDeque<>();
    static int[] visited = new int[4+1];
	public static void main(String[] args) throws NumberFormatException, IOException {
        // [static] 2차원 배열에 각 톱니 번호에 맞는 인덱스에 톱니 정보를 저장한다.
        // [static] 각 턴에 회전시킬 톱니들의 번호와 방향을 저장하는 List
        // [static] 톱니 무한루프를 방지하기위한 visited 배열

        // 각 턴에 시작번호와 방향을 가지고 DFS를 시작한다
        // visited와 현재 번호에 따라 옆쪽 톱니를 회전시킬지 판단한다. 회전이 결정된경우 재귀한다.
        // 모든 DFS가 끝나면 메서드2를 호출하여 톱니를 회전시킨다.

        // 메서드 1 : dfs로 톱니의 연쇄 회전을 결정 (현재 톱니번호, 회전방향) (이번턴에 회전할 톱니번호와 방향을 List에 저장)
        // 메서드 2 : List를 순회하며 톱니를 회전하는 메서드 (톱니번호, 회전방향)
        // 클래스 1 : 톱니의 번호와 방향을 저장하는 클래스

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 톱니바퀴 정보 담기
        for(int i = 1;i<=4;i++) {
            gears[i] = new LinkedList<>();
            String line = br.readLine();
            for(int j = 0;j<8;j++) {
                gears[i].addLast(line.charAt(j) - '0');
            }
        }

        // 회전 정보를 받고 바로 연산 수행
        int rollCount = Integer.parseInt(br.readLine());
        for(int i = 0;i<rollCount;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken());
            int gearDir = Integer.parseInt(st.nextToken());
            Arrays.fill(visited, 0);
            visited[gearNum] = 1;
            dfs(gearNum, gearDir);
            rotate();
        }

        int answer = 0;
        for(int i = 1;i<=4;i++) {
            if(gears[i].get(0) == 1) {
                answer += Math.pow(2, (i-1));
            }
        }

        // 톱니 상태에 따라 점수 출력
        System.out.println(answer);
	}

    static void dfs (int nowNum, int nowDir) {
        isRotate.push(new Gear(nowNum, nowDir));
        // 우측 톱니 체크
        if(nowNum + 1 <= 4 && visited[nowNum + 1] == 0) {
            if(gears[nowNum].get(2) != gears[nowNum+1].get(6)) {
                visited[nowNum + 1] = 1;
                dfs(nowNum + 1, 0 - nowDir);
            }
        }

        // 좌측 톱니 체크
        if(nowNum - 1 >= 1 && visited[nowNum - 1] == 0) {
            if(gears[nowNum].get(6) != gears[nowNum-1].get(2)) {
                visited[nowNum - 1] = 1;
                dfs(nowNum - 1, 0 - nowDir);
            }
        }
    }

    static void rotate () {
        // 반시계 : 앞에서 빼서 뒤에 추가
        // 시계 : 뒤에서 빼서 앞에 추가
        while(!isRotate.isEmpty()) {
            Gear g = isRotate.pop();
            if(g.dir == -1) {
                gears[g.num].addLast(gears[g.num].pollFirst());
            } else {
                gears[g.num].addFirst(gears[g.num].pollLast());
            }
        }
    }

    static class Gear {
        int num;
        int dir;
        Gear(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }
}