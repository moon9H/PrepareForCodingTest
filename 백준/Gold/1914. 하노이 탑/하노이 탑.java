import java.util.Scanner;
import java.math.BigInteger;

public class Main {
    // 이동 경로를 효율적으로 담기 위한 StringBuilder
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 1. 총 이동 횟수 계산: 2^n - 1
        // n이 100까지 들어오므로 BigInteger 필수!
        BigInteger count = new BigInteger("2").pow(n).subtract(BigInteger.ONE);
        System.out.println(count);

        // 2. n이 20 이하인 경우에만 상세 경로 출력
        if (n <= 20) {
            hanoi(n, 1, 2, 3);
            System.out.print(sb);
        }
    }

    /**
     * 하노이 탑 재귀 함수
     * @param n           원반 개수
     * @param start       출발 기둥
     * @param auxiliary   보조 기둥
     * @param destination 목적 기둥
     */
    public static void hanoi(int n, int start, int auxiliary, int destination) {
        // Base Case: 원반이 하나일 때
        if (n == 1) {
            sb.append(start).append(" ").append(destination).append("\n");
            return;
        }

        // Step 1: 위쪽 n-1개 원반을 보조 기둥으로 이동 (목적 기둥을 보조로 활용)
        hanoi(n - 1, start, destination, auxiliary);

        // Step 2: 가장 아래 큰 원반을 목적 기둥으로 이동
        sb.append(start).append(" ").append(destination).append("\n");

        // Step 3: 보조 기둥의 n-1개 원반을 목적 기둥으로 이동 (출발 기둥을 보조로 활용)
        hanoi(n - 1, auxiliary, start, destination);
    }
}