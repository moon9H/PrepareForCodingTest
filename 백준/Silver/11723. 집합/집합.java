import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        int x = 0;
        int s = 0;
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (st.hasMoreTokens()) x = Integer.parseInt(st.nextToken());
            if (cmd.equals("add")){
                s |= 1 << (x - 1);
            } else if (cmd.equals("remove")) {
                s &= ~(1 << (x - 1));
            } else if (cmd.equals("check")){
                sb.append(( (s & (1 << (x - 1)) ) >> (x - 1)) + "\n");
            } else if (cmd.equals("toggle")){
                if (( (s & (1 << (x - 1)) ) >> (x - 1)) == 1){
                    s &= ~(1 << (x - 1));
                } else{
                    s |= 1 << (x - 1);
                }
            } else if (cmd.equals("all")){
                s = -1;
            } else if (cmd.equals("empty")) {
                s = 0;
            }
        }
        System.out.println(sb);
    }
}