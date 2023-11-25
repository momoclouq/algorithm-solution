package Codility.Lessons.CountSemiPrimes;

import java.util.Arrays;

public class CountSemiPrimes {
    public static void main(String[] args) {
//        A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.
//
//        A semiprime is a natural number that is the product of two (not necessarily distinct) prime numbers. The first few semiprimes are 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.
//
//        You are given two non-empty arrays P and Q, each consisting of M integers. These arrays represent queries about the number of semiprimes within specified ranges.
//
//        Query K requires you to find the number of semiprimes within the range (P[K], Q[K]), where 1 ≤ P[K] ≤ Q[K] ≤ N.
//
//                For example, consider an integer N = 26 and arrays P, Q such that:
//
//        P[0] = 1    Q[0] = 26
//        P[1] = 4    Q[1] = 10
//        P[2] = 16   Q[2] = 20
//        The number of semiprimes within each of these ranges is as follows:
//
//        (1, 26) is 10,
//                (4, 10) is 4,
//                (16, 20) is 0.
//        Write a function:
//
//        class Solution { public int[] solution(int N, int[] P, int[] Q); }
//
//        that, given an integer N and two non-empty arrays P and Q consisting of M integers, returns an array consisting of M elements specifying the consecutive answers to all the queries.
//
//        For example, given an integer N = 26 and arrays P, Q such that:
//
//        P[0] = 1    Q[0] = 26
//        P[1] = 4    Q[1] = 10
//        P[2] = 16   Q[2] = 20
//        the function should return the values [10, 4, 0], as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [1..50,000];
//        M is an integer within the range [1..30,000];
//        each element of arrays P and Q is an integer within the range [1..N];
//        P[i] ≤ Q[i].
        System.out.println(Arrays.toString(solution(26, new int[]{1, 4, 16}, new int[]{26, 10, 20})));
        System.out.println(Arrays.toString(solution(26, new int[]{1}, new int[]{1})));
        System.out.println(Arrays.toString(solution(26, new int[]{1}, new int[]{26})));
        System.out.println(Arrays.toString(solution(26, new int[]{26}, new int[]{26})));
        System.out.println(Arrays.toString(solution(100, new int[]{1, 50}, new int[]{100, 99})));
    }

    private static int[] arrayF(int N) {
        int[] result = new int[N + 1];

        int i = 2;
        while (i * i <= N) {
            int k = i * i;
            if (result[k] == 0) {
                while (k <= N) {
                    if (result[k] == 0) result[k] = i;
                    k += i;
                }
            }

            i++;
        }

        return result;
    }

    public static int[] solution(int N, int[] P, int[] Q) {
        int[] referenceArr = arrayF(N);
        int[] pref_sums = new int[referenceArr.length];

        // build prefix sum
        for (int i = 1; i < pref_sums.length; i++) {
            if (referenceArr[i] == 0 || referenceArr[i / referenceArr[i]] != 0) {
                // prime number
                pref_sums[i] = pref_sums[i - 1];
            } else {
                // semi prime number
                pref_sums[i] = pref_sums[i - 1] + 1;
            }
        }

        // build result from pref_sums
        int[] result = new int[P.length];
        for (int i = 0; i < P.length; i++) {
            result[i] = pref_sums[Q[i]] - pref_sums[P[i] - 1];
        }

        return result;
    }
}
