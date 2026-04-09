import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int f = sc.nextInt();

        n = (n / 100) * 100;

        for (int i = 0; i < 100; i++) {
            if ((n + i) % f == 0) {
                System.out.printf("%02d", i);
                break;
            }
        }
    }
}