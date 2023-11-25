package Codility.Lessons.Ladder;

import java.util.Arrays;

public class Ladder {
    public static void main(String[] args) {
//        You have to climb up a ladder. The ladder has exactly N rungs, numbered from 1 to N. With each step, you can ascend by one or two rungs. More precisely:
//
//        with your first step you can stand on rung 1 or 2,
//        if you are on rung K, you can move to rungs K + 1 or K + 2,
//finally you have to stand on rung N.
//                Your task is to count the number of different ways of climbing to the top of the ladder.
//
//                For example, given N = 4, you have five different ways of climbing, ascending by:
//
//        1, 1, 1 and 1 rung,
//                1, 1 and 2 rungs,
//                1, 2 and 1 rung,
//                2, 1 and 1 rungs, and
//        2 and 2 rungs.
//                Given N = 5, you have eight different ways of climbing, ascending by:
//
//        1, 1, 1, 1 and 1 rung,
//                1, 1, 1 and 2 rungs,
//                1, 1, 2 and 1 rung,
//                1, 2, 1 and 1 rung,
//                1, 2 and 2 rungs,
//                2, 1, 1 and 1 rungs,
//                2, 1 and 2 rungs, and
//        2, 2 and 1 rung.
//                The number of different ways can be very large, so it is sufficient to return the result modulo 2P, for a given integer P.
//
//        Write a function:
//
//        class Solution { public int[] solution(int[] A, int[] B); }
//
//        that, given two non-empty arrays A and B of L integers, returns an array consisting of L integers specifying the consecutive answers; position I should contain the number of different ways of climbing the ladder with A[I] rungs modulo 2B[I].
//
//                For example, given L = 5 and:
//
//        A[0] = 4   B[0] = 3
//        A[1] = 4   B[1] = 2
//        A[2] = 5   B[2] = 4
//        A[3] = 5   B[3] = 3
//        A[4] = 1   B[4] = 1
//        the function should return the sequence [5, 1, 8, 0, 1], as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        L is an integer within the range [1..50,000];
//        each element of array A is an integer within the range [1..L];
//        each element of array B is an integer within the range [1..30].
        System.out.println(Arrays.toString(solution(new int[]{4, 4, 5, 5, 1}, new int[]{3, 2, 4, 3, 1})));
    }

    private static long[] fibonacci50000() {
        long[] fib = new long[50002];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib;
    }
    // reference: https://codesays.com/2014/solution-to-ladder-by-codility/
    public static int[] solution(int[] A, int[] B) {
        // the key to this algorithm is that for Nth rung, the number of ways to reach it equals (N)th + (N - 1)th
        // equals to fib(N + 1)
        // the current solution is not technically correct as long value is not enough to hold fibonacci value to 50000
        long[] fib = fibonacci50000();
        int[] result = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            result[i] =(int) (fib[A[i] + 1] % (1 << B[i]));
        }

        return result;
    }
}
