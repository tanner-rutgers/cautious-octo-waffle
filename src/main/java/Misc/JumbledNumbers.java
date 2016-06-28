package main.java.Misc;

/**
 * Created by Tanner on 6/25/2016.
 */
public class JumbledNumbers {

    public static void main(String[] args) {
        JumbledNumbers jumbledNumbers = new JumbledNumbers();
        jumbledNumbers.jumble(989);
    }

    void jumble(int N) {
        for (int i = 1; i < 10; i++) {
            jumble(i, N);
        }
    }

    void jumble(int m, int N) {
        if (m >= N) {
            return;
        }
        System.out.println(m);
        int last = m % 10;
        if (last > 0) {
            jumble(m * 10 + last - 1, N);
        }
        jumble(m * 10 + last, N);
        if (last < 9) {
            jumble(m * 10 + last + 1, N);
        }
    }
}
