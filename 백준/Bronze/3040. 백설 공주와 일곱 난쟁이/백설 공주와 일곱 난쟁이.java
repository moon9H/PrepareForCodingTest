import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] result;
    static int[] shortGuys;
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        shortGuys = new int[9];
        result = new int[7];
        for (int i = 0; i < 9; i++){
            shortGuys[i] = Integer.parseInt(br.readLine());
        }
        combination(0, 0, 0);
    }
    public static void combination(int count, int start, int sum){
        if (count == 7){
            if (sum == 100){
                for (int i : result) System.out.println(i);
            }
            return;
        }

        for (int i = start; i < 9; i++){
            result[count] = shortGuys[i];
            combination(count + 1, i + 1, sum + shortGuys[i]);
        }
    }
}
