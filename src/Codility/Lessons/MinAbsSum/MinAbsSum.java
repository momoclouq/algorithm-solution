package Codility.Lessons.MinAbsSum;

import java.util.Arrays;

public class MinAbsSum {
    public static void main(String[] args) {
//        For a given array A of N integers and a sequence S of N integers from the set {−1, 1}, we define val(A, S) as follows:
//
//        val(A, S) = |sum{ A[i]*S[i] for i = 0..N−1 }|
//
//        (Assume that the sum of zero elements equals zero.)
//
//        For a given array A, we are looking for such a sequence S that minimizes val(A,S).
//
//                Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given an array A of N integers, computes the minimum value of val(A,S) from all possible values of val(A,S) for all possible sequences S of N integers from the set {−1, 1}.
//
//        For example, given array:
//
//        A[0] =  1
//        A[1] =  5
//        A[2] =  2
//        A[3] = -2
//        your function should return 0, since for S = [−1, 1, −1, 1], val(A, S) = 0, which is the minimum possible value.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [0..20,000];
//        each element of array A is an integer within the range [−100..100].
        System.out.println(solution(new int[]{1, 5, 2, -2}));
        System.out.println(solution(new int[]{1, 5, 2, -3}));
        System.out.println(solution(new int[]{-100, -100, -100}));
        System.out.println(solution(new int[]{1, 5, 2, -3, -99, 100}));
        System.out.println(solution(new int[]{}));
        System.out.println(solution(new int[]{0}));
        System.out.println(solution(new int[]{3, 3, 3, 4, 5}));

    }

    public static int solution(int[] A) {
        if (A.length == 0) return 0;

        int[] options = new int[]{-1, 1};
        int[] dp = new int[A.length];

        dp[0] = Math.abs(A[0]); // first minimum value must always be positive value (abs)

        for (int i = 1; i < A.length; i++) {
            dp[i] = Math.abs(dp[i - 1] - Math.abs(A[i]));
        }

        return dp[A.length - 1];
    }
}
