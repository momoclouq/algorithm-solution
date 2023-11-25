package Codility.Lessons.CountTriangles;

import java.util.Arrays;

public class CountTriangles {
    public static void main(String[] args) {
//        An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if it is possible to build a triangle with sides of lengths A[P], A[Q] and A[R]. In other words, triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
//
//        A[P] + A[Q] > A[R],
//                A[Q] + A[R] > A[P],
//                A[R] + A[P] > A[Q].
//                        For example, consider array A such that:
//
//        A[0] = 10    A[1] = 2    A[2] = 5
//        A[3] = 1     A[4] = 8    A[5] = 12
//        There are four triangular triplets that can be constructed from elements of this array, namely (0, 2, 4), (0, 2, 5), (0, 4, 5), and (2, 4, 5).
//
//                Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given an array A consisting of N integers, returns the number of triangular triplets in this array.
//
//                For example, given array A such that:
//
//        A[0] = 10    A[1] = 2    A[2] = 5
//        A[3] = 1     A[4] = 8    A[5] = 12
//        the function should return 4, as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [0..1,000];
//        each element of array A is an integer within the range [1..1,000,000,000].
        System.out.println(solution(new int[]{10, 2, 5, 1, 8, 12}));
        System.out.println(solution(new int[]{1, 2, 10}));
        System.out.println(solution(new int[]{1000000000, 1000000001, 1000000002, 1000000004}));

    }

    public static int solution(int[] A) {
        Arrays.sort(A);

        // when the array is sorted, the only requirement needs to be checked is  A[P] + A[Q] > A[R]
        // with P <= Q <= R
        int result = 0;
        for (int P = 0; P < A.length - 2; P++) {
            int R = P + 2;
            for (int Q = P + 1; Q < A.length - 1; Q++) {
                while (R < A.length && A[P] + A[Q] > A[R]) {
                    R++;
                }

                result += R - Q - 1;
            }
        }

        return result;
    }
}
