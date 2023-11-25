package Codility.Lessons.TriangleFromEdge;

import java.util.Arrays;

public class TriangleFromEdge {
    public static void main(String[] args) {
//        An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
//
//       x
//                        For example, consider array A such that:
//
//        A[0] = 10    A[1] = 2    A[2] = 5
//        A[3] = 1     A[4] = 8    A[5] = 20
//        Triplet (0, 2, 4) is triangular.
//
//        Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given an array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.
//
//                For example, given array A such that:
//
//        A[0] = 10    A[1] = 2    A[2] = 5
//        A[3] = 1     A[4] = 8    A[5] = 20
//        the function should return 1, as explained above. Given array A such that:
//
//        A[0] = 10    A[1] = 50    A[2] = 5
//        A[3] = 1
//        the function should return 0.
//
//        Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [0..100,000];
//        each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
        System.out.println(solution(new int[]{10, 2, 5, 1, 8, 20}));
        System.out.println(solution(new int[]{10, 50, 5, 1}));
        System.out.println(solution(new int[]{-10, -50, 2, 1, 3, 20}));
        System.out.println(solution(new int[]{-10, -50, -2, -1, -120}));
        System.out.println(solution(new int[]{5, 3, 3}));
    }

    public static int solution(int[] A) {
        if (A.length < 3) return 0;

        Arrays.sort(A);

        // ignore negative number
        int positiveStartIndex = -1;

        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                positiveStartIndex = i;
                break;
            }
        }

        // array only contains negative numbers
        if (positiveStartIndex == -1) return 0;

        // assert that the remaining positive slice has 3 or more elements
        if (A.length - positiveStartIndex < 3) return 0;

        int posP = positiveStartIndex;
        int posQ = posP + 1;
        int posR = posQ + 1;

        boolean found = false;

        while (posR < A.length) {
            // reasoning: we have an assumption that if there exists 3 number P, Q, R with 0 <= P < Q < R < N that
            //A[P] + A[Q] > A[R],
            //A[Q] + A[R] > A[P],
            //A[R] + A[P] > A[Q].
            // Then P, Q, R must be next to each other when we sort the array

            long sum = A[posP] + A[posQ]; // avoid int overflow
            if (sum > (long) A[posR]) {
                found = true;
                break;
            } else {
                posP++;
                posQ++;
                posR++;
            }
        }

        return found ? 1 : 0;
    }
}
