import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        
        int answer = 0;
        
        for (int i = 0; i < N; i++) {
            int count = 0;
            
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                
                if (map[i][j] == 'Y') {
                    count++;
                    continue;
                }
                
                for (int k = 0; k < N; k++) {
                    if (map[i][k] == 'Y' && map[k][j] == 'Y') {
                        count++;
                        break;
                    }
                }
            }
            
            answer = Math.max(answer, count);
        }
        
        System.out.println(answer);
    }
}