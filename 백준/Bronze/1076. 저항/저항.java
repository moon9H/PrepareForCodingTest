import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] power = {
        1L, 10L, 100L, 1000L, 10000L,
        100000L, 1000000L, 10000000L, 100000000L, 1000000000L
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String first = br.readLine();
        String second = br.readLine();
        String third = br.readLine();

        long value = colorToNumber(first) * 10L + colorToNumber(second);
        value *= power[colorToNumber(third)];

        System.out.println(value);
    }

    static int colorToNumber(String color) {
        switch (color) {
            case "black":
                return 0;
            case "brown":
                return 1;
            case "red":
                return 2;
            case "orange":
                return 3;
            case "yellow":
                return 4;
            case "green":
                return 5;
            case "blue":
                return 6;
            case "violet":
                return 7;
            case "grey":
                return 8;
            case "white":
                return 9;
        }
        return -1;
    }
}