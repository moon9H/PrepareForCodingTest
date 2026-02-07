import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int jiminStick = 64;
        int cnt = 0;
        while (x > 0){
            while (jiminStick > x) jiminStick = jiminStick >> 1;
            x -= jiminStick;
            cnt++;
        }
        System.out.println(cnt);
    }
}