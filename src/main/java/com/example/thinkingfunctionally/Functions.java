package com.example.thinkingfunctionally;

public class Functions {
    public static int fibI(int n) {
        int total = 0;
        int previous = 1;

        for (int i = 1; i < n; i++) {
            int temp = total;
            total += previous;
            previous = temp;
        }

        return total;
    }

    public static int fibR(int n) {
        if (n == 1) {
            return 0;
        } else if (n == 2 || n == 3) {
            return 1;
        }

        return fibR(n - 1) + fibR(n - 2);
    }

    public static int fibTailR(int n) {
        return fibTailR(n, 0, 1);
    }

    private static int fibTailR(int n, int a, int b) {
        if (n == 1)
            return a;
        if (n == 2)
            return b;
        return fibTailR(n - 1, b, a + b);
    }
}
