package Codility.Lessons.CountFactors;

public class CountFactors {
    public static void main (String[] args) {
//        A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.
//
//                For example, 6 is a factor of 24, because M = 4 satisfies the above condition (24 = 6 * 4).
//
//                Write a function:
//
//        class Solution { public int solution(int N); }
//
//        that, given a positive integer N, returns the number of its factors.
//
//                For example, given N = 24, the function should return 8, because 24 has 8 factors, namely 1, 2, 3, 4, 6, 8, 12, 24. There are no other factors of 24.
//
//        Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [1..2,147,483,647].
        System.out.println(solution(1));
        System.out.println(solution(2));
        System.out.println(solution(3));
        System.out.println(solution(4));
        System.out.println(solution(100));
        System.out.println(solution(24));

        System.out.println(solution(Integer.MAX_VALUE));

    }

    public static int solution(int N) {
        long i = 1; // as we use i * i, should be aware of overflow
        int factors = 0;

        while (i * i < N) {
            if (N % i == 0) {
                factors += 2; // 1 for i and 1 for N / i as N % (N / i) == 0
            }
            i++;
        }

        if (i * i == N) {
            factors += 1;
        }

        return factors;
    }
}
