package CheckArrIfPermu;

public class CheckArrIfPermu {
    public static void main(String[] args) {
//        A non-empty array A consisting of N integers is given.
//
//        A permutation is a sequence containing each element from 1 to N once, and only once.
//
//        For example, array A such that:
//
//        A[0] = 4
//        A[1] = 1
//        A[2] = 3
//        A[3] = 2
//        is a permutation, but array A such that:
//
//        A[0] = 4
//        A[1] = 1
//        A[2] = 3
//        is not a permutation, because value 2 is missing.
//
//        The goal is to check whether array A is a permutation.
//
//                Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given an array A, returns 1 if array A is a permutation and 0 if it is not.
//
//                For example, given array A such that:
//
//        A[0] = 4
//        A[1] = 1
//        A[2] = 3
//        A[3] = 2
//        the function should return 1.
//
//        Given array A such that:
//
//        A[0] = 4
//        A[1] = 1
//        A[2] = 3
//        the function should return 0.
//
//        Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [1..100,000];
//        each element of array A is an integer within the range [1..1,000,000,000].
        System.out.println("test 1: " + solution(new int[]{4, 1, 3, 2}));
        System.out.println("test 2: " + solution(new int[]{4, 1, 3}));
        System.out.println("test 3: " + solution(new int[]{1}));
        System.out.println("test 4: " + solution(new int[]{4, 4, 1, 3}));
    }

    public static int solution(int[] A) {
        boolean[] occurrence = new boolean[A.length]; // if A is permutation then all value must be within 0 <= A <= A.length
        int maxValue = A.length;

        for (int i = 0; i < A.length; i++) {
            if (A[i] > maxValue) return 0; // value out of range -> not permutation

            if (!occurrence[A[i] - 1]) {
                occurrence[A[i] - 1] = true;
            } else {
                return 0; // found duplicate -> not permutation
            }
        }

        return 1;
    }
}
