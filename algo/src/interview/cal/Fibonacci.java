package interview.cal;

import java.time.Clock;

/**
 * Created by zhao on 12/9/16.
 */
public class Fibonacci {

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        System.out.println(f.getFibonacci(8));
        System.out.println(f.getFibonacciEfficient(8));
    }


    /**
     * O(n^2)
     * space(n)
     *
     * @param n
     * @return
     */
    private long getFibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return getFibonacci(n - 1) + getFibonacci(n - 2);
    }

    /**
     * O(n)
     * space(1)
     * @param n
     * @return
     */
    private long getFibonacciEfficient(int n) {
        long fn_2 = 0, fn_1 = 1, fn = 0;
        if (n < 2) {
            return 1;
        }
        for (int idx = 2; idx <= n; idx++) {
            fn = fn_2 + fn_1;
            fn_2 = fn_1;
            fn_1 = fn;
        }
        return fn;
    }

}
