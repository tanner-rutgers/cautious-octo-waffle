package main.java.Misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tanner on 6/26/2016.
 */
public class BigNumber {

    List<Integer> num;

    public BigNumber(Integer[] num) {
        this.num = new ArrayList<>();
        this.num.addAll(Arrays.asList(num));
    }

    public void increment() {
        int i = num.size() - 1;
        for (; i >= 0; i--) {
            if (num.get(i) < 9) {
                num.set(i, num.get(i) + 1);
                break;
            } else if (i == 0) {
                num.set(i, 1);
                num.add(0);
            } else {
                num.set(i, 0);
            }
        }
        printNum();
    }

    public void printNum() {
        StringBuilder sb = new StringBuilder();
        for (int i : num) {
            sb.append(i);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        BigNumber bigNumber = new BigNumber(new Integer[] {9, 8, 6});
        for (int i = 0; i < 20; i++) {
            bigNumber.increment();
        }
    }
}
