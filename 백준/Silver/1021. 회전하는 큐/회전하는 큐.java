import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // indexOf를 사용하기 위해 LinkedList로 선언
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }
        
        // 뽑아낼 원소의 위치 배열
        int[] targets = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
        }
        
        int count = 0; // 2번, 3번 연산의 총합
        
        for (int i = 0; i < M; i++) {
            int target = targets[i];
            
            // 뽑아내고자 하는 원소의 현재 인덱스 확인
            int targetIndex = deque.indexOf(target);
            
            // 큐의 중간 지점 계산 (크기가 짝수일 경우 앞쪽으로)
            int halfIndex = deque.size() / 2;
            
            // 중간 지점보다 앞에 있거나 같으면 -> 2번 연산 (왼쪽으로 이동)
            if (targetIndex <= halfIndex) {
                for (int j = 0; j < targetIndex; j++) {
                    deque.addLast(deque.pollFirst());
                    count++;
                }
            } 
            // 중간 지점보다 뒤에 있으면 -> 3번 연산 (오른쪽으로 이동)
            else {
                for (int j = 0; j < deque.size() - targetIndex; j++) {
                    deque.addFirst(deque.pollLast());
                    count++;
                }
            }
            // 1번 연산: 원하는 원소가 맨 앞에 왔으므로 뽑아냄
            deque.pollFirst();
        }
        
        System.out.println(count);
    }
}